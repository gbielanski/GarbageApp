package pl.example.android.garbageapp.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorType;
import pl.example.android.garbageapp.data.database.TermType;
import pl.example.android.garbageapp.data.network.model.SectorData;
import pl.example.android.garbageapp.utils.AppExecutors;

/**
 * Created by miltomasz on 04/11/17.
 */
public class SectorTermsNetworkDataSource {

    private static final String LOG_TAG = SectorTermsNetworkDataSource.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String SECTOR_TERMS__SYNC_TAG = "sector-terms-sync";

    // sync job service time units
    private static final int SYNC_INTERVAL_HOURS = 12;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 12;

    private static SectorTermsNetworkDataSource sInstance;
    private final AppExecutors mExecutors;

    // Database & Firebase
    private DatabaseReference mFirebaseDatabaseReference;
    private MutableLiveData<List<SectorTerm>> mDownloadedSectors;
    private final Context mContext;

    private SectorTermsNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        mDownloadedSectors = new MutableLiveData<>();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public static SectorTermsNetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SectorTermsNetworkDataSource(context.getApplicationContext(), executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    public void scheduleRecurringFetchSectorTermsSync() {

        Driver driver = new GooglePlayDriver(mContext);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job syncSectorTermsJob = dispatcher.newJobBuilder()
                .setService(SectorTermFirebaseJobService.class)
                .setTag(SECTOR_TERMS__SYNC_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_SECONDS,
                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();
        // Schedule the Job with the dispatcher
        dispatcher.schedule(syncSectorTermsJob);
        Log.d(LOG_TAG, "Sync SectorData terms Job scheduled");
    }

    public void startSectorTermsSyncService() {
        Intent intentToFetch = new Intent(mContext, SectorTermSyncIntentService.class);
        mContext.startService(intentToFetch);
        Log.d(LOG_TAG, "Service created");
    }

    public void fetchSectorTerms() {
        Log.d(LOG_TAG, "Fetch sector terms started");
        DatabaseReference blueRef = mFirebaseDatabaseReference.child("blue");
        blueRef.addValueEventListener(new SectorValueEventListener(SectorType.BLUE));

        DatabaseReference greenRef = mFirebaseDatabaseReference.child("green");
        greenRef.addValueEventListener(new SectorValueEventListener(SectorType.GREEN));

        DatabaseReference yellowRef = mFirebaseDatabaseReference.child("yellow");
        yellowRef.addValueEventListener(new SectorValueEventListener(SectorType.YELLOW));
    }

    public LiveData<List<SectorTerm>> getDownloadedSectors() {
        return mDownloadedSectors;
    }

    private class SectorValueEventListener implements ValueEventListener {

        private SectorType sectorType;

        SectorValueEventListener(SectorType sectorType){
            super();
            this.sectorType = sectorType;
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            List<SectorTerm> sectorModel = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                SectorData sectorData = snapshot.getValue(SectorData.class);
                sectorModel.add(new SectorTerm(sectorData.getTerm(), TermType.valueOf(sectorData.getType()), sectorType));
            }
            synchronized (this) {
                mDownloadedSectors.postValue(sectorModel);
            }
        }

        @Override
        public void onCancelled(DatabaseError error) {
            Log.w(LOG_TAG, "Failed to read value from sector.", error.toException());
        }
    }
}

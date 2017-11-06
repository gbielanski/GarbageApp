package pl.example.android.garbageapp.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pl.example.android.garbageapp.data.SectorTermSyncIntentService;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.utils.AppExecutors;

/**
 * Created by miltomasz on 04/11/17.
 */
public class SectorTermsNetworkDataSource {

    private static final String LOG_TAG = SectorTermsNetworkDataSource.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static SectorTermsNetworkDataSource sInstance;
    private final AppExecutors mExecutors;

    // Database & Firebase
    private DatabaseReference mFirebaseDatabaseReference;
    private MutableLiveData<List<SectorTerm>> mDownloadedSectorTerms;
    private final Context mContext;

    private SectorTermsNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        mDownloadedSectorTerms = new MutableLiveData<>();
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

    public void startFetchWeatherService() {
        Intent intentToFetch = new Intent(mContext, SectorTermSyncIntentService.class);
        mContext.startService(intentToFetch);
        Log.d(LOG_TAG, "Service created");
    }

    public void fetchSectorTerms() {
        Log.d(LOG_TAG, "Fetch sector terms started");
        DatabaseReference monthsRef = mFirebaseDatabaseReference.child("months");
        monthsRef.addValueEventListener(new ValueEventListener() {
            public final String LOG_TAG = ValueEventListener.class.getSimpleName();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<SectorTerm> sectorTerms = new ArrayList<>();
                for (DataSnapshot sectorTermSnapshots : dataSnapshot.getChildren()) {
                    SectorTerm sectorTerm = sectorTermSnapshots.getValue(SectorTerm.class);
                    sectorTerms.add(sectorTerm);
                }
                mDownloadedSectorTerms.postValue(sectorTerms);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(LOG_TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public LiveData<List<SectorTerm>> getDownloadedSectorTerms() {
        return mDownloadedSectorTerms;
    }
}

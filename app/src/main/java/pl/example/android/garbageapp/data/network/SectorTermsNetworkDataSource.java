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
    private MutableLiveData<List<Blue>> mDownloadedBlueSectors;
    private MutableLiveData<List<Green>> mDownloadedGreenSectors;
    private MutableLiveData<List<Yellow>> mDownloadedYellowSectors;
    private final Context mContext;

    private SectorTermsNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        mDownloadedBlueSectors = new MutableLiveData<>();
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
        DatabaseReference blueRef = mFirebaseDatabaseReference.child("blue");

        blueRef.addValueEventListener(new ValueEventListener() {
            public final String LOG_TAG = ValueEventListener.class.getSimpleName();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Blue> blueModel = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Blue blue = snapshot.getValue(Blue.class);
                    blueModel.add(blue);
                }
                mDownloadedBlueSectors.postValue(blueModel);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(LOG_TAG, "Failed to read value from Blue.", error.toException());
            }
        });

        DatabaseReference greenRef = mFirebaseDatabaseReference.child("green");
        greenRef.addValueEventListener(new ValueEventListener() {
            public final String LOG_TAG = ValueEventListener.class.getSimpleName();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Green> greenModel = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Green green = snapshot.getValue(Green.class);
                    greenModel.add(green);
                }
                mDownloadedGreenSectors.postValue(greenModel);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(LOG_TAG, "Failed to read value from Green.", error.toException());
            }
        });

        DatabaseReference yellowRef = mFirebaseDatabaseReference.child("yellow");
        yellowRef.addValueEventListener(new ValueEventListener() {
            public final String LOG_TAG = ValueEventListener.class.getSimpleName();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Yellow> yellowModel = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Yellow yellow = snapshot.getValue(Yellow.class);
                    yellowModel.add(yellow);
                }
                mDownloadedYellowSectors.postValue(yellowModel);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(LOG_TAG, "Failed to read value from Yellow.", error.toException());
            }
        });
    }

    public LiveData<List<Blue>> getDownloadedBlueSectors() {
        return mDownloadedBlueSectors;
    }

    public LiveData<List<Green>> getDownloadedGreenSectors() {
        return mDownloadedGreenSectors;
    }

    public LiveData<List<Yellow>> getDownloadedYellowSectors() {
        return mDownloadedYellowSectors;
    }
}

package pl.example.android.garbageapp.data;

import androidx.lifecycle.LiveData;
import android.util.Log;

import java.util.Date;
import java.util.List;

import pl.example.android.garbageapp.data.database.SectorColor;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorTermDao;

import pl.example.android.garbageapp.data.database.SectorTermsDatabaseDataSource;

import pl.example.android.garbageapp.data.network.SectorTermsNetworkDataSource;
import pl.example.android.garbageapp.utils.AppExecutors;
import pl.example.android.garbageapp.utils.SectorTermsDateUtils;

/**
 * Created by miltomasz on 03/11/17.
 */

public class SectorTermRepository {

    private static final String LOG_TAG = SectorTermRepository.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static SectorTermRepository sInstance;
    private final SectorTermDao mSectorTermDao;
    private final SectorTermsNetworkDataSource mSectorTermsNetworkDataSource;
    private final SectorTermsDatabaseDataSource mSectorTermsDatabaseDataSource;
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    private SectorTermRepository(SectorTermDao sectorTermDao,
                                 SectorTermsNetworkDataSource sectorTermsNetworkDataSource,
                                 SectorTermsDatabaseDataSource sectorTermsDatabaseDataSource,
                                 AppExecutors executors) {
        mSectorTermDao = sectorTermDao;
        mSectorTermsNetworkDataSource = sectorTermsNetworkDataSource;
        mSectorTermsDatabaseDataSource = sectorTermsDatabaseDataSource;
        mExecutors = executors;

        LiveData<List<SectorTerm>> sectorNetworkData = mSectorTermsNetworkDataSource.getDownloadedSectors();
        sectorNetworkData.observeForever(newSectorTermsFromNetwork -> {
            mExecutors.diskIO().execute(() -> {
                deleteOldData();
                Log.d(LOG_TAG, "Old sector terms deleted");
                insertNewData(newSectorTermsFromNetwork);
                Log.d(LOG_TAG, "New values inserted");
            });
        });
    }

    public synchronized static SectorTermRepository getInstance(SectorTermDao sectorTermDao,
                                                                SectorTermsNetworkDataSource sectorTermsNetworkDataSource,
                                                                SectorTermsDatabaseDataSource sectorTermsDatabaseDataSource,
                                                                AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SectorTermRepository(sectorTermDao, sectorTermsNetworkDataSource,
                        sectorTermsDatabaseDataSource, executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }


    private synchronized void initializeData() {
        if (mInitialized) {
            return;
        }
        mInitialized = true;

        // setup job scheduler for fetching sector terms every 12 hrs
        mSectorTermsNetworkDataSource.scheduleRecurringFetchSectorTermsSync();

        // setup alarm manager for checking if notification is needed
        mSectorTermsDatabaseDataSource.scheduleCountingSectorTermsForNotification();

        // fetch sector terms now
        mSectorTermsNetworkDataSource.startSectorTermsSyncService();
    }

    private void deleteOldData() {
        // TODO implement Dao delete method
        mSectorTermDao.deleteAll();
    }

    private void insertNewData(List<SectorTerm> sectorTerms) {
        mSectorTermDao.bulkInsert(
                (SectorTerm[]) sectorTerms.toArray(new SectorTerm[sectorTerms.size()])
        );
    }

    public LiveData<List<SectorTerm>> getCurrentSectorTerms(SectorColor sectorColor) {
        initializeData();
        Date today = SectorTermsDateUtils.getNormalizedUtcDateForToday();
        return mSectorTermDao.getFutureSectorTerms(today, SectorColor.toInt(sectorColor));
    }
}

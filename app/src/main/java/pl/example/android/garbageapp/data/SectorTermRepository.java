package pl.example.android.garbageapp.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorTermDao;
import pl.example.android.garbageapp.data.database.SectorType;
import pl.example.android.garbageapp.data.network.SectorTermsNetworkDataSource;
import pl.example.android.garbageapp.utils.AppExecutors;

/**
 * Created by miltomasz on 03/11/17.
 */

public class SectorTermRepository {

    private static final String LOG_TAG = SectorTermRepository.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static SectorTermRepository sInstance;
    private final SectorTermDao mSectorTermDao;
    private final SectorTermsNetworkDataSource mSectorTermsNetworkDataSource;
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    private SectorTermRepository(SectorTermDao sectorTermDao,
                                 SectorTermsNetworkDataSource sectorTermsNetworkDataSource,
                                 AppExecutors executors) {
        mSectorTermDao = sectorTermDao;
        mSectorTermsNetworkDataSource = sectorTermsNetworkDataSource;
        mExecutors = executors;

        LiveData<List<SectorTerm>> sectorNetworkData = mSectorTermsNetworkDataSource.getDownloadedSectors();
        sectorNetworkData.observeForever(newSectorTermsFromNetwork -> {
            mExecutors.diskIO().execute(() -> {
                deleteOldData();
                Log.d(LOG_TAG, "Old weather deleted");
                insertNewData(newSectorTermsFromNetwork);
                Log.d(LOG_TAG, "New values inserted");
            });
        });
    }

    public synchronized static SectorTermRepository getInstance(SectorTermDao sectorTermDao,
                                                                SectorTermsNetworkDataSource sectorTermsNetworkDataSource,
                                                                AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SectorTermRepository(
                        sectorTermDao, sectorTermsNetworkDataSource, executors);
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

        // fetch sector terms now
        mExecutors.diskIO().execute(() -> startFetchSectorTerms());
    }

    private void startFetchSectorTerms() {
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

    public LiveData<List<SectorTerm>> getCurrentSectorTerms(SectorType sectorType) {
        initializeData();

        Date yesterday = getYesterdayEndOfDay();
        return mSectorTermDao.getCurrentSectorTerms(yesterday, sectorType);
        //return mSectorTermDao.getAllSectorTerms();
    }

    private Date getYesterdayEndOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }
}

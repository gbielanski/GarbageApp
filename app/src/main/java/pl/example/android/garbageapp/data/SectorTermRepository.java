package pl.example.android.garbageapp.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorTermDao;
import pl.example.android.garbageapp.data.database.TermType;
import pl.example.android.garbageapp.data.network.SectorTermsNetworkDataSource;
import pl.example.android.garbageapp.data.network.model.Sector;
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

        LiveData<List<Sector>> sectorNetworkData = mSectorTermsNetworkDataSource.getDownloadedSectors();
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
        mExecutors.diskIO().execute(() -> startFetchSectorTerms());
    }

    private void startFetchSectorTerms() {
        mSectorTermsNetworkDataSource.startSectorTermsSyncService();
    }

    private void deleteOldData() {
        // TODO implement Dao delete method
        mSectorTermDao.deleteAll();
    }

    private void insertNewData(List<Sector> sectors) {
        List<SectorTerm> sectorTerms = new ArrayList<>();
        for (Sector s : sectors) {
            SectorTerm st = new SectorTerm(s.getTerm(), TermType.valueOf(s.getType()));
            sectorTerms.add(st);
        }
        mSectorTermDao.bulkInsert(
                (SectorTerm[]) sectorTerms.toArray(new SectorTerm[sectorTerms.size()])
        );
    }

    public LiveData<List<SectorTerm>> getCurrentSectorTerms() {
        initializeData();
        return mSectorTermDao.getAllSectorTerms();
    }
}

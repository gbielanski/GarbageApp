package pl.example.android.garbageapp.data.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Date;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.utils.AppExecutors;
import pl.example.android.garbageapp.utils.NotificationUtils;
import pl.example.android.garbageapp.utils.SectorTermsDateUtils;

/**
 * Service class for operating on local data source (sqlite, shared preferences).
 * Implemented as singleton.
 */

public class SectorTermsDatabaseDataSource {

    private static final String LOG_TAG = SectorTermsDatabaseDataSource.class.getSimpleName();


    private static final Object LOCK = new Object();
    private static SectorTermsDatabaseDataSource sInstance;

    private final AppExecutors mExecutors;
    private final Context mContext;
    private final SectorTermDao mSectorTermDao;

    private SectorTermsDatabaseDataSource(Context context,
                                          SectorTermDao sectorTermDao, AppExecutors executors) {
        mContext = context;
        mSectorTermDao = sectorTermDao;
        mExecutors = executors;
    }

    public static SectorTermsDatabaseDataSource getInstance(Context context,
                                                            SectorTermDao sectorTermDao,
                                                            AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the database data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SectorTermsDatabaseDataSource(
                        context.getApplicationContext(), sectorTermDao, executors
                );
                Log.d(LOG_TAG, "Made new database data source");
            }
        }
        return sInstance;
    }

    /**
     * Uses dao object to retrieve number of sector terms which should be processed next day.
     */
    public void countSectorTermsForNotification() {
        Date tomorrow = SectorTermsDateUtils.getNormalizedUtcDateForTomorrow();
        int notificationSectorColor = SectorColor.toInt(SectorColor.UNSET);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        if(sharedPreferences.contains(NotificationUtils.NOTIFICATION_SECTOR_COLOR)) {
            notificationSectorColor =
                    sharedPreferences.getInt(NotificationUtils.NOTIFICATION_SECTOR_COLOR, notificationSectorColor);
        }
        int count = mSectorTermDao.countSectorTermsForTomorrow(tomorrow, notificationSectorColor);
        String msg = mContext.getString(R.string.tomorrow_garbage_collection_msg);

        Log.d(LOG_TAG, "Sector terms counted: " + count + ". Notifying user");
        for (int notificationId = 0; notificationId < count; notificationId++) {
            NotificationUtils.showNotification(mContext, notificationId, notificationSectorColor);
        }
    }

    public void scheduleCountingSectorTermsForNotification() {
        NotificationUtils.scheduleSectorTermsNotification(mContext);
    }
}

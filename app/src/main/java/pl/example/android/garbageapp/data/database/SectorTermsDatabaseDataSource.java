package pl.example.android.garbageapp.data.database;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import pl.example.android.garbageapp.utils.AppExecutors;
import pl.example.android.garbageapp.utils.NotificationUtils;
import pl.example.android.garbageapp.utils.SectorTermsDateUtils;

/**
 * Created by miltomasz on 21/11/17.
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

    public void scheduleCountingSectorTermsForNotification() {
        Intent intent = new Intent(mContext, SectorTermNotificationIntentService.class);
        PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, intent, 0 );
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = getCalendarForNotification();
        // set up alarm manager to repeat
        alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void countSectorTermsForNotification() {
        Date today = SectorTermsDateUtils.getNormalizedUtcDateForToday();
        int count = mSectorTermDao.countSectorTermsForToday(today);
        if (count > 0) {
            NotificationUtils.showNotification(mContext, "Dziś wywóz śmieci");
        }
    }

    @NonNull
    private Calendar getCalendarForNotification() {
        // set the alarm to start at approximately 8:00 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        return calendar;
    }
}

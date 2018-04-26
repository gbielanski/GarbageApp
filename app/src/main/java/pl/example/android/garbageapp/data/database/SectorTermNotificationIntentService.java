package pl.example.android.garbageapp.data.database;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;

import pl.example.android.garbageapp.utils.InjectorUtils;
import pl.example.android.garbageapp.utils.NotificationUtils;

/**
 * Service class fired off by {@code {@link android.app.AlarmManager}} every day a given time.
 * It launches {@code {@link SectorTermsDatabaseDataSource}} to count
 * sector terms.
 */

public class SectorTermNotificationIntentService extends IntentService {

    private static final String SERVICE_NAME = SectorTermNotificationIntentService.class.getSimpleName();
    private static final String LOG_TAG = SERVICE_NAME;

    public SectorTermNotificationIntentService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "Notification service started");
        SectorTermsDatabaseDataSource sectorTermsDatabaseDataSource =
                InjectorUtils.provideDatabaseDataSource(getApplicationContext());
        sectorTermsDatabaseDataSource.countSectorTermsForNotification();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NotificationUtils.scheduleNotificationIntentServiceTriggering(getApplicationContext());
        }
    }
}

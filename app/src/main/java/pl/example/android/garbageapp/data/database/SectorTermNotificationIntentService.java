package pl.example.android.garbageapp.data.database;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import pl.example.android.garbageapp.utils.InjectorUtils;

/**
 * Created by miltomasz on 15/11/17.
 */

public class SectorTermNotificationIntentService extends IntentService {

    private static final String SERVICE_NAME = SectorTermNotificationIntentService.class.getSimpleName();
    private static final String LOG_TAG = SERVICE_NAME;

    public SectorTermNotificationIntentService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "Service started");
        SectorTermsDatabaseDataSource sectorTermsDatabaseDataSource =
                InjectorUtils.provideDatabaseDataSource(getApplicationContext());
        sectorTermsDatabaseDataSource.countSectorTermsForNotification();
    }
}

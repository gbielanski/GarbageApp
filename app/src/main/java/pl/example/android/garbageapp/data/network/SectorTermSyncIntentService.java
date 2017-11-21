package pl.example.android.garbageapp.data.network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import pl.example.android.garbageapp.utils.InjectorUtils;

/**
 * Created by miltomasz on 06/11/17.
 */
public class SectorTermSyncIntentService extends IntentService {

    private static final String LOG_TAG = SectorTermSyncIntentService.class.getSimpleName();

    public SectorTermSyncIntentService() {
        super("SectorTermSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "Intent service started");
        SectorTermsNetworkDataSource sectorTermsNetworkDataSource =
                InjectorUtils.provideNetworkDataSource(getApplicationContext());
        sectorTermsNetworkDataSource.fetchSectorTerms();
    }
}

package pl.example.android.garbageapp.utils;

import android.content.Context;

import pl.example.android.garbageapp.data.SectorTermRepository;
import pl.example.android.garbageapp.data.database.SectorTermsDatabase;
import pl.example.android.garbageapp.data.network.SectorTermsNetworkDataSource;

/**
 * Created by miltomasz on 06/11/17.
 */

public class InjectorUtils {

    public static SectorTermRepository provideRepository(Context context) {
        SectorTermsDatabase database = SectorTermsDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        SectorTermsNetworkDataSource networkDataSource =
                SectorTermsNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return SectorTermRepository.getInstance(database.sectorTermDao(), networkDataSource, executors);
    }

    public static SectorTermsNetworkDataSource provideNetworkDataSource(Context context) {
        provideRepository(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return SectorTermsNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

}

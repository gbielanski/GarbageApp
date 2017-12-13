package pl.example.android.garbageapp.utils;

import android.content.Context;

import pl.example.android.garbageapp.data.SectorTermRepository;
import pl.example.android.garbageapp.data.database.SectorTermsDatabase;
import pl.example.android.garbageapp.data.database.SectorTermsDatabaseDataSource;
import pl.example.android.garbageapp.data.network.SectorTermsNetworkDataSource;

/**
 * Created by miltomasz on 06/11/17.
 */

public class InjectorUtils {

    public static SectorTermRepository provideRepository(Context context) {
        // get database
        SectorTermsDatabase database = SectorTermsDatabase.getInstance(context.getApplicationContext());
        // get executors
        AppExecutors executors = AppExecutors.getInstance();
        // get network datasource
        SectorTermsNetworkDataSource networkDataSource =
                SectorTermsNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        // get database datasource
        SectorTermsDatabaseDataSource databaseDataSource =
                SectorTermsDatabaseDataSource.getInstance(
                        context.getApplicationContext(), database.sectorTermDao(), executors);
        // finally, get repository
        return SectorTermRepository.getInstance(
                database.sectorTermDao(), networkDataSource, databaseDataSource, executors
        );
    }

    public static SectorTermsNetworkDataSource provideNetworkDataSource(Context context) {
        provideRepository(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return SectorTermsNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    public static SectorTermsDatabaseDataSource provideDatabaseDataSource(Context context) {
        provideRepository(context.getApplicationContext());
        // get database
        SectorTermsDatabase database = SectorTermsDatabase.getInstance(context.getApplicationContext());
        // get executors
        AppExecutors executors = AppExecutors.getInstance();
        // get database datasource
        return SectorTermsDatabaseDataSource.getInstance(
                context.getApplicationContext(), database.sectorTermDao(), executors
        );
    }
}

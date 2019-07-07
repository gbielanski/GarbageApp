package pl.example.android.garbageapp.data.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {SectorTerm.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class SectorTermsDatabase extends RoomDatabase {

    private static final String LOG_TAG = SectorTermsDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "waste";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static SectorTermsDatabase sInstance;

    public static SectorTermsDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        SectorTermsDatabase.class, SectorTermsDatabase.DATABASE_NAME).build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract SectorTermDao sectorTermDao();
}

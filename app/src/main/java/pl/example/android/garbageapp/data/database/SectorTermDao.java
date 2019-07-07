package pl.example.android.garbageapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface SectorTermDao {

    @Query("SELECT * FROM sector_terms WHERE term >= :term AND sectorColor == :sectorColor")
    LiveData<List<SectorTerm>> getFutureSectorTerms(Date term, int sectorColor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(SectorTerm... sectorTerm);

    @Query("DELETE FROM sector_terms WHERE term < :term")
    void deleteOldSectorTerms(Date term);

    @Query("SELECT * FROM sector_terms")
    LiveData<List<SectorTerm>> getAllSectorTerms();

    @Query("DELETE FROM sector_terms")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM sector_terms WHERE term = :tomorrow AND sectorColor = :sectorColor")
    int countSectorTermsForTomorrow(Date tomorrow, int sectorColor);
}

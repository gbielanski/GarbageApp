package pl.example.android.garbageapp.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface SectorTermDao {

    @Query("SELECT * FROM sector_terms WHERE term >= :t AND sectorType == :sectorType")
    LiveData<List<SectorTerm>> getCurrentSectorTerms(Date t, SectorType sectorType);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(SectorTerm... sectorTerm);

    @Query("DELETE FROM sector_terms WHERE term < :term")
    void deleteOldSectorTerms(Date term);

    @Query("SELECT * FROM sector_terms")
    LiveData<List<SectorTerm>> getAllSectorTerms();

    @Query("DELETE FROM sector_terms")
    void deleteAll();
}

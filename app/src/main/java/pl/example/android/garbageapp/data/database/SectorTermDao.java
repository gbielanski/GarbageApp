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

    @Query("SELECT * FROM sector_terms WHERE term >= :term AND sectorType == :sectorType")
    LiveData<List<SectorTerm>> getFutureSectorTerms(Date term, int sectorType);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(SectorTerm... sectorTerm);

    @Query("DELETE FROM sector_terms WHERE term < :term")
    void deleteOldSectorTerms(Date term);

    @Query("SELECT * FROM sector_terms")
    LiveData<List<SectorTerm>> getAllSectorTerms();

    @Query("DELETE FROM sector_terms")
    void deleteAll();
}

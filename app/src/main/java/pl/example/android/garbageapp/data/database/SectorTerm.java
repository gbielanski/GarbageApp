package pl.example.android.garbageapp.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "sector_terms", indices = {@Index(value = {"sectorType"})})
public class SectorTerm {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String term;
    private String termType;
    private String sectorType;

    public SectorTerm(String term, String termType) {
        this.term = term;
        this.termType = termType;
    }

    // Constructor used by Room
    public SectorTerm(int id, String term, String termType, String sectorType) {
        this.id = id;
        this.term = term;
        this.termType = termType;
        this.sectorType = sectorType;
    }

    public String getTerm() {
        return term;
    }
    public String getTermType() {
        return termType;
    }
    public int getId() { return id; }
    public String getSectorType() { return sectorType; }
}

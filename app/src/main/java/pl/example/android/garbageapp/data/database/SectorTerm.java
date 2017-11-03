package pl.example.android.garbageapp.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "sector_terms", indices = {@Index(value = {"sectorType"})})
public class SectorTerm {
    private static final  int greenSectorType = 1;
    private static final  int blueSectorType = 2;
    private static final  int yellowSectorType = 3;

    public static final int termTypeMixed = 1;
    public static final int termTypeSegregated = 2;

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date term;
    private int termType;
    private int sectorType;

    @Ignore
    public SectorTerm(String term, int termType) {
        String dateFormat = "yyyy.MM.dd";
        Date dateTerm = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        try {
            dateTerm = simpleDateFormat.parse(term);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.term = dateTerm;
        this.termType = termType;
        sectorType = greenSectorType;
    }

    // Constructor used by Room
    public SectorTerm(int id, Date term, int termType, int sectorType) {
        this.id = id;
        this.term = term;
        this.termType = termType;
        this.sectorType = sectorType;
    }

    public String getTerm() {
        String dateFormat = "EEEE, d MMMM YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, new Locale("pl", "PL"));
        return simpleDateFormat.format(term);
    }

    //TODO extract outside
    public String getTermType() {
        switch (termType) {
            case termTypeMixed:
                return "zmieszane";
            case termTypeSegregated:
                return "segregowane";
            default:
                return "UNKNOWN";
        }
    }

    public int getId() {
        return id;
    }

    //TODO extract outside
    public String getSectorType() {
        switch (sectorType) {
            case greenSectorType:
                return "GREEN";
            case blueSectorType:
                return "BLUE";
            case yellowSectorType:
                return "YELLOW";
            default:
                return "UNKNOWN";
        }
    }
}

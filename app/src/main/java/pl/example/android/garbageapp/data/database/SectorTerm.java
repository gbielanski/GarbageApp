package pl.example.android.garbageapp.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static pl.example.android.garbageapp.data.database.SectorTerm.TermType.*;

@Entity(tableName = "sector_terms", indices = {@Index(value = {"sectorType"})})
public class SectorTerm {

    public enum SectorType{
        GREEN,
        BLUE,
        YELLOW;
    }

    public enum TermType{
        MIXED,
        SEGREGATED
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date term;
    private TermType termType;
    private SectorType sectorType;

    @Ignore
    public SectorTerm(String term, TermType termType) {
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
        sectorType = SectorType.GREEN;
    }

    // Constructor used by Room
    public SectorTerm(int id, Date term, TermType termType, SectorType sectorType) {
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

    //TODO extract outside and use getString from resources
    public String getTermType() {
        switch (termType) {
            case MIXED:
                return "zmieszane";
            case SEGREGATED:
                return "segregowane";
            default:
                return "UNKNOWN";
        }
    }

    public int getId() {
        return id;
    }

    public SectorType getSectorType() {
        return sectorType;
    }
}

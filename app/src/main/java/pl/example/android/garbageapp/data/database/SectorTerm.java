package pl.example.android.garbageapp.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pl.example.android.garbageapp.data.network.model.SectorData;
import pl.example.android.garbageapp.utilities.SectorTermsUtil;


@Entity(tableName = "sector_terms", indices = {@Index(value = {"sectorColor"})})
public class SectorTerm {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private Date term;
    private TermType termType;
    private SectorColor sectorColor;

    @Ignore
    public SectorTerm(String term, TermType termType, SectorColor sectorColor) {
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
        this.sectorColor = sectorColor;
    }

    // Constructor used by Room
    public SectorTerm(int id, Date term, TermType termType, SectorColor sectorColor) {
        this.id = id;
        this.term = term;
        this.termType = termType;
        this.sectorColor = sectorColor;
    }

    @Ignore
    public SectorTerm(SectorData sectorData, String color) {
        this.term = SectorTermsUtil.getTermDate(sectorData.getTerm());
        this.termType = TermType.toTermType(sectorData.getType());
        this.sectorColor = SectorColor.toSectorColor(color);
    }

    public String getTermString() {
        String dateFormat = "EEEE, d MMMM YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, new Locale("pl", "PL"));
        return simpleDateFormat.format(term);
    }

    //TODO extract outside and use getString from resources
    public String getTermTypeString() {
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

    public SectorColor getSectorColor() {
        return sectorColor;
    }

    public Date getTerm() {
        return term;
    }

    public TermType getTermType() {
        return termType;
    }
}

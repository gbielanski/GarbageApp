package pl.example.android.garbageapp.utilities;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pl.example.android.garbageapp.data.database.SectorColor;
import pl.example.android.garbageapp.data.database.SectorTerm;

import static pl.example.android.garbageapp.data.database.TermType.MIXED;
import static pl.example.android.garbageapp.data.database.TermType.SEGREGATED;

public class SectorTermsUtil {
    private static final String LOG_TAG = SectorTermsUtil.class.getSimpleName();

    public static List<SectorTerm> getFakeSectorTermsData() {
        ArrayList<SectorTerm> sectorTermsList = new ArrayList<>();
        sectorTermsList.add(new SectorTerm("2017.11.03", MIXED, SectorColor.GREEN));
        sectorTermsList.add(new SectorTerm("2017.11.06", SEGREGATED, SectorColor.GREEN));
        sectorTermsList.add(new SectorTerm("2017.11.17", MIXED, SectorColor.GREEN));
        sectorTermsList.add(new SectorTerm("2017.12.01", MIXED, SectorColor.GREEN));
        return sectorTermsList;
    }

    public static Date getTermDate(String stringDate) {
        String dateFormat = "yyyy.MM.dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, new Locale("pl", "PL"));
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error occurred while converting string date: " + e.getMessage());
            return null;
        }
    }
}

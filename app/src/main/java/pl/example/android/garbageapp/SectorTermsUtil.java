package pl.example.android.garbageapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorTerm.TermType;

import static pl.example.android.garbageapp.data.database.SectorTerm.TermType.*;

class SectorTermsUtil {
    public static List<SectorTerm> getFakeSectorTermsData() {
        ArrayList<SectorTerm> sectorTermsList = new ArrayList<>();
        sectorTermsList.add(new SectorTerm("2017.11.03", MIXED));
        sectorTermsList.add(new SectorTerm("2017.11.06", SEGREGATED));
        sectorTermsList.add(new SectorTerm("2017.11.17", MIXED));
        sectorTermsList.add(new SectorTerm("2017.12.01", MIXED));
        return sectorTermsList;
    }
}

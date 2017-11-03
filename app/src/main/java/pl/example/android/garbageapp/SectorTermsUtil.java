package pl.example.android.garbageapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import pl.example.android.garbageapp.data.database.SectorTerm;

class SectorTermsUtil {
    public static List<SectorTerm> getFakeSectorTermsData() {
        ArrayList<SectorTerm> sectorTermsList = new ArrayList<>();
        sectorTermsList.add(new SectorTerm("2017.11.03",SectorTerm.termTypeMixed));
        sectorTermsList.add(new SectorTerm("2017.11.06",SectorTerm.termTypeSegregated));
        sectorTermsList.add(new SectorTerm("2017.11.17",SectorTerm.termTypeMixed));
        sectorTermsList.add(new SectorTerm("2017.12.01",SectorTerm.termTypeMixed));
        return sectorTermsList;
    }
}

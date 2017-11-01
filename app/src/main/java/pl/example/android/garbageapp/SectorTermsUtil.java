package pl.example.android.garbageapp;

import java.util.ArrayList;
import java.util.List;

class SectorTermsUtil {
    public static List<SectorTerm> getFakeSectorTermsData() {
        ArrayList<SectorTerm> sectorTermsList = new ArrayList<>();
        sectorTermsList.add(new SectorTerm("piątek, 3 listopada 2017","zmieszane"));
        sectorTermsList.add(new SectorTerm("poniedziałek, 6 listopada 2017","segregowane"));
        sectorTermsList.add(new SectorTerm("piątek, 17 listopada 2017","zmieszane"));
        sectorTermsList.add(new SectorTerm("piątek, 1 grudzień 2017","zmieszane"));
        return sectorTermsList;
    }
}

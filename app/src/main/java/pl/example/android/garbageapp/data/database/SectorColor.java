package pl.example.android.garbageapp.data.database;

import java.util.HashMap;
import java.util.Map;


public enum SectorColor {
    UNSET(0) {
        @Override
        public String toString() {
            return "SEKTOR NIEZDEFINIOWANY";
        }
    },
    GREEN(1) {
        @Override
        public String toString() {
            return "SEKTOR I ZIELONY";
        }
    },
    BLUE(2) {
        @Override
        public String toString() {
            return "SEKTOR II NIEBIESKI";
        }
    },
    YELLOW(3) {
        @Override
        public String toString() {
            return "SEKTOR III ŻÓŁTY";
        }
    };

    private int sectorTypeValue;

    private static Map<Integer, SectorColor> map = new HashMap<>();

    static {
        for (SectorColor sectorColorEnum : SectorColor.values()) {
            map.put(sectorColorEnum.sectorTypeValue, sectorColorEnum);
        }
    }

    SectorColor(final int type) {
        sectorTypeValue = type;
    }

    public static SectorColor valueOf(int type) {
        return map.get(type);
    }

    public static int toInt(SectorColor sectorColorEnum) {
        return sectorColorEnum.sectorTypeValue;
    }

    public static SectorColor toSectorColor(String color) {
        switch (color) {
            case "blue" : return BLUE;
            case "green" : return GREEN;
            case "yellow" : return YELLOW;
            default: return UNSET;
        }
    }

    public abstract String toString();
}


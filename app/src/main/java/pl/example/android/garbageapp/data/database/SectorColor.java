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
    PINK(1) {
        @Override
        public String toString() {
            return "SEKTOR 1 RÓŻOWY";
        }
    },
    BLUE(2) {
        @Override
        public String toString() {
            return "SEKTOR 2 NIEBIESKI";
        }
    },
    YELLOW(3) {
        @Override
        public String toString() {
            return "SEKTOR 3 ŻÓŁTY";
        }
    },
    GREEN(4) {
        @Override
        public String toString() {
            return "SEKTOR 4 ZIELONY";
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
            case "pink" : return PINK;
            case "yellow" : return YELLOW;
            case "green" : return GREEN;
            default: return UNSET;
        }
    }

    public abstract String toString();
}


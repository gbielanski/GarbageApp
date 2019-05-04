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
    DEEP_PURPLE(2) {
        @Override
        public String toString() {
            return "SEKTOR 2 CIEMNO FIOLETOWY";
        }
    },
    GREEN(3) {
        @Override
        public String toString() {
            return "SEKTOR 3 ZIELONY";
        }
    },
    AMBER(4) {
        @Override
        public String toString() {
            return "SEKTOR 4 AMBER";
        }
    },
    ORANGE(5) {
        @Override
        public String toString() {
            return "SEKTOR 5 ORANGE";
        }
    },
    BLUE(30) {
        @Override
        public String toString() {
            return "SEKTOR 2 NIEBIESKI";
        }
    },
    YELLOW(40) {
        @Override
        public String toString() {
            return "SEKTOR 3 ŻÓŁTY";
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
            case "pink" : return PINK;
            case "deep_purple" : return DEEP_PURPLE;
            case "green" : return GREEN;
            case "amber" : return AMBER;
            case "orange" : return ORANGE;

            case "blue" : return BLUE;
            case "yellow" : return YELLOW;
            default: return UNSET;
        }
    }

    public abstract String toString();
}


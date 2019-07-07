package pl.example.android.garbageapp.data.database;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public enum SectorColor {
    UNSET(0) {
        @NonNull
        @Override
        public String toString() {
            return "REJON NIEZDEFINIOWANY";
        }
    },
    PINK(1) {
        @NonNull
        @Override
        public String toString() {
            return "REJON 1 RÓŻOWY";
        }
    },
    DEEP_PURPLE(2) {
        @NonNull
        @Override
        public String toString() {
            return "REJON 2 CIEMNO FIOLETOWY";
        }
    },
    GREEN(3) {
        @NonNull
        @Override
        public String toString() {
            return "REJON 3 ZIELONY";
        }
    },
    AMBER(4) {
        @NonNull
        @Override
        public String toString() {
            return "REJON 4 AMBER";
        }
    },
    ORANGE(5) {
        @NonNull
        @Override
        public String toString() {
            return "REJON 5 ORANGE";
        }
    },
    DEEP_ORANGE(6) {
        @NonNull
        @Override
        public String toString() {
            return "REJON 6 DEEP ORANGE";
        }
    },
    PURPLE(7) {
        @NonNull
        @Override
        public String toString() {
            return "REJON 7 PURPLE";
        }
    },
    INDIGO(8) {
        @NonNull
        @Override
        public String toString() {
            return "REJON 8 INDIGO";
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
            case "deep_orange" : return DEEP_ORANGE;
            case "purple" : return PURPLE;
            case "indigo" : return INDIGO;
            default: return UNSET;
        }
    }

    public abstract String toString();
}


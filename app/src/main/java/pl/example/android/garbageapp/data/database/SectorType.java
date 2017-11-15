package pl.example.android.garbageapp.data.database;

import java.util.HashMap;
import java.util.Map;


public enum SectorType {
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

    private static Map<Integer, SectorType> map = new HashMap<>();

    static {
        for (SectorType sectorTypeEnum : SectorType.values()) {
            map.put(sectorTypeEnum.sectorTypeValue, sectorTypeEnum);
        }
    }

    SectorType(final int type) {
        sectorTypeValue = type;
    }

    public static SectorType valueOf(int type) {
        return map.get(type);
    }

    public static int toInt(SectorType sectorTypeEnum) {
        return sectorTypeEnum.sectorTypeValue;
    }

    public abstract String toString();
}


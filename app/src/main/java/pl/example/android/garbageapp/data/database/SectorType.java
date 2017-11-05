package pl.example.android.garbageapp.data.database;

import java.util.HashMap;
import java.util.Map;


public enum SectorType {
    GREEN(1),
    BLUE(2),
    YELLOW(3);

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
}


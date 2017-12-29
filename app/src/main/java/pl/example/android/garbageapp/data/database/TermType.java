package pl.example.android.garbageapp.data.database;

import java.util.HashMap;
import java.util.Map;

public enum TermType {
        MIXED (1),
        SEGREGATED (2),
        BIO (3),
        UNKNOWN(4);

    private int termTypeValue;

    private static Map<Integer, TermType> map = new HashMap<>();

    static {
        for (TermType termTypeEnum : TermType.values()) {
            map.put(termTypeEnum.termTypeValue, termTypeEnum);
        }
    }

    TermType(final int type) {
        this.termTypeValue = type;
    }

    public static TermType valueOf(int type) {
        return map.get(type);
    }

    public static int toInt(TermType termTypeEnum) {
        return termTypeEnum.termTypeValue;
    }

    public static TermType toTermType(String termTypeString) {
        switch (termTypeString) {
            case "MIXED" : return MIXED;
            case "SEGREGATED" : return SEGREGATED;
            case "BIO" : return BIO;
            default: return UNKNOWN;
        }
    }
}

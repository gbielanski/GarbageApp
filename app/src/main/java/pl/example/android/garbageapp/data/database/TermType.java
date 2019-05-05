package pl.example.android.garbageapp.data.database;

import java.util.HashMap;
import java.util.Map;

public enum TermType {
        MIXED (1),
        PLASTIC (2),
        PAPER (3),
        GLASS (4),
        BIO (5),
        UNKNOWN(6);

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
            case "PLASTIC" : return PLASTIC;
            case "PAPER" : return PAPER;
            case "GLASS" : return GLASS;
            case "BIO" : return BIO;
            default: return UNKNOWN;
        }
    }
}

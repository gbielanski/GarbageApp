package pl.example.android.garbageapp.data.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static int toInt(SectorColor sectorColor) {
        return SectorColor.toInt(sectorColor);
    }

    @TypeConverter
    public static SectorColor toSectorTyp(int sector) {
        return SectorColor.valueOf(sector);
    }

    @TypeConverter
    public static int toInt(TermType termType) {
        return TermType.toInt(termType);
    }

    @TypeConverter
    public static TermType toTermTyp(int termType) {
        return TermType.valueOf(termType);
    }
}

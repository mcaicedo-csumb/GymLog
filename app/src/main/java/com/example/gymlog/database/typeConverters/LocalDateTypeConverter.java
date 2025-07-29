package com.example.gymlog.database;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTypeConverter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime date) {
        return date.format(formatter);
    }

    @TypeConverter
    public static LocalDateTime toLocalDateTime(String dateString) {
        return LocalDateTime.parse(dateString, formatter);
    }
}

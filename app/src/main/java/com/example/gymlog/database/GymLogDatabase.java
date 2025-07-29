package com.example.gymlog.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.gymlog.database.entities.GymLog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {GymLog.class}, version = 1, exportSchema = false)
@TypeConverters({com.example.gymlog.database.LocalDateTypeConverter.class})
public abstract class GymLogDatabase extends RoomDatabase {
    public static final String GYM_LOG_TABLE = "gym_log_table";
    private static final String DB_NAME = "GYM_LOG_DATABASE";
    private static GymLogDatabase database;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static GymLogDatabase getDatabase(final Context context) {
        if (database == null) {
            synchronized (GymLogDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder(
                            context.getApplicationContext(),
                            GymLogDatabase.class,
                            DB_NAME
                    ).build();
                }
            }
        }
        return database;
    }

    public abstract GymLogDAO gymLogDAO();
}

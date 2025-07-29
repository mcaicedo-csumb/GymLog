package com.example.gymlog.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gymlog.database.entities.GymLog;

import java.util.List;

@Dao
public interface GymLogDAO {

    @Insert
    void insert(GymLog gymLog);

    @Query("SELECT * FROM gym_log_table ORDER BY date DESC")
    List<GymLog> getAllLogs();
}

package com.example.gymlog.database;

import android.app.Application;
import android.util.Log;

import com.example.gymlog.MainActivity;
import com.example.gymlog.database.entities.GymLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GymLogRepository {
    private final GymLogDAO gymLogDAO;
    private ArrayList<GymLog> allLogs;
    private static GymLogRepository repository;

    public static GymLogRepository getRepository(Application application) {
        if (repository != null) return repository;

        Future<GymLogRepository> future = GymLogDatabase.databaseWriteExecutor.submit(() ->
                new GymLogRepository(application));

        try {
            repository = future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "Problem getting GymLogRepository, thread error.");
        }

        return repository;
    }

    private GymLogRepository(Application application) {
        GymLogDatabase db = GymLogDatabase.getDatabase(application);
        this.gymLogDAO = db.gymLogDAO();
        this.allLogs = new ArrayList<>(gymLogDAO.getAllLogs());
    }

    public ArrayList<GymLog> getAllLogs() {
        return allLogs;
    }

    public void insertGymLog(GymLog gymLog) {
        GymLogDatabase.databaseWriteExecutor.execute(() -> {
            gymLogDAO.insert(gymLog);
            allLogs = new ArrayList<>(gymLogDAO.getAllLogs());
        });
    }
}

/**
 * Author: Maria Camila Caicedo
 * Date: 7/30/2025
 * <p>
 * handles insertions and queries to Room db
 * connects GymLogDAO and UserDAO to the app
 */

package com.example.gymlog.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.gymlog.MainActivity;
import com.example.gymlog.database.entities.GymLog;
import com.example.gymlog.database.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GymLogRepository {

    private final GymLogDAO gymLogDAO;
    private final UserDAO userDAO;

    private ArrayList<GymLog> allLogs;

    private static GymLogRepository repository;

    private GymLogRepository(Application application) {
        GymLogDatabase db = GymLogDatabase.getDatabase(application);
        this.gymLogDAO = db.gymLogDAO();
        this.userDAO = db.userDAO();
        this.allLogs = (ArrayList<GymLog>) this.gymLogDAO.getAllRecords();
    }

    /**
     * returns repo instance
     */
    public static GymLogRepository getRepository(Application application) {
        if (repository != null) return repository;

        Future<GymLogRepository> future = GymLogDatabase.databaseWriteExecutor.submit(
                () -> new GymLogRepository(application)
        );

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "Error getting GymLogRepository.");
        }
        return null;
    }

    public ArrayList<GymLog> getAllLogs() {
        Future<ArrayList<GymLog>> future = GymLogDatabase.databaseWriteExecutor.submit(
                () -> (ArrayList<GymLog>) gymLogDAO.getAllRecords()
        );
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.TAG, "Error fetching logs.");
        }
        return null;
    }

    public void insertGymLog(GymLog gymLog) {
        GymLogDatabase.databaseWriteExecutor.execute(() -> {
            gymLogDAO.insert(gymLog);
        });
    }

    public void insertUser(User... user) {
        GymLogDatabase.databaseWriteExecutor.execute(() -> {
            userDAO.insert(user);
        });
    }

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }

    public LiveData<List<GymLog>> getAllLogsByUserIdLiveData(int loggedInUserId) {
        return gymLogDAO.getRecordsetUserIdLiveData(loggedInUserId);
    }

    /**
     * @deprecated use LiveData version instead
     */
    @Deprecated
    public ArrayList<GymLog> getAllLogsByUserId(int loggedInUserId) {
        Future<ArrayList<GymLog>> future = GymLogDatabase.databaseWriteExecutor.submit(
                () -> (ArrayList<GymLog>) gymLogDAO.getRecordsetUserId(loggedInUserId)
        );
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.TAG, "Error fetching logs for user.");
        }
        return null;
    }
}

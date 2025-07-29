package com.example.gymlog.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.gymlog.database.GymLogDatabase;

import java.time.LocalDateTime;

@Entity(tableName = GymLogDatabase.GYM_LOG_TABLE)
public class GymLog {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String exercise;
    private double weight;
    private int reps;

    @ColumnInfo(name = "date")
    private LocalDateTime date;

    public GymLog(String exercise, double weight, int reps) {
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.date = LocalDateTime.now();
    }

    // Cleaned up version of toString (from video 6)
    @NonNull
    @Override
    public String toString() {
        return exercise + '\n' +
                "weight: " + weight + '\n' +
                "reps: " + reps + '\n' +
                "date: " + date.toString() + '\n' +
                "-=-=-=-=-=-=\n";
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GymLog gymLog = (GymLog) o;

        return id == gymLog.id &&
                Double.compare(weight, gymLog.weight) == 0 &&
                reps == gymLog.reps &&
                exercise.equals(gymLog.exercise) &&
                date.equals(gymLog.date);
    }
}

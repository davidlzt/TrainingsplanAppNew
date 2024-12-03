package databasehandler;

import databaseconnection.DatabaseConnection;
import models.Device;
import models.Exercise;
import models.Muscle;

import java.sql.*;
import java.util.List;

public class ExerciseHandler {

    public void insertExercise(Exercise exercise) {
        String sql = "INSERT INTO exercises (name, difficulty, image, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, exercise.getName());
            pstmt.setString(2, exercise.getDifficulty());
            pstmt.setString(3, exercise.getImage());
            pstmt.setString(4, exercise.getDescription());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int exerciseId = generatedKeys.getInt(1);
                    insertExerciseMuscle(exerciseId, exercise.getTargetMuscles());
                    insertExerciseDevice(exerciseId, exercise.getDevices());
                }
            }
            System.out.println("Übung erfolgreich hinzugefügt!");
        } catch (SQLException e) {
            System.out.println("Fehler beim Hinzufügen der Übung: " + e.getMessage());
        }
    }

    private void insertExerciseMuscle(int exerciseId, List<Muscle> muscles) {
        String sql = "INSERT INTO exercise_muscle (exercise_id, muscle_name, muscle_description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Muscle muscle : muscles) {
                pstmt.setInt(1, exerciseId);
                pstmt.setString(2, muscle.getName());  // Name des Muskels
                pstmt.setString(3, muscle.getDescription());  // Beschreibung des Muskels
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Hinzufügen der Übung-Muskeln-Verbindung: " + e.getMessage());
        }
    }

    private void insertExerciseDevice(int exerciseId, List<Device> devices) {
        String sql = "INSERT INTO exercise_device (exercise_id, device_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Device device : devices) {
                pstmt.setInt(1, exerciseId);
                pstmt.setInt(2, device.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Hinzufügen der Übung-Gerät-Verbindung: " + e.getMessage());
        }
    }
}

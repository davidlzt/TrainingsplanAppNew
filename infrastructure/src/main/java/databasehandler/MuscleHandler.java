package databasehandler;

import databaseconnection.DatabaseConnection;
import models.Muscle;

import java.sql.*;

public class MuscleHandler {

    public void insertMuscle(Muscle muscle) {
        String sql = "INSERT INTO muscles (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, muscle.getName());
            pstmt.executeUpdate();
            System.out.println("Muskel erfolgreich hinzugefügt!");
        } catch (SQLException e) {
            System.out.println("Fehler beim Hinzufügen des Muskels: " + e.getMessage());
        }
    }
}

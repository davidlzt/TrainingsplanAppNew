package databaseconnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {
        createUserTable();
        createMuscleTable();
        createDeviceTable();
        createExerciseTable();
        createExerciseTargetMusclesTable();
        createExerciseDeviceTable();
    }

    private static void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(255), "
                + "email VARCHAR(255), "
                + "password VARCHAR(255), "
                + "gewicht INT, "
                + "age INT, "
                + "groesse INT, "
                + "geschlecht ENUM('male', 'female'), "
                + "role ENUM('user', 'admin', 'vip', 'moderator') DEFAULT 'user')";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabelle 'users' wurde erstellt oder existiert bereits.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle 'users': " + e.getMessage());
        }
    }

    private static void createMuscleTable() {
        String sql = "CREATE TABLE IF NOT EXISTS muscles ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(255))";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabelle 'muscles' wurde erstellt oder existiert bereits.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle 'muscles': " + e.getMessage());
        }
    }

    private static void createDeviceTable() {
        String sql = "CREATE TABLE IF NOT EXISTS device ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(255), "
                + "description TEXT, "
                + "type VARCHAR(50), "
                + "image VARCHAR(255))";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabelle 'devices' wurde erstellt oder existiert bereits.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle 'devices': " + e.getMessage());
        }
    }

    private static void createExerciseTable() {
        String sql = "CREATE TABLE IF NOT EXISTS exercises ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(255), "
                + "difficulty VARCHAR(50), "
                + "image VARCHAR(255), "
                + "description TEXT)";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabelle 'exercises' wurde erstellt oder existiert bereits.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle 'exercises': " + e.getMessage());
        }
    }

    private static void createExerciseTargetMusclesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS exercise_target_muscles ("
                + "exercise_id BIGINT NOT NULL, "
                + "muscle_id INT NOT NULL, "
                + "PRIMARY KEY (exercise_id, muscle_id), "
                + "FOREIGN KEY (exercise_id) REFERENCES exercises(id), "
                + "FOREIGN KEY (muscle_id) REFERENCES muscles(id))";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabelle 'exercise_target_muscles' wurde erstellt oder existiert bereits.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle 'exercise_target_muscles': " + e.getMessage());
        }
    }

    private static void createExerciseDeviceTable() {
        String sql = "CREATE TABLE IF NOT EXISTS exercise_device ("
                + "exercise_id BIGINT NOT NULL, "
                + "device_id BIGINT NOT NULL, "
                + "PRIMARY KEY (exercise_id, device_id), "
                + "FOREIGN KEY (exercise_id) REFERENCES exercises(id), "
                + "FOREIGN KEY (device_id) REFERENCES device(id))";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabelle 'exercise_device' wurde erstellt oder existiert bereits.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle 'exercise_device': " + e.getMessage());
        }
    }
}

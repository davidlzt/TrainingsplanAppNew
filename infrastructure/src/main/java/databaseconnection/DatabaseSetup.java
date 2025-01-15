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
    }

    private static void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(255), "
                + "email VARCHAR(255), "
                + "password VARCHAR(255), "
                + "weight DOUBLE, "
                + "age INT, "
                + "height DOUBLE, "
                + "sex VARCHAR(10), "
                + "role VARCHAR(32))";

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
        String sql = "CREATE TABLE IF NOT EXISTS devices ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
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
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
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
}

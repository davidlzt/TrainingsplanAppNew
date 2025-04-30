package databasehandler;

import databaseconnection.DatabaseConnection;
import entitys.Device;

import java.sql.*;

public class DeviceHandler {

    public void insertDevice(Device device) {
        String sql = "INSERT INTO devices (name, description, type, image) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, device.getName());
            pstmt.setString(2, device.getDescription());
            pstmt.executeUpdate();
            System.out.println("Gerät erfolgreich hinzugefügt!");
        } catch (SQLException e) {
            System.out.println("Fehler beim Hinzufügen des Geräts: " + e.getMessage());
        }
    }
}

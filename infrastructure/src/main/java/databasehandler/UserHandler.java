package databasehandler;

import databaseconnection.DatabaseConnection;
import entitys.User;
import valueobjects.Email;
import valueobjects.Weight;
import valueobjects.Height;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserHandler {

    public void insertUser(String username, String email, String password, double gewicht, int age, double groesse, String geschlecht, String role) {
        if (!isValidRole(role)) {
            System.out.println("Ungültige Rolle! Zulässige Werte: admin, moderator, user, vip.");
            return;
        }

        String sql = "INSERT INTO users (username, email, password, gewicht, age, groesse, geschlecht, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setDouble(4, gewicht);
            pstmt.setInt(5, age);
            pstmt.setDouble(6, groesse);
            pstmt.setString(7, geschlecht);
            pstmt.setString(8, role);
            pstmt.executeUpdate();
            System.out.println("User erfolgreich registriert!");
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen des Users: " + e.getMessage());
        }
    }

    private boolean isValidRole(String role) {
        List<String> validRoles = List.of("admin", "moderator", "user", "vip");
        return validRoles.contains(role);
    }

    public boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Login-Validierung fehlgeschlagen: " + e.getMessage());
            return false;
        }
    }

    public boolean isAdmin(String username) {
        String sql = "SELECT role FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    return "admin".equalsIgnoreCase(role);
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Admin-Überprüfung: " + e.getMessage());
        }
        return false;
    }

    public void updateUserRole(String adminUsername, int userId, String newRole) {
        if (!isAdmin(adminUsername)) {
            System.out.println("Nur Admins können die Rolle ändern.");
            return;
        }

        if (!isValidRole(newRole)) {
            System.out.println("Ungültige Rolle.");
            return;
        }

        String sql = "UPDATE users SET role = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newRole);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
            System.out.println("Rolle erfolgreich geändert.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Aktualisieren der Rolle: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users ORDER BY role";
        List<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String emailString = rs.getString("email");
                String password = rs.getString("password");
                double gewichtValue = rs.getDouble("gewicht");
                int age = rs.getInt("age");
                double groesseValue = rs.getDouble("groesse");
                String geschlecht = rs.getString("geschlecht");
                String role = rs.getString("role");

                // Erstelle die Value Objects
                Email email = new Email(emailString);
                Weight weight = new Weight(gewichtValue);
                Height height = new Height(groesseValue);

                // Erstelle den User und füge ihn der Liste hinzu
                User user = new User(id, username, email, password, weight, age, height, geschlecht, role);
                user.setId(id);
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen der Nutzer: " + e.getMessage());
        }

        return users;
    }
}

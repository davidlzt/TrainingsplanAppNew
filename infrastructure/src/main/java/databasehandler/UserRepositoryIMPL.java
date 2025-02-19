package databasehandler;

import databaseconnection.DatabaseConnection;
import entitys.User;
import repositories.UserRepository;
import valueobjects.Email;
import valueobjects.Height;
import valueobjects.Role;
import valueobjects.Weight;

import java.sql.*;

public class UserRepositoryIMPL implements UserRepository {

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO users (id, username, email, password, weight, age, height, sex, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, user.getId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, (user.getEmail()).getAdress());
            pstmt.setString(4, user.getPassword());
            pstmt.setDouble(5, user.getWeight().getValue());
            pstmt.setInt(6, user.getAge());
            pstmt.setDouble(7, user.getHeight().getValue());
            pstmt.setString(8, user.getSex());
            pstmt.setString(9, user.getRole().getRolename());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fehler beim Speichern des Benutzers: " + e.getMessage());
        }
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("email");
                    double weight = rs.getDouble("weight");
                    int height = rs.getInt("height");
                    String role = rs.getString("role");

                    Email userEmail = new Email(email);
                    Weight userWeight = new Weight(weight);
                    Height userHeight = new Height(height);
                    Role userRole = Role.valueOf(role.toUpperCase());

                    return new User(
                            rs.getLong("id"),
                            rs.getString("username"),
                            userEmail,
                            rs.getString("password"),
                            userWeight,
                            rs.getInt("age"),
                            userHeight,
                            rs.getString("sex"),
                            userRole 
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen des Benutzers: " + e.getMessage());
        }
        return null;
    }
    @Override
    public void updateUserRole(Long userId, Role newRole) {
        String sql = "UPDATE users SET role = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newRole.getRolename());
            pstmt.setLong(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Aktualisieren der Benutzerrolle: " + e.getMessage());
        }
    }

    @Override
    public Long findMaxId() {
        String sql = "SELECT MAX(id) FROM users";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen der maximalen ID: " + e.getMessage());
        }
        return 0L;
    }

}

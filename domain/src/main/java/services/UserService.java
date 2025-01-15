package services;

import entitys.User;
import repositories.UserRepository;
import valueobjects.Email;
import valueobjects.Height;
import valueobjects.Role;
import valueobjects.Weight;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(Long id, String username, Email email, String password, Weight weight, int age, Height height, String sex, Role role) {
        User user = new User(id, username, email, password, weight, age, height, sex, role);
        userRepository.insertUser(user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public void changeUserRole(Long userId, Role newRole) {
        userRepository.updateUserRole(userId, newRole);
    }

    public void changePassword(Long userId, String newPassword) {
        User user = userRepository.getUserByUsername(userId.toString());  // Get by userId
        if (user != null) {
            user.changePassword(newPassword);
            userRepository.insertUser(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}

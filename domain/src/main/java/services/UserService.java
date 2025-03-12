package services;

import entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserRepository;
import valueobjects.Role;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }


    @Transactional
    public void changeUserRole(Long userId, Role newRole) {
        userRepository.updateUserRole(userId, newRole);
    }

    @Transactional
    public void changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setPassword(newPassword);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
    public List<User> getAllUsers() {
        List<User> allUser = userRepository.findAll();
        return allUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

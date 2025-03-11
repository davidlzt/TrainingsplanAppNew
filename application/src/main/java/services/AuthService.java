package services;

import entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public boolean verifyUser(String username, String password) {
        return userService.authenticateUser(username, password);
    }

    public void registerUser(User user) {
        userService.registerUser(user);
    }
}
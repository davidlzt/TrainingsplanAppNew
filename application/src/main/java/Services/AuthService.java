package Services;

import services.UserService;
import valueobjects.Email;
import valueobjects.Height;
import valueobjects.Role;
import valueobjects.Weight;

public class AuthService {
    private UserService userService;


    public boolean verifyUser(String username, String password){
        return userService.authenticateUser(username, password);
    }

    public void registerUser(Long id, String username, Email email, String password, Weight weight, int age, Height height, String sex, Role role){
        userService.registerUser(id, username, email, password, weight, age, height, sex ,role);
    }
}

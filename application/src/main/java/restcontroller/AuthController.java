package restcontroller;

import Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repositories.UserRepository;
import valueobjects.Email;
import valueobjects.Height;
import valueobjects.Role;
import valueobjects.Weight;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private AuthService authService;
    private UserRepository userRepository;

    @PostMapping("/login/{username}/{password}")
    public boolean login(@PathVariable String username, @PathVariable String password) {
        return authService.verifyUser(username, password);
    }

    @GetMapping("/register/next-id")
    public Long getNextUserId() {
        Long maxId = userRepository.findMaxId();
        return (maxId != null) ? maxId + 1 : 1;
    }


    @GetMapping("/register/{id}/{username}/{email}/{password}/{weight}/{age}/{height}/{sex}/{role}")
    public void register(@PathVariable Long id, @PathVariable String username, @PathVariable Email email, @PathVariable String password,
                         @PathVariable Weight weight, @PathVariable int age, @PathVariable Height height, @PathVariable String sex,
                         @PathVariable Role role) {
        authService.registerUser(id, username, email, password, weight, age, height, sex, role);
    }
}

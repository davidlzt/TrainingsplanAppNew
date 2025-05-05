package restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.UserRepository;
import entitys.User;
import restcontroller.util.LoginRequest;
import services.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register/nextid")
    public Long getNextUserId() {
        Long maxId = userRepository.findMaxId();
        System.out.println("Max ID found: " + maxId);
        return (maxId != null) ? maxId + 1 : 1;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            if (userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword())) {
                System.out.println("Login successful");
                return ResponseEntity.ok(Map.of("success", true));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("success", false, "message", "Invalid credentials"));
            }
        } catch (Exception e) {
            System.err.println("Error occurred during login: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "An error occurred during login"));
        }
    }
}

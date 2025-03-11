package restcontroller;

import services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.UserRepository;
import entitys.User;
import restcontroller.util.LoginRequest;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register/nextid")
    public Long getNextUserId() {
        Long maxId = userRepository.findMaxId();
        System.out.println("Max ID found: " + maxId);
        return (maxId != null) ? maxId + 1 : 1;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        authService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (authService.verifyUser(loginRequest.getUsername(), loginRequest.getPassword())) {
            System.out.println("Login successful");
            return ResponseEntity.ok(Map.of("success", true));

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", false, "message", "Invalid credentials"));
        }
    }
}

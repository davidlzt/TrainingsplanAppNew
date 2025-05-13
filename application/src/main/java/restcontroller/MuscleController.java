package restcontroller;

import entitys.Muscle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.MuscleService;

import java.util.List;

@RestController
@RequestMapping("/api/muscles")
@CrossOrigin("*")
public class MuscleController {

    private final MuscleService muscleService;

    @Autowired
    public MuscleController(MuscleService muscleService) {
        this.muscleService = muscleService;
    }

    @GetMapping
    public ResponseEntity<List<Muscle>> getAllMuscles() {
        try {
            List<Muscle> muscles = muscleService.getAllMuscles();
            return ResponseEntity.ok(muscles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}

package restcontroller;

import entitys.Muscle;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Muscle> getAllMuscles() {
        return muscleService.getAllMuscles();
    }
}

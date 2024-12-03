package restcontroller;

import models.Exercise;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/exercises")
    public List<Exercise> getAllExercises() {
        return List.of(null);
    }

    @PostMapping("/exercises")
    public void addExercise(@RequestBody Exercise exercise) {
    }
}


package restcontroller;

import entitys.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restcontroller.util.ExerciseDTO;
import restcontroller.util.MusclesAndDevicesRequest;
import services.ExerciseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping(produces = "application/json")
    public List<ExerciseDTO> getAllExercises() {
        return exerciseService.getAllExercises().stream()
                .map(exercise -> new ExerciseDTO(exercise.getId(), exercise.getName(), exercise.getDescription()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exercise = exerciseService.getExerciseById(id);
        return exercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        Long maxId = exerciseService.getMaxExerciseId();
        exercise.setId(maxId + 1);

        Exercise newExercise = exerciseService.addExercise(exercise);
        return new ResponseEntity<>(newExercise, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{exerciseId}/muscles-devices")
    public ResponseEntity<Void> addMusclesAndDevicesToExercise(
            @PathVariable Long exerciseId,
            @RequestBody MusclesAndDevicesRequest request) {

        exerciseService.addMusclesAndDevicesToExercise(exerciseId, request.getMuscles(), request.getDevices());
        return ResponseEntity.noContent().build();
    }


}

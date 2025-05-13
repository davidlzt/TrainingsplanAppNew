package restcontroller;

import entitys.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repositories.MuscleRepository;
import restcontroller.util.ExerciseDTO;
import restcontroller.util.ExerciseMapper;
import restcontroller.util.ExerciseRequestDTO;
import restcontroller.util.MusclesAndDevicesRequest;
import services.ExerciseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class ExerciseController {

    private final ExerciseService exerciseService;

    private final ExerciseMapper exerciseMapper;
    public ExerciseController(ExerciseService exerciseService, ExerciseMapper exerciseMapper) {
        this.exerciseMapper = exerciseMapper;
        this.exerciseService = exerciseService;
    }

    @GetMapping(produces = "application/json")
    public List<ExerciseDTO> getAllExercises() {
        return exerciseService.getAllExercises().stream()
                .map(exercise -> new ExerciseDTO(exercise.getId(), exercise.getName(), exercise.getDescription()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exercise = Optional.ofNullable(exerciseService.getExerciseById(id));
        return exercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody ExerciseRequestDTO exerciseRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body((Exercise) bindingResult.getAllErrors());
        }
        Exercise exercise = exerciseMapper.toEntity(exerciseRequestDTO);
        Exercise savedExercise = exerciseService.addExercise(exercise);
        return new ResponseEntity<>(savedExercise, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        Optional<Exercise> exercise = Optional.ofNullable(exerciseService.getExerciseById(id));
        if (exercise.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
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

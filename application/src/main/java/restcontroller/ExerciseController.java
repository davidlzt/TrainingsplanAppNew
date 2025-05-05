package restcontroller;

import entitys.Device;
import entitys.Exercise;
import entitys.Muscle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repositories.DeviceRepository;
import repositories.MuscleRepository;
import restcontroller.util.ExerciseDTO;
import restcontroller.util.ExerciseRequestDTO;
import restcontroller.util.MusclesAndDevicesRequest;
import services.DeviceService;
import services.ExerciseService;
import services.MuscleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    public ExerciseController(MuscleRepository muscleRepository) {
    }

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
    public ResponseEntity<Exercise> addExercise(@RequestBody ExerciseRequestDTO exerciseRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Exercise newExercise = ExerciseDTO.getExercise(exerciseRequestDTO);
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

package restcontroller;

import entitys.Trainingsplan;
import entitys.TrainingsplanExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ExerciseRepository;
import repositories.TrainingsplanRepository;
import restcontroller.util.TrainingsplanRequestDTO;
import services.TrainingsplanService;
import entitys.Exercise;
import valueobjects.Trainingsziel;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/trainingsplan")
public class TrainingsplanController {

    private final TrainingsplanService trainingsplanService;

    @Autowired
    public TrainingsplanController(TrainingsplanService trainingsplanService, ExerciseRepository exerciseRepository, TrainingsplanRepository trainingsplanRepository) {
        this.trainingsplanService = trainingsplanService;
    }
    @GetMapping
    public List<Trainingsplan> getAllTrainingPlans() {
        return trainingsplanService.getAllTrainingPlans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainingsplan> getTrainingsplan(@PathVariable Long id) {
        Optional<Trainingsplan> trainingsplan = trainingsplanService.getTrainingsplanWithExercises(id);
        return trainingsplan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public Trainingsplan createTrainingsplan(@RequestBody TrainingsplanRequestDTO request) {
        Trainingsplan trainingsplan = new Trainingsplan();
        trainingsplan.setName(request.getName());
        trainingsplan.setDescription(request.getDescription());
        trainingsplan.setGoal(Trainingsziel.fromLabel(request.getGoal()));
        trainingsplan.setTrainingDays(request.getTrainingDays());

        return trainingsplanService.createTrainingsplanWithExercisesAndStrategy(trainingsplan, request.getExerciseIds());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTrainingsplan(@PathVariable Long id,
                                                    @RequestBody Trainingsplan trainingsplan) {
        trainingsplanService.updateTrainingsplan(id,
                trainingsplan.getName(),
                trainingsplan.getDescription(),
                trainingsplan.getGoal().getLabel(),
                trainingsplan.getTrainingDays());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingsplan(@PathVariable Long id) {
        trainingsplanService.deleteTrainingsplan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/exercises")
    public List<Exercise> getExercisesForTrainingsplan(@PathVariable Long id) {
        return trainingsplanService.getExercisesForTrainingsplan(id);
    }

    @GetMapping("/{id}/training-frequency")
    public int getTrainingFrequencyForTrainingsplan(@PathVariable Long id) {
        return trainingsplanService.getTrainingFrequencyForTrainingsplan(id);
    }
}

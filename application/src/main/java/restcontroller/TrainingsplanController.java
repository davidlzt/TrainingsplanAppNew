package restcontroller;

import entitys.Trainingsplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.TrainingsplanService;
import entitys.Exercise;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/trainingsplan")
public class TrainingsplanController {

    private final TrainingsplanService trainingsplanService;

    @Autowired
    public TrainingsplanController(TrainingsplanService trainingsplanService) {
        this.trainingsplanService = trainingsplanService;
    }
    @GetMapping
    public List<Trainingsplan> getAllTrainingPlans() {
        List<Trainingsplan> plans = trainingsplanService.getAllTrainingPlans();
        System.out.println("Trainingspläne aus der Datenbank: " + plans);
        return plans;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainingsplan> getTrainingsplan(@PathVariable Long id) {
        Optional<Trainingsplan> trainingsplan = trainingsplanService.getTrainingsplanWithExercises(id);

        return trainingsplan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Trainingsplan> createTrainingsplan(@RequestBody Trainingsplan trainingsplan) {
        System.out.println("Empfangene Daten: " + trainingsplan);
        Trainingsplan savedPlan = trainingsplanService.createTrainingsplan(trainingsplan);
        return ResponseEntity.ok(savedPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTrainingsplan(@PathVariable Long id,
                                                    @RequestBody Trainingsplan trainingsplan) {
        trainingsplanService.updateTrainingsplan(id,
                trainingsplan.getName(),
                trainingsplan.getDescription(),
                trainingsplan.getGoal(),
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
        List<Exercise> exercises = trainingsplanService.getExercisesForTrainingsplan(id);
        System.out.println("Trainingspläne aus der Datenbank: " + exercises);
        return exercises;
    }

    @GetMapping("/{id}/training-frequency")
    public int getTrainingFrequencyForTrainingsplan(@PathVariable Long id) {
        int days = trainingsplanService.getTrainingFrequencyForTrainingsplan(id);
        System.out.println("Trainingsplan aus der Datenbank: " + days);
        return days;
    }

}

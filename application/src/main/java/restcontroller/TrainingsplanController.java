package restcontroller;

import entitys.Trainingsplan;
import entitys.TrainingsplanExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ExerciseRepository;
import repositories.TrainingsplanRepository;
import restcontroller.util.TrainingsplanRequestDTO;
import services.TrainingsplanService;
import entitys.Exercise;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/trainingsplan")
public class TrainingsplanController {

    private final TrainingsplanService trainingsplanService;
    private final ExerciseRepository exerciseRepository;
    private final TrainingsplanRepository trainingsplanRepository;

    @Autowired
    public TrainingsplanController(TrainingsplanService trainingsplanService, ExerciseRepository exerciseRepository, TrainingsplanRepository trainingsplanRepository) {
        this.trainingsplanService = trainingsplanService;
        this.exerciseRepository = exerciseRepository;
        this.trainingsplanRepository = trainingsplanRepository;
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
    public Trainingsplan createTrainingsplan(@RequestBody TrainingsplanRequestDTO request) {
        // Zuerst den Trainingsplan erstellen
        Trainingsplan trainingsplan = new Trainingsplan();
        trainingsplan.setName(request.getName());
        trainingsplan.setDescription(request.getDescription());
        trainingsplan.setGoal(request.getGoal());
        trainingsplan.setTrainingDays(request.getTrainingDays());

        // Übungen verarbeiten
        List<TrainingsplanExercise> trainingsplanExercises = new ArrayList<>();
        System.out.println("Empfangene Übung-IDs: " + request.getExerciseIds());

        if (request.getExerciseIds() != null && !request.getExerciseIds().isEmpty()) {
            for (Long exerciseId : request.getExerciseIds()) {
                Exercise exercise = exerciseRepository.findById(exerciseId)
                        .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + exerciseId));

                // TrainingsplanExercise hinzufügen
                TrainingsplanExercise trainingsplanExercise = new TrainingsplanExercise();
                trainingsplanExercise.setExercise(exercise);
                trainingsplanExercise.setTrainingsplan(trainingsplan);
                trainingsplanExercises.add(trainingsplanExercise);
            }
        } else {
            System.out.println("Keine Übungen übergeben.");
        }

        // Die Übungsliste dem Trainingsplan zuweisen
        trainingsplan.setTrainingsplanExercises(trainingsplanExercises);

        // Trainingsplan speichern
        Trainingsplan savedTrainingsplan = trainingsplanRepository.save(trainingsplan);

        // Jetzt TrainingsplanExercise speichern (wegen CascadeType.PERSIST und orphanRemoval = true wird das automatisch gehandhabt)

        return savedTrainingsplan;
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

package services;

import entitys.Exercise;
import entitys.Trainingsplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ExerciseRepository;
import repositories.TrainingsplanRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingsplanService {

    private final TrainingsplanRepository trainingsplanRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public TrainingsplanService(TrainingsplanRepository trainingsplanRepository, ExerciseRepository exerciseRepository) {
        this.trainingsplanRepository = trainingsplanRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public Trainingsplan createTrainingsplan(String name, String description, String goal, List<Integer> trainingDays, Map<Integer, Integer> selectedExercises) {
        List<Exercise> resolvedExercises = selectedExercises.values().stream()
                .map(exerciseId -> exerciseRepository.findById(Long.valueOf(exerciseId))
                        .orElseThrow(() -> new IllegalArgumentException("Ungültige Übungs-ID: " + exerciseId)))
                .collect(Collectors.toList());

        Trainingsplan trainingsplan = new Trainingsplan(
                name,
                description,
                goal,
                trainingDays,
                resolvedExercises
        );

        return trainingsplanRepository.save(trainingsplan);
    }

    public Trainingsplan getTrainingsplanById(Long id) {
        Optional<Trainingsplan> trainingsplan = trainingsplanRepository.findById(id);
        return trainingsplan.orElse(null);
    }

    public List<Trainingsplan> getAllTrainingsplaene() {
        return trainingsplanRepository.findAll();
    }

    public Trainingsplan updateTrainingsplan(Long id, String name, String description, String goal, List<Integer> trainingDays, Map<Integer, Integer> selectedExercises) {
        Optional<Trainingsplan> optionalPlan = trainingsplanRepository.findById(id);
        if (optionalPlan.isPresent()) {
            Trainingsplan trainingsplan = optionalPlan.get();
            trainingsplan.setName(name);
            trainingsplan.setDescription(description);
            trainingsplan.setGoal(goal);
            trainingsplan.setTrainingDays(trainingDays);

            List<Exercise> resolvedExercises = selectedExercises.values().stream()
                    .map(exerciseId -> exerciseRepository.findById(Long.valueOf(exerciseId))
                            .orElseThrow(() -> new IllegalArgumentException("Ungültige Übungs-ID: " + exerciseId)))
                    .collect(Collectors.toList());

            trainingsplan.setExercises(resolvedExercises);
            return trainingsplanRepository.save(trainingsplan);
        }
        return null;
    }

    public boolean deleteTrainingsplan(Long id) {
        if (trainingsplanRepository.existsById(id)) {
            trainingsplanRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

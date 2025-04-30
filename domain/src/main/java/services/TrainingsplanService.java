package services;

import entitys.Trainingsplan;
import entitys.Exercise;
import entitys.TrainingsplanExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ExerciseRepository;
import repositories.TrainingsplanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingsplanService {

    private final ExerciseRepository exerciseRepository;
    private final TrainingsplanRepository trainingsplanRepository;

    @Autowired
    public TrainingsplanService(ExerciseRepository exerciseRepository, TrainingsplanRepository trainingsplanRepository) {
        this.exerciseRepository = exerciseRepository;
        this.trainingsplanRepository = trainingsplanRepository;
    }

    public Optional<Trainingsplan> getTrainingsplanWithExercises(Long id) {
        Optional<Trainingsplan> trainingsplan = trainingsplanRepository.findById(id);

        if (trainingsplan.isPresent()) {
            Trainingsplan plan = trainingsplan.get();
            List<Exercise> exercises = plan.getTrainingsplanExercises()
                    .stream()
                    .map(TrainingsplanExercise::getExercise)
                    .toList();
        }

        return trainingsplan;
    }


    public List<Trainingsplan> getAllTrainingPlans() {
        return trainingsplanRepository.findAll();
    }

    public Trainingsplan createTrainingsplan(Trainingsplan trainingsplan) {
        List<TrainingsplanExercise> trainingsplanExercises = new ArrayList<>();

        for (Long exerciseId : trainingsplan.getExerciseIds()) {
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + exerciseId));

            if (exercise != null) {
                TrainingsplanExercise trainingsplanExercise = new TrainingsplanExercise();
                trainingsplanExercise.setExercise(exercise);
                trainingsplanExercise.setTrainingsplan(trainingsplan);
                trainingsplanExercises.add(trainingsplanExercise);
            } else {
                System.out.println("Übung mit ID " + exerciseId + " wurde nicht gefunden.");
            }
        }

        if (trainingsplanExercises.isEmpty()) {
            System.out.println("Keine Übungen zugeordnet!");
        }

        trainingsplan.setTrainingsplanExercises(trainingsplanExercises);
        return trainingsplanRepository.save(trainingsplan);
    }

    public void updateTrainingsplan(Long id, String name, String description, String goal, List<Long> trainingDays) {
        trainingsplanRepository.updateTrainingsplan(id, name, description, goal, trainingDays);
    }

    public void deleteTrainingsplan(Long id) {
        trainingsplanRepository.deleteById(id);
    }

    public List<Exercise> getExercisesForTrainingsplan(Long id) {
        Optional<Trainingsplan> trainingsplan = trainingsplanRepository.findById(id);

        if (trainingsplan.isPresent()) {
            List<TrainingsplanExercise> trainingsplanExercises = trainingsplan.get().getTrainingsplanExercises();
            List<Exercise> exercises = new ArrayList<>();

            for (TrainingsplanExercise tpExercise : trainingsplanExercises) {
                exercises.add(tpExercise.getExercise());
            }
            return exercises;
        }
        return new ArrayList<>();
    }


    public int getTrainingFrequencyForTrainingsplan(Long id) {
        Optional<Trainingsplan> trainingsplanOptional = trainingsplanRepository.findById(id);

        if (trainingsplanOptional.isPresent()) {
            Trainingsplan trainingsplan = trainingsplanOptional.get();

            return trainingsplan.getTrainingDays().size();
        }

        return 0;
    }

}

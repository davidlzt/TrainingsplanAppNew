package services;

import entitys.Device;
import entitys.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repositories.ExerciseRepository;
import entitys.Muscle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private static final Logger logger = LoggerFactory.getLogger(ExerciseService.class);

    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise addExercise(Exercise exercise) {
        if (exercise.getName() == null || exercise.getName().isEmpty()) {
            throw new IllegalArgumentException("Exercise name cannot be null or empty");
        }
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    public void addMusclesAndDevicesToExercise(Long exerciseId, List<Muscle> muscles, List<Device> devices) {
        Optional<Exercise> exerciseOpt = exerciseRepository.findById(exerciseId);
        if (exerciseOpt.isPresent()) {
            Exercise exercise = exerciseOpt.get();
            exercise.setTargetMuscles(muscles);

            if (devices != null && !devices.isEmpty()) {
                exercise.setDevices(devices);
            }

            exerciseRepository.save(exercise);
            logger.info("Muscles and devices added to exercise with ID {}", exerciseId);
        } else {
            logger.error("Exercise with ID {} not found", exerciseId);
            throw new RuntimeException("Exercise not found");
        }
    }

    public Long getMaxExerciseId() {
        return exerciseRepository.findTopByOrderByIdDesc()
                .map(Exercise::getId)
                .orElse(0L);
    }
}

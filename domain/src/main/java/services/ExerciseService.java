package services;

import entitys.Device;
import entitys.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repositories.ExerciseRepository;
import entitys.Muscle;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise addExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    public void addMusclesAndDevicesToExercise(Long exerciseId, List<Muscle> muscles, List<Device> devices) {
        Optional<Exercise> exerciseOpt = exerciseRepository.findById(exerciseId);
        if (exerciseOpt.isPresent()) {
            Exercise exercise = exerciseOpt.get();
            exercise.setTargetMuscles(muscles);
            exercise.setDevices(devices);
            exerciseRepository.save(exercise);
        }
    }
    public Long getMaxExerciseId() {
        List<Exercise> exercises = exerciseRepository.findAll(Sort.by(Sort.Order.desc("id")));
        if (!exercises.isEmpty()) {
            return exercises.get(0).getId();
        } else {
            return 0L;
        }
    }
}

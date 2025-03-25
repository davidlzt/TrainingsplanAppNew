package services;

import entitys.Trainingsplan;
import entitys.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TrainingsplanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingsplanService {

    private final TrainingsplanRepository trainingsplanRepository;

    @Autowired
    public TrainingsplanService(TrainingsplanRepository trainingsplanRepository) {
        this.trainingsplanRepository = trainingsplanRepository;
    }

    public Optional<Trainingsplan> getTrainingsplanWithExercises(Long id) {
        Optional<Trainingsplan> trainingsplan = trainingsplanRepository.findById(id);

        if (trainingsplan.isPresent()) {
            Trainingsplan plan = trainingsplan.get();
            List<Exercise> exercises = plan.getExercises();
        }

        return trainingsplan;
    }

    public List<Trainingsplan> getAllTrainingPlans() {
        return trainingsplanRepository.findAll();
    }
    public Trainingsplan createTrainingsplan(Trainingsplan trainingsplan) {
        return trainingsplanRepository.save(trainingsplan);
    }

    public void updateTrainingsplan(Long id, String name, String description, String goal, List<Integer> trainingDays) {
        trainingsplanRepository.updateTrainingsplan(id, name, description, goal, trainingDays);
    }

    public void deleteTrainingsplan(Long id) {
        trainingsplanRepository.deleteById(id);
    }
    public List<Exercise> getExercisesForTrainingsplan(Long id) {
        Optional<Trainingsplan> trainingsplan = trainingsplanRepository.findById(id);

        if (trainingsplan.isPresent()) {
            return trainingsplan.get().getExercises();
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

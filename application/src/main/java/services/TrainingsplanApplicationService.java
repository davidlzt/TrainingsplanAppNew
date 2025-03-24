package services;

import entitys.Exercise;
import entitys.Trainingsplan;
import repositories.ExerciseRepository;
import repositories.TrainingsplanRepository;
import restcontroller.util.TrainingsplanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.TrainingsplanService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingsplanApplicationService {

    private final TrainingsplanService trainingsplanService;
    private final TrainingsplanRepository trainingsplanRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public TrainingsplanApplicationService(TrainingsplanService trainingsplanService, TrainingsplanRepository trainingsplanRepository, ExerciseRepository exerciseRepository) {
        this.trainingsplanService = trainingsplanService;
        this.trainingsplanRepository = trainingsplanRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public Trainingsplan createTrainingsplanFromDTO(TrainingsplanDTO dto, Map<Integer, Integer> selectedExercises) {
        return trainingsplanService.createTrainingsplan(
                dto.getName(),
                dto.getDescription(),
                dto.getGoal(),
                dto.getTrainingDays(),
                selectedExercises
        );
    }

    public Trainingsplan getTrainingsplanById(Long id) {
        return trainingsplanService.getTrainingsplanById(id);
    }

    public List<Trainingsplan> getAllTrainingsplaene() {
        return trainingsplanService.getAllTrainingsplaene();
    }

    public Trainingsplan updateTrainingsplan(Long id, TrainingsplanDTO dto, Map<String, Integer> selectedExercises) {
        Map<Integer, Integer> convertedSelectedExercises = selectedExercises.entrySet().stream()
                .collect(Collectors.toMap(entry -> Integer.parseInt(entry.getKey()), Map.Entry::getValue));

        return trainingsplanService.updateTrainingsplan(
                id,
                dto.getName(),
                dto.getDescription(),
                dto.getGoal(),
                dto.getTrainingDays(),
                convertedSelectedExercises
        );
    }



    public boolean deleteTrainingsplan(Long id) {
        return trainingsplanService.deleteTrainingsplan(id);
    }
}

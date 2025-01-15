package services;

import entitys.Trainingsplan;
import entitys.Exercise;
import repositories.TrainingsplanRepository;
import valueobjects.Duration;

import java.util.List;

public class TrainingsplanService {

    private final TrainingsplanRepository trainingsplanRepository;

    public TrainingsplanService(TrainingsplanRepository trainingsplanRepository) {
        this.trainingsplanRepository = trainingsplanRepository;
    }

    public void createTrainingsplan(Long id, String name, String description, Duration duration, String goal, List<Exercise> exercises) {
        Trainingsplan trainingsplan = new Trainingsplan(id, name, description, duration, goal, exercises);
        trainingsplanRepository.insertTrainingsplan(trainingsplan);
    }


    public Trainingsplan getTrainingsplanById(Long id) {
        return trainingsplanRepository.getTrainingsplanById(id);
    }

    public List<Trainingsplan> getAllTrainingsplaene() {
        return trainingsplanRepository.getAllTrainingsplaene();
    }

    public void updateTrainingsplan(Long id, String name, String description, Duration duration, String goal, List<Exercise> exercises) {
        Trainingsplan trainingsplan = new Trainingsplan(id, name, description, duration, goal, exercises);
        trainingsplanRepository.updateTrainingsplan(trainingsplan);
    }

    public void deleteTrainingsplan(Long id) {
        trainingsplanRepository.deleteTrainingsplan(id);
    }

    public void addExerciseToTrainingsplan(Long trainingsplanId, Exercise exercise) {
        Trainingsplan trainingsplan = trainingsplanRepository.getTrainingsplanById(trainingsplanId);
        if (trainingsplan != null) {
            trainingsplan.addExercise(exercise);
            trainingsplanRepository.updateTrainingsplan(trainingsplan);
        } else {
            System.out.println("Trainingsplan mit ID " + trainingsplanId + " wurde nicht gefunden.");
        }
    }

    public void removeExerciseFromTrainingsplan(Long trainingsplanId, Exercise exercise) {
        Trainingsplan trainingsplan = trainingsplanRepository.getTrainingsplanById(trainingsplanId);
        if (trainingsplan != null) {
            trainingsplan.removeExercise(exercise);
            trainingsplanRepository.updateTrainingsplan(trainingsplan);
        } else {
            System.out.println("Trainingsplan mit ID " + trainingsplanId + " wurde nicht gefunden.");
        }
    }

    public void updateTrainingsplanDuration(Long trainingsplanId, Duration newDuration) {
        Trainingsplan trainingsplan = trainingsplanRepository.getTrainingsplanById(trainingsplanId);
        if (trainingsplan != null) {
            trainingsplan.setDuration(newDuration);
            trainingsplanRepository.updateTrainingsplan(trainingsplan);
        } else {
            System.out.println("Trainingsplan mit ID " + trainingsplanId + " wurde nicht gefunden.");
        }
    }

    public void updateTrainingsplanGoal(Long trainingsplanId, String newGoal) {
        Trainingsplan trainingsplan = trainingsplanRepository.getTrainingsplanById(trainingsplanId);
        if (trainingsplan != null) {
            trainingsplan.updateGoal(newGoal);
            trainingsplanRepository.updateTrainingsplan(trainingsplan);
        } else {
            System.out.println("Trainingsplan mit ID " + trainingsplanId + " wurde nicht gefunden.");
        }
    }
}

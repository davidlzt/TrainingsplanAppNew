package restcontroller.util;

import java.util.List;
import java.util.Map;

public class TrainingsplanDTO {

    private String name;
    private String description;
    private String goal;
    private List<Integer> trainingDays;
    private Map<String, Integer> selectedExercises;

    public TrainingsplanDTO(String name, String description, String goal, List<Integer> trainingDays, Map<String, Integer> selectedExercises) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.trainingDays = trainingDays;
        this.selectedExercises = selectedExercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public List<Integer> getTrainingDays() {
        return trainingDays;
    }

    public void setTrainingDays(List<Integer> trainingDays) {
        this.trainingDays = trainingDays;
    }

    public Map<String, Integer> getSelectedExercises() {
        return selectedExercises;
    }

    public void setSelectedExercises(Map<String, Integer> selectedExercises) {
        this.selectedExercises = selectedExercises;
    }
}

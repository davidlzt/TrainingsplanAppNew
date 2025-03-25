package restcontroller.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
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

}

package restcontroller.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class TrainingsplanRequestDTO {
    private String name;
    private String description;
    private String goal;
    private List<Long> trainingDays;
    private List<Long> exerciseIds;

    public TrainingsplanRequestDTO(String name, String description, String goal, List<Long> trainingDays, List<Long> exerciseIds) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.trainingDays = trainingDays;
        this.exerciseIds = exerciseIds;
    }

    public TrainingsplanRequestDTO() {}
}


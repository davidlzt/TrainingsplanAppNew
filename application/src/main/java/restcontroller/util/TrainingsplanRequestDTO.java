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

}

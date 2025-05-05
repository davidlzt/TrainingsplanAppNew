package restcontroller.util;

import entitys.Device;
import entitys.Muscle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ExerciseRequestDTO {

    private final String name;
    private final String description;
    private final List<Long> muscleIds;
    private final List<Device> deviceIds;

    public ExerciseRequestDTO(String name, String description, List<Long> muscleIds, List<Device> deviceIds) {
        this.name = name;
        this.description = description;
        this.muscleIds = muscleIds;
        this.deviceIds = deviceIds;
    }

}

package restcontroller.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDTO {
    private Long id;
    private String name;
    private String description;

    public ExerciseDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}

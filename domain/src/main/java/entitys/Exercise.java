package entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import valueobjects.Muscle;

import java.util.List;

@Entity
@Getter
@Setter
public class Exercise {

    @Id
    private Long id;
    private String name;
    private String difficulty;
    private String image;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "exercise_target_muscles", joinColumns = @JoinColumn(name = "exercise_id"))
    private List<Muscle> targetMuscles;

    private String description;

    @OneToMany
    @JoinTable(
            name = "exercise_device",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    private List<Device> devices;

    public Exercise() {
    }

    public Exercise(String name, String difficulty, String image, List<Muscle> targetMuscles, String description, List<Device> devices) {
        this.name = name;
        this.difficulty = difficulty;
        this.image = image;
        this.targetMuscles = targetMuscles;
        this.description = description;
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", image='" + image + '\'' +
                ", targetMuscles=" + targetMuscles +
                ", description='" + description + '\'' +
                ", devices=" + devices +
                '}';
    }
}

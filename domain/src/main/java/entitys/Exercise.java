package entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String difficulty;
    private String image;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Muscle> targetMuscles;

    @OneToMany
    @JoinTable(
            name = "exercise_device",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    @JsonIgnore
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

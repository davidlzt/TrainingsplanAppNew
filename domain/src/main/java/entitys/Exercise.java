package entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String name;
    private String description;


    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TrainingsplanExercise> trainingsplanExercises = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "exercise_muscle",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_id")
    )
    @JsonIgnore
    private List<Muscle> targetMuscles;

    @ManyToMany
    @JoinTable(
            name = "exercise_device",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    private List<Device> devices = new ArrayList<>();

    public Exercise(String name, List<Muscle> targetMuscles, String description, List<Device> devices) {
        this.name = name;
        this.targetMuscles = targetMuscles != null ? targetMuscles : new ArrayList<>();
        this.description = description;
        this.devices = devices != null ? devices : new ArrayList<>();
    }
}
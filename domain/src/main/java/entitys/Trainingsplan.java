package entitys;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repositories.ExerciseRepository;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Trainingsplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Setter
    private String description;

    @Setter
    private String goal;

    @Setter
    @ElementCollection
    private List<Long> trainingDays = new ArrayList<>();;

    @Setter
    @OneToMany(mappedBy = "trainingsplan", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference
    private List<TrainingsplanExercise> trainingsplanExercises = new ArrayList<>();


    public Trainingsplan(String name, String description, String goal, List<Long> trainingDays) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.trainingDays = trainingDays;
    }

    public List<Long> getExerciseIds() {
        List<Long> exerciseIds = new ArrayList<>();
        for (TrainingsplanExercise te : trainingsplanExercises) {
            exerciseIds.add(te.getExercise().getId());
        }
        return exerciseIds;
    }

    public void addExercise(Exercise exercise) {
        TrainingsplanExercise te = new TrainingsplanExercise(this, exercise);
        trainingsplanExercises.add(te);
    }

    public void removeExercise(Exercise exercise) {
        trainingsplanExercises.removeIf(te -> te.getExercise().equals(exercise));
    }

}

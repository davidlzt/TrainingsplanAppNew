package entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
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
    private List<Integer> trainingDays;

    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Exercise> exercises = new ArrayList<>();


    public Trainingsplan() {
    }

    public Trainingsplan(String name, String description, String goal, List<Integer> trainingDays, List<Exercise> exercises) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.trainingDays = trainingDays;
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        if (!exercises.contains(exercise)) {
            exercises.add(exercise);
        }
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }
}

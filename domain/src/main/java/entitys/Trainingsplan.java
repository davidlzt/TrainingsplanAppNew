package entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import valueobjects.Duration;

import java.util.List;

@Entity
public class Trainingsplan {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Setter
    private Duration duration;

    @Getter
    @Setter
    private String goal;

    @Getter
    @OneToMany
    private List<Exercise> exercises;

    public Trainingsplan() {
    }

    public Trainingsplan(Long id, String name, String description, Duration duration, String goal, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.goal = goal;
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

    public void updateGoal(String newGoal) {
        this.goal = newGoal;
    }

    public int getDurationInWeeks() {
        return duration.getDurationInWeeks();
    }

    public void printTrainingsplan() {
        System.out.println("Trainingsplan: " + name);
        System.out.println("Ziel: " + goal);
        System.out.println("Beschreibung: " + description);
        System.out.println("Dauer: " + duration.getDurationInWeeks() + " Wochen");
        System.out.println("Übungen:");
        for (Exercise exercise : exercises) {
            System.out.println(exercise.getName());
        }
    }
}

package entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TrainingsplanExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainingsplan_id")
    @JsonBackReference
    private Trainingsplan trainingsplan;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public TrainingsplanExercise(Trainingsplan trainingsplan, Exercise exercise) {
        this.trainingsplan = trainingsplan;
        this.exercise = exercise;
    }
}
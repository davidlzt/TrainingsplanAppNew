package entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Muscle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public Muscle() {
    }

    public Muscle(String name, String description, Exercise exercise) {
        this.name = name;
        this.description = description;
        this.exercise = exercise;
    }

    @Override
    public String toString() {
        return "Muscle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", exercise=" + exercise +
                '}';
    }
}

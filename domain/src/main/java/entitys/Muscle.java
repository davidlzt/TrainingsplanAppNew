package entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Muscle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "targetMuscles")
    @JsonIgnore
    private List<Exercise> exercises;


    public Muscle() {
    }

    public Muscle(String name, String description, List<Exercise> exercise) {
        this.name = name;
        this.description = description;
        this.exercises =  exercise;
    }

    public Muscle(String name, String description) {
        this.name = name;
        this.description = description;
        this.exercises = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Muscle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\''+
                '}';
    }
}

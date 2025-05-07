package entitys;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import valueobjects.Trainingsziel;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private Trainingsziel goal;

    @Setter
    @ElementCollection
    private List<Long> trainingDays = new ArrayList<>();;

    @Setter
    @OneToMany(mappedBy = "trainingsplan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TrainingsplanExercise> trainingsplanExercises = new ArrayList<>();


    public void setId(long l) {
        this.id = l;
    }
}

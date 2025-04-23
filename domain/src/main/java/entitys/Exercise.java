package entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToMany
    @JoinTable(
            name = "exercise_device",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    @JsonManagedReference
    private List<Device> device;


    public Exercise() {}

    public Exercise(String name, String difficulty, String image, List<Muscle> targetMuscles, String description, List<Device> device) {
        this.name = name;
        this.difficulty = difficulty;
        this.image = image;
        this.targetMuscles = targetMuscles;
        this.description = description;
        this.device = device;
    }
}

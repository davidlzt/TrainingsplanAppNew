package entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "device")
@Getter
@Setter
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String type;
    private String image;

    @ManyToMany(mappedBy = "device", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Exercise> exercises;

    public Device() {}

    public Device(String name, String description, String type, String image) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

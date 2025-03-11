package entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "device")
@Getter
@Setter
public class Device {

    @Id
    private Long id;

    private String name;
    private String description;
    private String type;
    private String image;

    public Device() {
    }

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

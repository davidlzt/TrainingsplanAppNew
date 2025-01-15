package entitys;

import valueobjects.Muscle;

import java.util.List;

public class Exercise {
    private Long id;
    private String name;
    private String difficulty;
    private String image;
    private List<Muscle> targetMuscles;
    private String description;
    private List<Device> devices;

    public Exercise(String name, String difficulty, String image, List<Muscle> targetMuscles, String description, List<Device> devices) {
        this.name = name;
        this.difficulty = difficulty;
        this.image = image;
        this.targetMuscles = targetMuscles;
        this.description = description;
        this.devices = devices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Muscle> getTargetMuscles() {
        return targetMuscles;
    }

    public void setTargetMuscles(List<Muscle> targetMuscles) {
        this.targetMuscles = targetMuscles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", image='" + image + '\'' +
                ", targetMuscles=" + targetMuscles +
                ", description='" + description + '\'' +
                ", devices=" + devices +
                '}';
    }
}

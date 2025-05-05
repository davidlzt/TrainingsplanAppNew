package restcontroller.util;

import entitys.Device;
import entitys.Exercise;
import entitys.Muscle;
import lombok.Getter;
import lombok.Setter;
import repositories.MuscleRepository;
import services.ExerciseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class ExerciseDTO {
    private Long id;
    private String name;
    private String description;

    private static MuscleRepository muscleRepository;

    private static ExerciseService exerciseService;
    public ExerciseDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public static Exercise getExercise(ExerciseRequestDTO exerciseRequestDTO) {
        Exercise exercise = new Exercise();
        exercise.setName(exerciseRequestDTO.getName());
        exercise.setDescription(exerciseRequestDTO.getDescription());
        List<Device> devices = exerciseRequestDTO.getDeviceIds();
        if (devices == null) {
            devices = new ArrayList<>();
        }
        devices.forEach(device -> device.getExercises().add(exercise));
        List<Long> muscleIds = exerciseRequestDTO.getMuscleIds();
        if (muscleIds == null) {
            muscleIds = new ArrayList<>();
        }
        List<Long> filteredMuscleIds = muscleIds.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Muscle> muscles = muscleRepository.findAllById(filteredMuscleIds);
        exercise.setDevices(devices);
        exercise.setTargetMuscles(muscles);

        return exerciseService.addExercise(exercise);
    }
}

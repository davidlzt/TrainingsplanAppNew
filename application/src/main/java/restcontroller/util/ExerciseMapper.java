package restcontroller.util;

import entitys.Device;
import entitys.Exercise;
import entitys.Muscle;
import org.springframework.stereotype.Component;
import repositories.MuscleRepository;

import java.util.List;
import java.util.Objects;

@Component
public class ExerciseMapper {

    private final MuscleRepository muscleRepository;

    public ExerciseMapper(MuscleRepository muscleRepository) {
        this.muscleRepository = muscleRepository;
    }

    public Exercise toEntity(ExerciseRequestDTO dto) {
        Exercise exercise = new Exercise();
        exercise.setName(dto.getName());
        exercise.setDescription(dto.getDescription());

        List<Device> devices = dto.getDeviceIds() != null ? dto.getDeviceIds() : List.of();
        devices.forEach(device -> device.getExercises().add(exercise));

        List<Long> muscleIds = dto.getMuscleIds() != null
                ? dto.getMuscleIds().stream().filter(Objects::nonNull).toList()
                : List.of();

        List<Muscle> muscles = muscleRepository.findAllById(muscleIds);

        exercise.setDevices(devices);
        exercise.setTargetMuscles(muscles);

        return exercise;
    }
}

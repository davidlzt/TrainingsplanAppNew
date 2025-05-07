package strategies;

import entitys.Exercise;
import java.util.List;
import java.util.stream.Collectors;

public class FullBodyStrategy implements TrainingsplanStrategy {
    @Override
    public List<Exercise> selectExercises(List<Exercise> allExercises) {
        return allExercises.stream()
                .distinct()
                .limit(6)
                .collect(Collectors.toList());
    }
}

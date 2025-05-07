package strategies;

import entitys.Exercise;
import java.util.List;
import java.util.stream.Collectors;

public class FatLossStrategy implements TrainingsplanStrategy {
    @Override
    public List<Exercise> selectExercises(List<Exercise> allExercises) {
        return allExercises.stream()
                .limit(8)
                .collect(Collectors.toList());
    }
}

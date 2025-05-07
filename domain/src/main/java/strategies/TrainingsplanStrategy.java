package strategies;

import entitys.Exercise;
import java.util.List;

public interface TrainingsplanStrategy {
    List<Exercise> selectExercises(List<Exercise> allExercises);
}
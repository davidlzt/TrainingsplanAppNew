package strategies;

import org.springframework.stereotype.Component;

@Component
public class TrainingsplanStrategyFactory {

    public TrainingsplanStrategy getStrategy(String goal) {
        return switch (goal.toLowerCase()) {
            case "fullbody" -> new FullBodyStrategy();
            case "fatloss" -> new FatLossStrategy();
            case "musclefocus" -> new MuscleFocusStrategy();
            default -> new FullBodyStrategy();
        };
    }
}

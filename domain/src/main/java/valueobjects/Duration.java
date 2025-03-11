package valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Duration {
    private int durationInWeeks;

    public Duration(int durationInWeeks) {
        if (durationInWeeks <= 0) {
            throw new IllegalArgumentException("Dauer muss größer als 0 sein.");
        }
        this.durationInWeeks = durationInWeeks;
    }

    public Duration() {

    }

    @Override
    public String toString() {
        return durationInWeeks + " Wochen";
    }
}

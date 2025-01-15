package valueobjects;

public class Duration {
    private int durationInWeeks;

    public Duration(int durationInWeeks) {
        if (durationInWeeks <= 0) {
            throw new IllegalArgumentException("Dauer muss größer als 0 sein.");
        }
        this.durationInWeeks = durationInWeeks;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    @Override
    public String toString() {
        return durationInWeeks + " Wochen";
    }
}

package valueobjects;

import java.util.Objects;

public class Height {
    private final double value;

    public Height(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Größe muss größer als 0 sein.");
        }
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Height height = (Height) o;
        return Double.compare(height.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + " cm";
    }
}

package valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;
@Getter
@Embeddable
public class Weight {
    private double value;

    public Weight(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Gewicht muss größer als 0 sein.");
        }
        this.value = value;
    }

    public Weight() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return Double.compare(weight.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + " kg";
    }
}

package valueobjects;

import lombok.Getter;

@Getter
public enum Trainingsziel {
    MUSKELAUFBAU("Muskelaufbau"),
    GANZKÖRPER("Ganzkörper"),
    FETTABBAU("Fettabbau");

    private final String label;

    Trainingsziel(String label) {
        this.label = label;
    }

    public static Trainingsziel fromLabel(String label) {
        for (Trainingsziel z : values()) {
            if (z.label.trim().equalsIgnoreCase(label.trim())) {
                return z;
            }
        }
        throw new IllegalArgumentException("Unbekanntes Trainingsziel: " + label);
    }
}

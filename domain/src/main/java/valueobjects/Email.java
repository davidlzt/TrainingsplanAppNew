package valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private final String adress;
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Email(String adress) {
        if (!EMAIL_PATTERN.matcher(adress).matches()) {
            throw new IllegalArgumentException("Ungültige E-Mail-Adresse.");
        }
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(adress, email.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adress);
    }

    @Override
    public String toString() {
        return adress;
    }
}

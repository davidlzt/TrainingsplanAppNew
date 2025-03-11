package entitys;

import jakarta.persistence.*;
import lombok.*;
import valueobjects.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email"))
    private Email email;

    private String password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "gewicht"))
    private Weight weight;

    private int age;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "groesse"))
    private Height height;

    @Enumerated(EnumType.STRING)
    @Column(name = "geschlecht")
    private Gender geschlecht;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public void changePassword(String newPassword) {
        if (newPassword == null || newPassword.length() < 8) {
            throw new IllegalArgumentException("Das Passwort muss mindestens 8 Zeichen lang sein.");
        }
        this.password = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import valueobjects.Email;
import valueobjects.Role;
import valueobjects.Weight;
import valueobjects.Height;

import java.util.Objects;

@Entity
public class User {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Embedded
    private Email email;
    private String password;
    @Embedded
    private Weight weight;
    private int age;
    @Getter
    @Embedded
    private Height height;
    @Getter
    private String sex;
    private Role role;

    public User(Long id,String username, Email email, String password, Weight weight, int age, Height height, String sex, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.weight = weight;
        this.age = age;
        this.height = height;
        this.sex = sex;
        this.role = role;
    }

    protected User() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Email getEmail() { return email; }

    public String getPassword() {
        return password;
    }

    public Weight getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public Height getHeight() {
        return height;
    }

    public String getSex() {
        return sex;
    }

    public Role getRole() {
        return role;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email=" + email +
                ", gewicht=" + weight +
                ", age=" + age +
                ", groesse=" + height +
                ", sex='" + sex + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}

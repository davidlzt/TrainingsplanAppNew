package repositories;

import entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import valueobjects.Role;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT MAX(u.id) FROM User u")
    Long findMaxId();

    List<User> findAll();

    @Override
    @NonNull
    User save(User user);

    default void updateUserRole(Long userId, Role newRole) {
        User user = findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setRole(newRole);
        save(user);
    }
}

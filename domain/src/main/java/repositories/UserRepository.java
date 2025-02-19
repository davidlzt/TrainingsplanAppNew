
package repositories;

import entitys.User;
import valueobjects.Role;

public interface UserRepository {
    void insertUser(User user);
    User getUserByUsername(String username);
    void updateUserRole(Long userId, Role newRole);
    Long findMaxId();

}

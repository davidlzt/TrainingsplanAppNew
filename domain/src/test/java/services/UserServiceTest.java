package services;

import entitys.User;
import valueobjects.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import repositories.UserRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser() {
        Email email = new Email("test@example.com");
        Weight weight = new Weight(75);
        Height height = new Height(175);
        User user = new User(null, "testUser", email, "password", weight, 25, height, Gender.MALE, Role.USER);

        when(userRepository.save(user)).thenReturn(user);

        userService.registerUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testAuthenticateUser() {
        String username = "testUser";
        String password = "password";
        Email email = new Email("test@example.com");
        Weight weight = new Weight(75);
        Height height = new Height(175);
        User user = new User(1L, username, email, password, weight, 25, height, Gender.MALE, Role.USER);

        when(userRepository.findByUsername(username)).thenReturn(user);

        boolean authenticated = userService.authenticateUser(username, password);
        assertTrue(authenticated);
    }

    @Test
    public void testAuthenticateUser_InvalidPassword() {
        String username = "testUser";
        String password = "wrongPassword";
        Email email = new Email("test@example.com");
        Weight weight = new Weight(75);
        Height height = new Height(175);
        User user = new User(1L, username, email, "password", weight, 25, height, Gender.MALE, Role.USER);

        when(userRepository.findByUsername(username)).thenReturn(user);

        boolean authenticated = userService.authenticateUser(username, password);
        assertFalse(authenticated);
    }

    @Test
    public void testChangePassword() {
        Long userId = 1L;
        String newPassword = "newPassword123";
        Email email = new Email("test@example.com");
        Weight weight = new Weight(75);
        Height height = new Height(175);
        User user = new User(userId, "testUser", email, "password", weight, 25, height, Gender.MALE, Role.USER);

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        userService.changePassword(userId, newPassword);
        assertEquals(newPassword, user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testChangePassword_InvalidPassword() {
        Long userId = 1L;
        String newPassword = "short";
        Email email = new Email("test@example.com");
        Weight weight = new Weight(75);
        Height height = new Height(175);
        User user = new User(userId, "testUser", email, "password", weight, 25, height, Gender.MALE, Role.USER);

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> userService.changePassword(userId, newPassword));
    }

    @Test
    public void testChangeUserRole() {
        Long userId = 1L;
        Role newRole = Role.ADMIN;
        Email email = new Email("test@example.com");
        Weight weight = new Weight(75);
        Height height = new Height(175);
        User user = new User(userId, "testUser", email, "password", weight, 25, height, Gender.MALE, Role.USER);

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        userService.changeUserRole(userId, newRole);
        assertEquals(newRole, user.getRole());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}

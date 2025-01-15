package services;

import entitys.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.UserRepository;
import valueobjects.Email;
import valueobjects.Height;
import valueobjects.Role;
import valueobjects.Weight;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
        mockUser = new User(1L, "testuser", new Email("test@example.com"), "password123", new Weight(70.5), 25, new Height(175), "male", Role.USER);
    }

    @Test
    public void testRegisterUser() {
        doNothing().when(userRepository).insertUser(mockUser);

        userService.registerUser(1L, "testuser", new Email("test@example.com"), "password123", new Weight(70.5), 25, new Height(175), "male", Role.USER);

        verify(userRepository, times(1)).insertUser(mockUser);
    }


    @Test
    public void testAuthenticateUser_Success() {
        when(userRepository.getUserByUsername("testuser")).thenReturn(mockUser);

        boolean isAuthenticated = userService.authenticateUser("testuser", "password123");

        assertTrue(isAuthenticated);
    }

    @Test
    public void testAuthenticateUser_Failure() {
        when(userRepository.getUserByUsername("testuser")).thenReturn(mockUser);

        boolean isAuthenticated = userService.authenticateUser("testuser", "wrongpassword");

        assertFalse(isAuthenticated);
    }

    @Test
    public void testChangeUserRole() {
        doNothing().when(userRepository).updateUserRole(1L, Role.ADMIN);

        userService.changeUserRole(1L, Role.ADMIN);

        verify(userRepository, times(1)).updateUserRole(1L, Role.ADMIN);
    }


    @Test
    public void testChangePassword_Success() {
        when(userRepository.getUserByUsername("testuser")).thenReturn(mockUser);
        doNothing().when(userRepository).insertUser(mockUser);

        userService.changePassword(1L, "newpassword123");

        assertEquals("newpassword123", mockUser.getPassword());
    }


    @Test
    public void testChangePassword_UserNotFound() {
        when(userRepository.getUserByUsername("testuser")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.changePassword(1L, "newpassword123");
        });
    }
}

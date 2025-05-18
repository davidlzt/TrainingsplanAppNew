package services;

import entitys.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.UserRepository;
import valueobjects.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
    }

    @Test
    void testRegisterUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.registerUser(user);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testAuthenticateUser() {
        when(userRepository.findByUsername("testuser")).thenReturn(user);

        boolean isAuthenticated = userService.authenticateUser("testuser", "password");

        assertTrue(isAuthenticated);
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testChangeUserRoleWithSave() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setRole(Role.USER);
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        userService.changeUserRole(1L, Role.ADMIN);
        verify(userRepository, times(1)).save(any(User.class));
        assertEquals(Role.ADMIN, mockUser.getRole());
    }

    @Test
    void testChangeUserRoleWithUpdateUserRole() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setRole(Role.USER);
        mockUser.setUsername("testuser");
        mockUser.setPassword("password");
        mockUser.setEmail(new Email("test@web.de"));
        mockUser.setWeight(new Weight(100));
        mockUser.setAge(22);
        mockUser.setHeight(new Height(189));
        mockUser.setGeschlecht(Gender.MALE);
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        userService.changeUserRole(1L, Role.ADMIN);

        verify(userRepository, times(1)).save(mockUser);

        assertEquals(Role.ADMIN, mockUser.getRole());
    }

    @Test
    void testChangePassword() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.changePassword(1L, "newpassword");

        assertEquals("newpassword", user.getPassword());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals(user.getUsername(), users.getFirst().getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        boolean result = userService.deleteUser(1L);
        assertTrue(result);
        verify(userRepository, times(1)).deleteById(1L);

    }
}

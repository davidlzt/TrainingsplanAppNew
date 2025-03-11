package services;

import entitys.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.UserRepository;
import valueobjects.*;

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
        mockUser = new User(1L, "testuser", new Email("test@example.com"), "password123", new Weight(70.5), 25, new Height(175), Gender.MALE, Role.USER);
    }




    @Test
    public void testChangeUserRole() {
        doNothing().when(userRepository).updateUserRole(1L, Role.ADMIN);

        userService.changeUserRole(1L, Role.ADMIN);

        verify(userRepository, times(1)).updateUserRole(1L, Role.ADMIN);
    }


}

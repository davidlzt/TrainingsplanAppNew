package services;

import entitys.Muscle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.MuscleRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MuscleServiceTest {

    @Mock
    private MuscleRepository muscleRepository;

    @InjectMocks
    private MuscleService muscleService;

    private Muscle muscle;

    @BeforeEach
    void setUp() {
        muscle = new Muscle();
        muscle.setId(1L);
        muscle.setName("Biceps");
    }

    @Test
    void testGetAllMuscles() {
        when(muscleRepository.findAll()).thenReturn(Arrays.asList(muscle));

        List<Muscle> muscles = muscleService.getAllMuscles();

        assertNotNull(muscles);
        assertFalse(muscles.isEmpty());
        assertEquals(1, muscles.size());
        assertEquals(muscle.getName(), muscles.get(0).getName());
        verify(muscleRepository, times(1)).findAll();
    }
}

package services;

import entitys.Exercise;
import entitys.Device;
import entitys.Muscle;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import repositories.ExerciseRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseService exerciseService;

    @Test
    public void testAddExercise() {
        Exercise exercise = new Exercise();
        when(exerciseRepository.save(exercise)).thenReturn(exercise);

        Exercise result = exerciseService.addExercise(exercise);
        assertEquals(exercise, result);
    }

    @Test
    public void testGetAllExercises() {
        Exercise exercise1 = new Exercise();
        Exercise exercise2 = new Exercise();
        when(exerciseRepository.findAll()).thenReturn(Arrays.asList(exercise1, exercise2));

        var exercises = exerciseService.getAllExercises();
        assertNotNull(exercises);
        assertEquals(2, exercises.size());
    }

    @Test
    public void testGetExerciseById() {
        Exercise exercise = new Exercise();
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));

        Optional<Exercise> result = exerciseService.getExerciseById(1L);
        assertTrue(result.isPresent());
        assertEquals(exercise, result.get());
    }

    @Test
    public void testDeleteExercise() {
        doNothing().when(exerciseRepository).deleteById(1L);

        exerciseService.deleteExercise(1L);
        verify(exerciseRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testAddMusclesAndDevicesToExercise() {
        Exercise exercise = new Exercise();
        Muscle muscle = new Muscle();
        Device device = new Device();
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));

        exerciseService.addMusclesAndDevicesToExercise(1L, List.of(muscle), List.of(device));

        verify(exerciseRepository, times(1)).save(exercise);
    }
}

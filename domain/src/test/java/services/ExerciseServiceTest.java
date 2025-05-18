package services;

import entitys.Device;
import entitys.Exercise;
import entitys.Muscle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.ExerciseRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseService exerciseService;

    private Exercise exercise;

    @BeforeEach
    void setUp() {
        exercise = new Exercise();
        exercise.setId(1L);
        exercise.setName("Push Up");
    }

    @Test
    void testAddExercise() {
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(exercise);

        Exercise createdExercise = exerciseService.addExercise(exercise);

        assertNotNull(createdExercise);
        assertEquals(exercise.getName(), createdExercise.getName());
        verify(exerciseRepository, times(1)).save(any(Exercise.class));
    }

    @Test
    void testGetAllExercises() {
        when(exerciseRepository.findAll()).thenReturn(Arrays.asList(exercise));

        var exercises = exerciseService.getAllExercises();

        assertNotNull(exercises);
        assertFalse(exercises.isEmpty());
        assertEquals(1, exercises.size());
        assertEquals(exercise.getName(), exercises.get(0).getName());
        verify(exerciseRepository, times(1)).findAll();
    }

    @Test
    void testGetExerciseById() {
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));

        Optional<Exercise> foundExercise = Optional.ofNullable(exerciseService.getExerciseById(1L));

        assertTrue(foundExercise.isPresent());
        assertEquals(exercise.getName(), foundExercise.get().getName());
        verify(exerciseRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteExercise() {
        doNothing().when(exerciseRepository).deleteById(1L);

        exerciseService.deleteExercise(1L);

        verify(exerciseRepository, times(1)).deleteById(1L);
    }

    @Test
    void testAddMusclesAndDevicesToExercise() {
        Muscle muscle = new Muscle();
        muscle.setName("Chest");
        Device device = new Device();
        device.setName("Dumbbell");

        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(exercise);

        exerciseService.addMusclesAndDevicesToExercise(1L, Arrays.asList(muscle), Arrays.asList(device));

        assertEquals(1, exercise.getTargetMuscles().size());
        assertEquals(1, exercise.getDevices().size());
        assertEquals(muscle.getName(), exercise.getTargetMuscles().get(0).getName());
        assertEquals(device.getName(), exercise.getDevices().get(0).getName());

        verify(exerciseRepository, times(1)).findById(1L);
        verify(exerciseRepository, times(1)).save(any(Exercise.class));
    }

    @Test
    void testGetMaxExerciseId() {
        Exercise exercise2 = new Exercise();
        exercise2.setId(2L);

        when(exerciseRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(exercise2));

        Long maxId = exerciseService.getMaxExerciseId();

        assertEquals(2L, maxId);
        verify(exerciseRepository, times(1)).findTopByOrderByIdDesc();
    }

    @Test
    void testGetMaxExerciseIdWhenNoExercises() {
        when(exerciseRepository.findTopByOrderByIdDesc()).thenReturn(Optional.empty());

        Long maxId = exerciseService.getMaxExerciseId();

        assertEquals(0L, maxId);
        verify(exerciseRepository, times(1)).findTopByOrderByIdDesc();
    }

}

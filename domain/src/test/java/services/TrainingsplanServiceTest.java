package services;

import entitys.Exercise;
import entitys.Trainingsplan;
import entitys.TrainingsplanExercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.ExerciseRepository;
import repositories.TrainingsplanRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainingsplanServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private TrainingsplanRepository trainingsplanRepository;

    @InjectMocks
    private TrainingsplanService trainingsplanService;

    private Trainingsplan trainingsplan;
    private Exercise exercise;

    @BeforeEach
    void setUp() {
        trainingsplan = new Trainingsplan();
        trainingsplan.setId(1L);
        trainingsplan.setName("Full Body Plan");

        exercise = new Exercise();
        exercise.setId(1L);
        exercise.setName("Push Up");
    }

    @Test
    void testGetTrainingsplanWithExercises() {
        TrainingsplanExercise tpe = new TrainingsplanExercise();
        tpe.setExercise(exercise);
        tpe.setTrainingsplan(trainingsplan);
        trainingsplan.setTrainingsplanExercises(Arrays.asList(tpe));

        when(trainingsplanRepository.findById(1L)).thenReturn(Optional.of(trainingsplan));

        Optional<Trainingsplan> result = trainingsplanService.getTrainingsplanWithExercises(1L);

        assertTrue(result.isPresent());
        assertEquals(trainingsplan.getName(), result.get().getName());
        assertEquals(1, result.get().getTrainingsplanExercises().size());
        verify(trainingsplanRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateTrainingsplanWithExercises() {
        Trainingsplan newPlan = new Trainingsplan();
        newPlan.setName("Full Body Plan");
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));
        when(trainingsplanRepository.save(any(Trainingsplan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<Long> exerciseIds = List.of(1L);
        Trainingsplan createdPlan = trainingsplanService.createTrainingsplanWithExercises(newPlan, exerciseIds);

        assertNotNull(createdPlan);
        assertEquals("Full Body Plan", createdPlan.getName());
        assertEquals(1, createdPlan.getTrainingsplanExercises().size());
        verify(exerciseRepository, times(1)).findById(1L);
        verify(trainingsplanRepository, times(1)).save(any(Trainingsplan.class));
    }

    @Test
    void testDeleteTrainingsplan() {
        doNothing().when(trainingsplanRepository).deleteById(1L);

        trainingsplanService.deleteTrainingsplan(1L);

        verify(trainingsplanRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetExercisesForTrainingsplan() {
        TrainingsplanExercise tpe = new TrainingsplanExercise();
        tpe.setExercise(exercise);
        tpe.setTrainingsplan(trainingsplan);
        trainingsplan.setTrainingsplanExercises(Arrays.asList(tpe));

        when(trainingsplanRepository.findById(1L)).thenReturn(Optional.of(trainingsplan));

        List<Exercise> exercises = trainingsplanService.getExercisesForTrainingsplan(1L);

        assertNotNull(exercises);
        assertEquals(1, exercises.size());
        assertEquals(exercise.getName(), exercises.get(0).getName());
        verify(trainingsplanRepository, times(1)).findById(1L);
    }
}

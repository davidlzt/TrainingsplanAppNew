package services;

import entitys.Trainingsplan;
import entitys.Exercise;
import repositories.TrainingsplanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import valueobjects.Duration;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrainingsplanServiceTest {

    private TrainingsplanRepository trainingsplanRepository;
    private TrainingsplanService trainingsplanService;
    private Trainingsplan mockTrainingsplan;
    private Exercise mockExercise;
    private Duration mockDuration;

    @BeforeEach
    public void setUp() {
        trainingsplanRepository = mock(TrainingsplanRepository.class);

        trainingsplanService = new TrainingsplanService(trainingsplanRepository);

        mockTrainingsplan = new Trainingsplan(1L, "Test Plan", "Test Description", new Duration(4), "Fitness Goal", new ArrayList<>());
        mockExercise = new Exercise("Push Up", "Medium", "image.jpg", new ArrayList<>(), "Push up description", new ArrayList<>());
        mockDuration = new Duration(4);
    }

    @Test
    public void testCreateTrainingsplan() {
        doNothing().when(trainingsplanRepository).insertTrainingsplan(any(Trainingsplan.class));

        trainingsplanService.createTrainingsplan(1L, "Test Plan", "Test Description", new Duration(4) {
        }, "Fitness Goal", new ArrayList<>());

        verify(trainingsplanRepository, times(1)).insertTrainingsplan(any(Trainingsplan.class));
    }

    @Test
    public void testGetTrainingsplanById() {
        when(trainingsplanRepository.getTrainingsplanById(1L)).thenReturn(mockTrainingsplan);

        Trainingsplan retrievedTrainingsplan = trainingsplanService.getTrainingsplanById(1L);

        assertNotNull(retrievedTrainingsplan);
        assertEquals(1L, retrievedTrainingsplan.getId());
        assertEquals("Test Plan", retrievedTrainingsplan.getName());
    }

    @Test
    public void testUpdateTrainingsplanDuration() {
        when(trainingsplanRepository.getTrainingsplanById(1L)).thenReturn(mockTrainingsplan);
        doNothing().when(trainingsplanRepository).updateTrainingsplan(any(Trainingsplan.class));

        trainingsplanService.updateTrainingsplanDuration(1L, mockDuration);

        assertEquals(6, mockTrainingsplan.getDurationInWeeks());
        verify(trainingsplanRepository, times(1)).updateTrainingsplan(mockTrainingsplan);
    }

    @Test
    public void testAddExerciseToTrainingsplan() {
        when(trainingsplanRepository.getTrainingsplanById(1L)).thenReturn(mockTrainingsplan);
        doNothing().when(trainingsplanRepository).updateTrainingsplan(any(Trainingsplan.class));

        trainingsplanService.addExerciseToTrainingsplan(1L, mockExercise);

        assertTrue(mockTrainingsplan.getExercises().contains(mockExercise));
        verify(trainingsplanRepository, times(1)).updateTrainingsplan(mockTrainingsplan);
    }

    @Test
    public void testRemoveExerciseFromTrainingsplan() {
        mockTrainingsplan.addExercise(mockExercise);
        when(trainingsplanRepository.getTrainingsplanById(1L)).thenReturn(mockTrainingsplan);
        doNothing().when(trainingsplanRepository).updateTrainingsplan(any(Trainingsplan.class));

        trainingsplanService.removeExerciseFromTrainingsplan(1L, mockExercise);

        assertFalse(mockTrainingsplan.getExercises().contains(mockExercise));
        verify(trainingsplanRepository, times(1)).updateTrainingsplan(mockTrainingsplan);
    }

    @Test
    public void testDeleteTrainingsplan() {
        doNothing().when(trainingsplanRepository).deleteTrainingsplan(1L);

        trainingsplanService.deleteTrainingsplan(1L);

        verify(trainingsplanRepository, times(1)).deleteTrainingsplan(1L);
    }
}

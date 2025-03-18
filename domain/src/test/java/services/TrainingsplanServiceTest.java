package services;

import entitys.Exercise;
import entitys.Trainingsplan;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import repositories.TrainingsplanRepository;
import valueobjects.Duration;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrainingsplanServiceTest {

    @Mock
    private TrainingsplanRepository trainingsplanRepository;

    @InjectMocks
    private TrainingsplanService trainingsplanService;

    @Test
    public void testCreateTrainingsplan() {
        Trainingsplan trainingsplan = new Trainingsplan(1L, "Plan 1", "Description", new Duration(4), "Lose Weight", null);
        when(trainingsplanRepository.save(trainingsplan)).thenReturn(trainingsplan);

        trainingsplanService.createTrainingsplan(1L, "Plan 1", "Description", new Duration(4), "Lose Weight", null);
        verify(trainingsplanRepository, times(1)).save(trainingsplan);
    }

    @Test
    public void testGetTrainingsplanById() {
        Trainingsplan trainingsplan = new Trainingsplan(1L, "Plan 1", "Description", new Duration(4), "Lose Weight", null);
        when(trainingsplanRepository.getTrainingsplanById(1L)).thenReturn(trainingsplan);

        Trainingsplan result = trainingsplanService.getTrainingsplanById(1L);
        assertEquals(trainingsplan, result);
    }

    @Test
    public void testUpdateTrainingsplan() {
        Trainingsplan trainingsplan = new Trainingsplan(1L, "Plan 1", "Description", new Duration(4), "Lose Weight", null);
        when(trainingsplanRepository.getTrainingsplanById(1L)).thenReturn(trainingsplan);

        trainingsplanService.updateTrainingsplan(1L, "Updated Plan", "New Description", new Duration(6), "Gain Muscle", null);
        verify(trainingsplanRepository, times(1)).updateTrainingsplan(trainingsplan);
    }

    @Test
    public void testDeleteTrainingsplan() {
        doNothing().when(trainingsplanRepository).deleteById(1L);

        trainingsplanService.deleteTrainingsplan(1L);
        verify(trainingsplanRepository, times(1)).deleteById(1L);
    }
}

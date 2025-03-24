package services;

import entitys.Exercise;
import entitys.Trainingsplan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.TrainingsplanRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrainingsplanServiceTest {

    @Mock
    private TrainingsplanRepository trainingsplanRepository;

    @InjectMocks
    private TrainingsplanService trainingsplanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTrainingsplan() {
        Trainingsplan trainingsplan = new Trainingsplan("Plan 1", "Description", "Lose Weight", List.of(1, 3, 5), List.of());
        when(trainingsplanRepository.save(any(Trainingsplan.class))).thenReturn(trainingsplan);

        Trainingsplan createdPlan = trainingsplanService.createTrainingsplan("Plan 1", "Description", "Lose Weight", List.of(1, 3, 5), List.of());

        assertNotNull(createdPlan);
        assertEquals("Plan 1", createdPlan.getName());
        verify(trainingsplanRepository, times(1)).save(any(Trainingsplan.class));
    }

    @Test
    public void testGetTrainingsplanById() {
        Trainingsplan trainingsplan = new Trainingsplan("Plan 1", "Description", "Lose Weight", List.of(1, 3, 5), List.of());
        when(trainingsplanRepository.findById(1L)).thenReturn(Optional.of(trainingsplan));

        Trainingsplan result = trainingsplanService.getTrainingsplanById(1L);

        assertNotNull(result);
        assertEquals("Plan 1", result.getName());
        verify(trainingsplanRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateTrainingsplan() {
        Trainingsplan trainingsplan = new Trainingsplan("Plan 1", "Description", "Lose Weight", List.of(1, 3, 5), List.of());
        when(trainingsplanRepository.findById(1L)).thenReturn(Optional.of(trainingsplan));
        when(trainingsplanRepository.save(any(Trainingsplan.class))).thenReturn(trainingsplan);

        Trainingsplan updatedPlan = trainingsplanService.updateTrainingsplan(1L, "Updated Plan", "New Description", "Gain Muscle", List.of(2, 4, 6), List.of());

        assertNotNull(updatedPlan);
        assertEquals("Updated Plan", updatedPlan.getName());
        assertEquals("Gain Muscle", updatedPlan.getGoal());
        verify(trainingsplanRepository, times(1)).save(any(Trainingsplan.class));
    }

    @Test
    public void testDeleteTrainingsplan() {
        doNothing().when(trainingsplanRepository).deleteById(1L);

        trainingsplanService.deleteTrainingsplan(1L);

        verify(trainingsplanRepository, times(1)).deleteById(1L);
    }
}

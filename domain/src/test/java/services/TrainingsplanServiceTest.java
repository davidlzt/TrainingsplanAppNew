package services;

import entitys.Exercise;
import entitys.Trainingsplan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    private Trainingsplan trainingsplan;

    @BeforeEach
    void setUp() {
        trainingsplan = new Trainingsplan("Plan 1", "Description", "Lose Weight", List.of(1, 3, 5), List.of());
    }

    @Test
    public void testCreateTrainingsplan() {
        when(trainingsplanRepository.save(any(Trainingsplan.class))).thenReturn(trainingsplan);

        Trainingsplan createdPlan = trainingsplanService.createTrainingsplan(trainingsplan);

        assertNotNull(createdPlan);
        assertEquals("Plan 1", createdPlan.getName());
        assertEquals("Lose Weight", createdPlan.getGoal());
        verify(trainingsplanRepository, times(1)).save(any(Trainingsplan.class));
    }


    @Test
    public void testDeleteTrainingsplan() {
        doNothing().when(trainingsplanRepository).deleteById(1L);

        trainingsplanService.deleteTrainingsplan(1L);

        verify(trainingsplanRepository, times(1)).deleteById(1L);
    }
}

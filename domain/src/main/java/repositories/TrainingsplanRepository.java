package repositories;

import entitys.Trainingsplan;

import java.util.List;

public interface TrainingsplanRepository {
    void insertTrainingsplan(Trainingsplan trainingsplan);
    Trainingsplan save(Trainingsplan trainingsplan);

    Trainingsplan getTrainingsplanById(Long id);
    List<Trainingsplan> getAllTrainingsplaene();
    void updateTrainingsplan(Trainingsplan trainingsplan);
    void deleteTrainingsplan(Long id);
}

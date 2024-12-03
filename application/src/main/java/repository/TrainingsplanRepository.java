package repository;

import models.Trainingsplan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingsplanRepository extends JpaRepository<Trainingsplan, Long> {
}

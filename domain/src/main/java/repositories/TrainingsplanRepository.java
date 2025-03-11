package repositories;

import entitys.Trainingsplan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingsplanRepository extends JpaRepository<Trainingsplan, Long> {

    @Override
    Trainingsplan save(Trainingsplan trainingsplan);
    List<Trainingsplan> findAll();

    Trainingsplan getTrainingsplanById(Long id);

    @Modifying
    @Query("UPDATE Trainingsplan t SET t.name = :name WHERE t.id = :id")
    void updateTrainingsplan(Trainingsplan trainingsplan);

    void deleteById(Long id);
}

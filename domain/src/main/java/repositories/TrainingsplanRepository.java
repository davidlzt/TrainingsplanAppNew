package repositories;

import entitys.Exercise;
import entitys.Trainingsplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingsplanRepository extends JpaRepository<Trainingsplan, Long> {

    List<Trainingsplan> findAll();

    Optional<Trainingsplan> findById(Long id);

    @Modifying
    @Query("UPDATE Trainingsplan t SET t.name = :name, t.description = :description, t.goal = :goal, t.trainingDays = :trainingDays WHERE t.id = :id")
    void updateTrainingsplan(@Param("id") Long id,
                             @Param("name") String name,
                             @Param("description") String description,
                             @Param("goal") String goal,
                             @Param("trainingDays") List<Integer> trainingDays);

    void deleteById(Long id);


    @Query("SELECT e FROM Trainingsplan e JOIN e.exercises WHERE e.id = :trainingsplanId")
    List<Exercise> findExercisesByTrainingsplanId(@Param("trainingsplanId") Long trainingsplanId);

}

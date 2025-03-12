package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entitys.Muscle;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Long> {
}
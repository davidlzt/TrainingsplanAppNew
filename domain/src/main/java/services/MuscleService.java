package services;

import entitys.Muscle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.MuscleRepository;

import java.util.List;

@Service
public class MuscleService {

    private final MuscleRepository muscleRepository;

    @Autowired
    public MuscleService(MuscleRepository muscleRepository) {
        this.muscleRepository = muscleRepository;
    }

    public List<Muscle> getAllMuscles() {
        return muscleRepository.findAll();
    }
}

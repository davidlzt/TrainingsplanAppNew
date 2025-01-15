package databasehandler;
import entitys.Trainingsplan;
import repositories.TrainingsplanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrainingsplanRepositoryIMPL implements TrainingsplanRepository {

    private List<Trainingsplan> trainingsplaene = new ArrayList<>();

    @Override
    public void insertTrainingsplan(Trainingsplan trainingsplan) {

    }

    @Override
    public Trainingsplan save(Trainingsplan trainingsplan) {
        if (trainingsplan.getId() != null) {
            Optional<Trainingsplan> existingTrainingsplan = trainingsplaene.stream()
                    .filter(t -> t.getId().equals(trainingsplan.getId()))
                    .findFirst();

            if (existingTrainingsplan.isPresent()) {
                int index = trainingsplaene.indexOf(existingTrainingsplan.get());
                trainingsplaene.set(index, trainingsplan);
                System.out.println("Trainingsplan aktualisiert: " + trainingsplan.getName());
            } else {
                trainingsplaene.add(trainingsplan);
                System.out.println("Trainingsplan hinzugefügt: " + trainingsplan.getName());
            }
        } else {
            trainingsplaene.add(trainingsplan);
            System.out.println("Trainingsplan hinzugefügt: " + trainingsplan.getName());
        }
        return trainingsplan;
    }

    @Override
    public Trainingsplan getTrainingsplanById(Long id) {
        Optional<Trainingsplan> trainingsplan = trainingsplaene.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        return trainingsplan.orElse(null);
    }

    @Override
    public List<Trainingsplan> getAllTrainingsplaene() {
        return new ArrayList<>(trainingsplaene);
    }

    @Override
    public void updateTrainingsplan(Trainingsplan trainingsplan) {

    }

    @Override
    public void deleteTrainingsplan(Long id) {
        trainingsplaene.removeIf(t -> t.getId().equals(id));
        System.out.println("Trainingsplan gelöscht mit ID: " + id);
    }

}

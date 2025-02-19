package restcontroller;

import entitys.Trainingsplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.TrainingsplanRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Trainingsplan")
public class TrainingsplanController {

    @Autowired
    private TrainingsplanRepository trainingsplanRepository;

    @GetMapping
    public List<Trainingsplan> getAllTrainingsplaene() {
        return trainingsplanRepository.getAllTrainingsplaene();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainingsplan> getTrainingsplanById(@PathVariable Long id) {
        Optional<Trainingsplan> trainingsplan = Optional.ofNullable(trainingsplanRepository.getTrainingsplanById(id));
        return trainingsplan.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Trainingsplan> createTrainingsplan(@RequestBody Trainingsplan trainingsplan) {
        Trainingsplan savedTrainingsplan = trainingsplanRepository.save(trainingsplan);
        return new ResponseEntity<>(savedTrainingsplan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainingsplan> updateTrainingsplan(@PathVariable Long id, @RequestBody Trainingsplan trainingsplan) {
        if (trainingsplanRepository.getTrainingsplanById(id) != null) {
            Trainingsplan updatedTrainingsplan = trainingsplanRepository.save(trainingsplan);
            return new ResponseEntity<>(updatedTrainingsplan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingsplan(@PathVariable Long id) {
        if (trainingsplanRepository.getTrainingsplanById(id) != null) {
            trainingsplanRepository.deleteTrainingsplan(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

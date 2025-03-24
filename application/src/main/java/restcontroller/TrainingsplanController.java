package restcontroller;

import entitys.Trainingsplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restcontroller.util.TrainingsplanDTO;
import services.TrainingsplanApplicationService;
import java.util.Map;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/trainingsplan")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class TrainingsplanController {

    @Autowired
    private TrainingsplanApplicationService trainingsplanService;

    @GetMapping
    public List<Trainingsplan> getAllTrainingsplaene() {
        return trainingsplanService.getAllTrainingsplaene();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainingsplan> getTrainingsplanById(@PathVariable Long id) {
        Optional<Trainingsplan> trainingsplan = Optional.ofNullable(trainingsplanService.getTrainingsplanById(id));
        return trainingsplan.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createTrainingsplan(@RequestBody TrainingsplanDTO trainingsplanDTO) {
        try {
            System.out.println("Empfangenes TrainingsplanDTO-Objekt: " + trainingsplanDTO);

            Map<Integer, Integer> selectedExercises = trainingsplanDTO.getSelectedExercises().entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> Integer.parseInt(entry.getKey()),
                            Map.Entry::getValue
                    ));

            System.out.println("Konvertierte Übungen: " + selectedExercises);

            Trainingsplan newPlan = trainingsplanService.createTrainingsplanFromDTO(trainingsplanDTO, selectedExercises);

            return ResponseEntity.ok(newPlan);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fehler beim Erstellen des Trainingsplans: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainingsplan> updateTrainingsplan(@PathVariable Long id, @RequestBody TrainingsplanDTO dto) {
        Trainingsplan updatedTrainingsplan = trainingsplanService.updateTrainingsplan(id, dto, dto.getSelectedExercises());
        if (updatedTrainingsplan != null) {
            return new ResponseEntity<>(updatedTrainingsplan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingsplan(@PathVariable Long id) {
        boolean deleted = trainingsplanService.deleteTrainingsplan(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

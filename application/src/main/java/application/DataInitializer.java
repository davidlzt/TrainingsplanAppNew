package application;

import entitys.Device;
import entitys.Exercise;
import entitys.Muscle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repositories.DeviceRepository;
import repositories.ExerciseRepository;
import repositories.MuscleRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;
    private final DeviceRepository deviceRepository;
    private final MuscleRepository muscleRepository;

    public DataInitializer(ExerciseRepository exerciseRepository, DeviceRepository deviceRepository, MuscleRepository muscleRepository) {
        this.exerciseRepository = exerciseRepository;
        this.deviceRepository = deviceRepository;
        this.muscleRepository = muscleRepository;
    }

    @Override
    public void run(String... args) {
        if (muscleRepository.count() == 0) {
            List<Muscle> muscles = List.of(
                    new Muscle("Schulter", "Für Schulterübungen"),
                    new Muscle("Nacken", "Für Nackenübungen"),
                    new Muscle("Oberer Rücken", "Für obere Rückenmuskulatur"),
                    new Muscle("Unterer Rücken", "Für untere Rückenmuskulatur"),
                    new Muscle("Bizeps", "Für Bizepsübungen"),
                    new Muscle("Trizeps", "Für Trizepsübungen"),
                    new Muscle("Brust", "Für Brustübungen"),
                    new Muscle("Beine", "Für Beinmuskulatur"),
                    new Muscle("Bauch", "Für Bauchmuskulatur")
            );
            muscleRepository.saveAll(muscles);
        }

        if (exerciseRepository.count() == 0) {
            List<Device> devices = createDefaultDevices();
            deviceRepository.saveAll(devices);

            List<Exercise> exercises = createDefaultExercises(devices);
            exerciseRepository.saveAll(exercises);
        }
    }

    private List<Device> createDefaultDevices() {
        Device langhantel = new Device("Langhantel", "Verwendung für verschiedene Übungen wie Kreuzheben und Bankdrücken.");
        Device beinpresse = new Device("Beinpresse", "Verwendung für Beinübungen.");
        Device kabelzug = new Device("Kabelzug", "Verwendung für Latziehen und Trizepsübungen.");
        Device schulterdrueckenmaschine = new Device("Schulterdrückenmaschine", "Verwendung für Schulterdrückübungen.");
        Device bank = new Device("Bank", "Verwendung für Bankdrücken und andere Oberkörperübungen.");
        Device kurzhanteln = new Device("Kurzhanteln", "Verwendung für Bizeps-, Trizeps- und Schulterübungen.");
        Device beinstrecker = new Device("Beinstrecker", "Verwendung für Beinübungen.");
        Device beugemaschine = new Device("Beugemaschine", "Verwendung für Beinbeugung (Hamstring-Übungen).");

        return Arrays.asList(langhantel, beinpresse, kabelzug, schulterdrueckenmaschine, bank, kurzhanteln, beinstrecker, beugemaschine);
    }

    private List<Exercise> createDefaultExercises(List<Device> devices) {
        List<Muscle> muscles = muscleRepository.findAll();

        Muscle schulter = findMuscle(muscles, "Schulter");
        Muscle nacken = findMuscle(muscles, "Nacken");
        Muscle obererRucken = findMuscle(muscles, "Oberer Rücken");
        Muscle untererRucken = findMuscle(muscles, "Unterer Rücken");
        Muscle bizeps = findMuscle(muscles, "Bizeps");
        Muscle trizeps = findMuscle(muscles, "Trizeps");
        Muscle brust = findMuscle(muscles, "Brust");
        Muscle beine = findMuscle(muscles, "Beine");
        Muscle bauch = findMuscle(muscles, "Bauch");

        Device langhantel = devices.get(0);
        Device beinpressmaschiene = devices.get(1);
        Device kabelzug = devices.get(2);
        Device schulterdrueckenmaschine = devices.get(3);
        Device bank = devices.get(4);
        Device kurzhanteln = devices.get(5);
        Device beinstrecker = devices.get(6);
        Device beugemaschine = devices.get(7);

        Exercise schulterdruecken = new Exercise("Schulterdrücken", List.of(schulter), "Übung für die Schultern", List.of(schulterdrueckenmaschine));
        Exercise rudern = new Exercise("Rudern", List.of(obererRucken), "Übung für den oberen Rücken", List.of(langhantel));
        Exercise latziehen = new Exercise("Latziehen", List.of(obererRucken), "Übung für den Latissimus", List.of(kabelzug));
        Exercise kreuzheben = new Exercise("Kreuzheben", List.of(untererRucken), "Übung für den unteren Rücken", List.of(langhantel));
        Exercise bankdruecken = new Exercise("Bankdrücken", List.of(brust, trizeps), "Brustübung mit Fokus auf Trizeps", List.of(bank));
        Exercise beinpresse = new Exercise("Beinpresse", List.of(beine), "Beinübung für Quadrizeps und Oberschenkel", List.of(beinpressmaschiene));
        Exercise kniebeugen = new Exercise("Kniebeugen", List.of(beine), "Beinübung für Quadrizeps, Oberschenkel und Gesäßmuskeln", List.of(langhantel));
        Exercise bauchmuskeltraining = new Exercise("Crunches", List.of(bauch), "Bauchübung für die oberen Bauchmuskeln", List.of());
        Exercise bizepscurl = new Exercise("Bizepscurls", List.of(bizeps), "Übung für den Bizeps", List.of(kurzhanteln));
        Exercise trizepsdruecken = new Exercise("Trizepsdrücken", List.of(trizeps), "Übung für den Trizeps", List.of(kabelzug));
        Exercise schulterheben = new Exercise("Schulterheben", List.of(nacken), "Übung für den Nacken", List.of(kurzhanteln));
        Exercise ausfallschritte = new Exercise("Ausfallschritte", List.of(beine), "Übung für die Oberschenkel und Gesäßmuskeln", List.of(kurzhanteln));
        Exercise wadenheben = new Exercise("Wadenheben", List.of(beine), "Übung für die Wadenmuskulatur", List.of(beinstrecker));

        return List.of(
                schulterdruecken, rudern, latziehen, kreuzheben, bankdruecken,
                beinpresse, kniebeugen, bauchmuskeltraining, bizepscurl, trizepsdruecken,
                schulterheben, ausfallschritte, wadenheben
        );
    }

    private Muscle findMuscle(List<Muscle> muscles, String name) {
        return muscles.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Muskel nicht gefunden: " + name));
    }
}

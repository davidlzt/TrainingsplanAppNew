package models;

public enum Muscle {

    BIZEPS("Bizeps", "Der Bizeps ist der Hauptmuskel im Oberarm, der bei Armbeugen aktiviert wird."),

    TRIZEPS("Trizeps", "Der Trizeps ist der Gegenspieler des Bizeps und für die Streckung des Arms verantwortlich."),

    BRUST("Brust", "Die Brustmuskulatur ist für die Armbewegung nach vorne verantwortlich."),

    // Schultermuskulatur
    SCHULTER_VORNE("Schulter vorne", "Der vordere Teil des Deltamuskels ist für das Anheben des Arms nach vorne verantwortlich."),
    SCHULTER_MITTE("Schulter Mitte", "Der mittlere Teil des Deltamuskels ist für das Heben des Arms zur Seite verantwortlich."),
    SCHULTER_HINTEN("Schulter hinten", "Der hintere Teil des Deltamuskels ist für das Heben des Arms nach hinten verantwortlich."),

    RUECKEN_OBEN("Oberer Rücken", "Der obere Rücken umfasst den Trapezmuskel und die oberen Bereiche der Lats."),
    RUECKEN_MITTE("Mittlerer Rücken", "Der mittlere Rücken umfasst die mittleren Lats und die Rhomboiden."),
    RUECKEN_UNTEN("Unterer Rücken", "Der untere Rücken umfasst die Lendenmuskulatur, insbesondere den Erector spinae."),
    NACKEN("Nacken", "Der Nackenmuskel (Trapez) ist für das Beugen und Drehen des Kopfes verantwortlich."),

    QUADRIZEPS("Quadrizeps", "Der Quadrizeps ist der Hauptmuskel im Oberschenkel, der bei der Beinstreckung arbeitet."),
    BEINBEUGER("Beinbeuger", "Die Beinbeugemuskeln (Hamstrings) sind die Muskeln auf der Rückseite des Oberschenkels."),
    WADEN("Waden", "Die Wadenmuskeln (Gastrocnemius und Soleus) sind für die Fußhebung verantwortlich."),

    OBERSCHNITTSBAUCH("Oberschnittsbauch", "Die obere Bauchmuskulatur umfasst den oberen Teil des Rectus Abdominis."),
    UNTERSCHNITTSBAUCH("Unterschnittsbauch", "Der untere Bauchbereich umfasst den unteren Teil des Rectus Abdominis."),
    LIEGEBECHTERBAUCH("Seitlicher Bauch", "Der seitliche Bauch (Obliques) ist für das Drehen und Beugen des Oberkörpers verantwortlich."),

    GLUTEUS("Gluteus", "Der Gluteus ist der Gesäßmuskel, der beim Heben und Strecken des Oberschenkels aktiviert wird."),

    FOREARM("Unterarm", "Die Unterarmmuskeln sind für die Bewegung der Hand und Finger verantwortlich."),
    KALBSMUSKULATUR("Kalbs", "Kalbsregion ist der Musculus gastrocnemius und der Musculus soleus der hinteren Unterschenkelmuskulatur."),

    CORE("Core", "Der Core besteht aus einer Kombination der Bauch-, unteren Rücken- und Hüftmuskeln und spielt eine Rolle bei der Stabilität des Rumpfes.");

    private final String name;
    private final String description;

    Muscle(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static void printAllMuscles() {
        for (Muscle muscle : Muscle.values()) {
            System.out.println(muscle.getName() + ": " + muscle.getDescription());
        }
    }

    @Override
    public String toString() {
        return name + " (" + description + ")";
    }
}

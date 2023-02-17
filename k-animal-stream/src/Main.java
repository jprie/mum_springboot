import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {


        Animal dog = new Animal("Dog", Animal.Species.MAMMAL, 30, 20.5f);
        Animal spider = new Animal("Spider", Animal.Species.INSECT, 12, 0.2f);
        Animal crocodile = new Animal("Crocodile", Animal.Species.REPTILE, 0, 90.2f);
        Animal elephant = new Animal("Elephant", Animal.Species.MAMMAL, 50, 1500.4f);

        ArrayList<Animal> animals = new ArrayList<>(List.of(dog, spider, crocodile, elephant));

        // Aufgabe 1
        // Erzeugen Sie aus dem animals-ArrayList eine neue Liste die...
        /*
        - nur Säugetiere
        - Namen der Säugetiere
        - das Erste überspringen
        - rückwärts sortiert
         */

        var animalNamesReversed = animals.stream()
                .filter(animal -> animal.getSpecies() == Animal.Species.MAMMAL)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .skip(1)
                .toList();

        System.out.println("Animal names reversed: " + animalNamesReversed);

        // Aufgabe 2
        // Finden Sie das leichteste INSECT

        var lightestInsect = animals.stream()
                .filter(animal -> animal.getSpecies() == Animal.Species.INSECT)
//                .sorted(Comparator.comparing(Animal::getWeight))
//                .limit(1)
//                .toList();
//                .findFirst();
                .min(Comparator.comparing(Animal::getWeight));

        System.out.println("Lightest insect: " + lightestInsect);

        // Aufgabe 3
        // Finden Sie das Schnellste Reptile, aber ohne die 2 schwersten Tieren zu zählen


        var fastestAnimal = animals.stream()
                .sorted(Comparator.comparing(Animal::getWeight).reversed())
                .skip(2)
                .filter(animal -> animal.getSpecies() == Animal.Species.REPTILE)
                // wenn hier keine Reptilien mehr bekomm ich einen
                // leeren Stream
                .max(Comparator.comparing(Animal::getSpeed));

        //Vorteil: wenn fastestAnimal empty ist, dann bekomm ich deswegen keine Exception
        Optional<String> optName = fastestAnimal.map(Animal::getName);

        // "Nachteil": ich hab halt nur ein Optional<String>
        String actuallyTheString = optName.get(); // BOOOOMBEEEE explodiert immerhin sofort!!!

        // Sicher die Bombe entpacken,
        String securelyUnpackedString = optName.orElse("Default String");

        optName.orElseGet(Main::generateDefaultString);

        // Wirft eine Runtime Exception
        optName.orElseThrow();

        try {
            optName.orElseThrow(() -> new Exception("Ausnahme aufgetreten"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(fastestAnimal);

    }

    public static String generateDefaultString() {
         return "Default String";
    }
}
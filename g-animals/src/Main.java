import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Animal dog = new Animal("Dog", Animal.Species.MAMMAL, 30, 20.5f);
        Animal spider = new Animal("Spider", Animal.Species.INSECT, 12, 0.2f);
        Animal crocodile = new Animal("Crocodile", Animal.Species.REPTILE, 0, 90.2f);

        ArrayList<Animal> animals = new ArrayList<>(List.of(dog, spider, crocodile));

        System.out.println(animals);

        Collections.sort(animals, new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.getSpeed() - o2.getSpeed();
            }
        });

        Collections.sort(animals, Comparator.comparing(Animal::getWeight));

        Collections.sort(animals, (a1,a2) -> a1.getSpecies().compareTo(a2.getSpecies()));

        Collections.sort(animals, Main::compareSpeed);
    }

    public static int compareSpeed(Animal a1, Animal a2) {
        return a1.getSpeed() - a2.getSpeed();
    }
}
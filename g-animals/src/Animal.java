public class Animal implements Comparable<Animal> {

    @Override
    public int compareTo(Animal o) {
        return name.compareTo(o.name);
    }

    enum Species {
        BIRD, FISH, INSECT, MAMMAL, REPTILE
    }

    private String name;
    private Species species;
    private int speed;
    private float weight;

    public Animal(String name, Species species, int speed, float weight) {
        this.name = name;
        this.species = species;
        this.speed = speed;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", species=" + species +
                ", speed=" + speed +
                ", weight=" + weight +
                '}';
    }

    public String getName() {
        return name;
    }

    public Species getSpecies() {
        return species;
    }

    public int getSpeed() {
        return speed;
    }

    public float getWeight() {
        return weight;
    }
}

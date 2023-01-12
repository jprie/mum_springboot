package app.dogs;

import java.util.Objects;

public abstract class Dog {

    public static final int BASE_TAX = 120;
    private String name;
    private String barkSound;

    protected Dog(String name, String barkSound) {
        this.name = name;
        this.barkSound = barkSound;
    }

    // Abstrakte Methode hat keinen body (=keine Implementierung)
    // Muss in der abgeleiteten Klasse implementiert werden
    public abstract double calculateTaxes();

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", barkSound='" + barkSound + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(name, dog.name) && Objects.equals(barkSound, dog.barkSound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, barkSound);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarkSound() {
        return barkSound;
    }

    public void setBarkSound(String barkSound) {
        this.barkSound = barkSound;
    }
}

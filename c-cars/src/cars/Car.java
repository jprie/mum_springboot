package cars;

public class Car {

    private static int numberWheels = 4;

    // (Objekt) Attribute
    private String brand;
    private String model;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    // Überschreibt die Standard-Implementierung von toString() die wir von
    // java.lang.Object geerbt haben.
    // NUR Methoden, die in einer Eltern-Klasse implementiert sind (siehe: Methoden-Signatur)
    // können tatsächlich überschrieben werden.
    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", numberWheels=" + numberWheels +
                '}';
    }

    public static int getNumberWheels() {
        return numberWheels;
    }

    public static void setNumberWheels(int numberWheels) {
        Car.numberWheels = numberWheels;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

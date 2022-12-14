package cars;

public class SportsCar extends Car {

    public SportsCar(String brand, String model) {
        super(brand, model);
    }

    @Override
    public String toString() {
        return "Sports" + super.toString();
    }
}

package app.dogs;

public class GardenDog extends PetDog {

    public GardenDog(String name, String barkSound) {
        super(name, barkSound);
    }

    @Override
    public double calculateTaxes() {
        return BASE_TAX;
    }
}

package app.dogs;

public class FamilyDog extends PetDog {

    public FamilyDog(String name, String barkSound) {
        super(name, barkSound);
    }

    @Override
    public double calculateTaxes() {
        return BASE_TAX;
    }


}

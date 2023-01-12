package app.dogs;

public class RescueDog extends Dog {

    public RescueDog(String name, String barkSound) {
        super(name, barkSound);
    }

    @Override
    public double calculateTaxes() {
        return BASE_TAX/2;
    }

}

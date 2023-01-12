package app.dogs;

public class GuideDog extends Dog {

    public GuideDog(String name, String barkSound) {
        super(name, barkSound);
    }

    @Override
    public double calculateTaxes() {
        return 0;
    }
}

package app.dogs;

public class WatchDog extends Dog {

// public WatchDog() {}
    private int trainingLevel;

    public WatchDog(String name, String barkSound, int trainingLevel) {
        super(name, barkSound);
        this.trainingLevel = trainingLevel;
    }

    @Override
    public double calculateTaxes() {
        return BASE_TAX*0.8;
    }
}

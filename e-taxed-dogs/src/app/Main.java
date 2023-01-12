package app;

import app.dogs.Dog;
import app.dogs.GuideDog;
import app.dogs.RescueDog;

public class Main {
    public static void main(String[] args) {

        RescueDog rudi = new RescueDog("Rudi", "waff");

        // -ea einschalten!!
        assert(rudi.calculateTaxes() == Dog.BASE_TAX/2);

        Dog waldi = new GuideDog("Waldi", "rawau");

        assert(waldi.calculateTaxes() == 0);


    }
}
package app.roundtrips;

import app.destinations.Destination;
import app.trips.Trip;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RoundTrip extends Trip {

    private ArrayList<Destination> destinations;

    public RoundTrip(String title, ArrayList<Destination> destinations, Trip.MeansOfTravel means,
                     BigDecimal price) {

        // "Jeder Konstruktor muss den Konstruktor der Basis-Klasse aufrufen"
        super(title, null, means, price);
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        // TODO: anderes Format unter Verwendung der Attribute/getter definieren
        return String.format("app.roundtrips.RoundTrip{id=%d, title=%s, destinations=%s, means=%s, price=%s}",
        id, title, destinations, means, price);
    }

    //    public app.roundtrips.RoundTrip() {
//        super()
//    }
}

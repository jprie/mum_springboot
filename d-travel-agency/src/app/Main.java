package app;

import app.destinations.Destination;
import app.roundtrips.RoundTrip;
import app.trips.Trip;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Fine Travels");

        TravelAgency agency = initTravelAgency();

        System.out.println(agency);

        InputHandler inputHandler = new InputHandler(agency);
        inputHandler.run();

        testStaticMethode();


    }

    private static TravelAgency initTravelAgency() {
        Trip barcelonaTrip = new Trip("Barcelona for New Year's",
                new Destination("Barcelona","Spain"),
                Trip.MeansOfTravel.PLANE, new BigDecimal("450.99"));

        Trip seychellesTrip = new Trip("Seychelles is a dream",
                new Destination("Seychelles", "Seychelles"),
                Trip.MeansOfTravel.PLANE, new BigDecimal("1200.00"));

        Trip grazTrip = new Trip("Graz für Weihnachten",
                new Destination("Graz"," Austria"),
                Trip.MeansOfTravel.TRAIN,
                new BigDecimal("42.05"));

        ArrayList<Destination> destinations = new ArrayList<>();

        destinations.add(new Destination("Vienna", "Austria"));
        destinations.add(new Destination("Munich", "Germany"));
        destinations.add(new Destination("Basel", "Switzerland"));
        destinations.add(new Destination("Vienna", "Austria"));

        RoundTrip europeTrip = new RoundTrip("Europe trip",
                destinations,
                Trip.MeansOfTravel.BIKE,
                new BigDecimal("50.12"));


        System.out.println(barcelonaTrip);
        System.out.println(seychellesTrip);
        System.out.println(grazTrip);

        // new ArrayList<app.trips.Trip>() erstellt eine leere Liste!
        TravelAgency agency  = new TravelAgency("Fine Travels", new ArrayList<Trip>());

        // Nimmt die ArrayList in app.TravelAgency und fügt einen app.trips.Trip ein
        // Über getter ein app.trips.Trip hinzufügen
        agency.getTrips().add(grazTrip);

        agency.addTrip(europeTrip);

        agency.addTrip(barcelonaTrip);
        agency.addTrip(seychellesTrip);
        agency.removeTrip(grazTrip);
        return agency;
    }



    private static void testStaticMethode() {

        // Objekt-Methode und auch Fields können aus dem statischen Kontext nicht aufgerufen werden
        // Merkregel: "Objekt kennt seine Klasse, aber Klasse kennt nicht jedes Objekt!"
        // testObjectMethode();
    }

    private void testObjectMethod() {

        testStaticMethode();
    }


}
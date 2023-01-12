package app.test;

import app.InputHandler;
import app.TravelAgency;
import app.destinations.Destination;
import app.roundtrips.RoundTrip;
import app.trips.Trip;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {

    // Führt Test-Methoden aus (separat/unabhängig von der eigentlichen Main.main)
    public static void main(String[] args) {

        System.out.println("Start Tests");

        System.out.println("Test equals for destination");
        testEqualsForDestination();

        testAddTripAllInputsGiven();

        testAddRoundTripAllInputsGiven();

        testRoundTripPolymorphic();

        testSingleTripHasDestination();
    }

    private static void testRoundTripPolymorphic() {

        RoundTrip roundTrip = new RoundTrip("Italy trip",
                new ArrayList<>(List.of(new Destination("Florence", "Italy")
                , new Destination("Rome", "Italy"))),
                Trip.MeansOfTravel.CAR,
                new BigDecimal("1200.90")
        );

        System.out.println("Number of destinations: " + roundTrip.getNumberOfDestinations());

        Trip trip = roundTrip;

//        trip.getNumberOfDestinations();
        Destination destination = new Destination("Florence", "Italy");
        if (roundTrip.hasDestination(destination)) {
            System.out.println("RoundTrip has destination: " + destination);
        }
        assert(roundTrip.hasDestination(destination));

        assert(trip.hasDestination(destination));
    }

    public static void testSingleTripHasDestination() {

        Trip singleTrip =
                new Trip("Silvester Trip", new Destination("Berlin", "Germany"),
                        Trip.MeansOfTravel.HELICOPTER, new BigDecimal("5000"));

        Destination testDestination = new Destination("Berlin", "Germany");

        assert(singleTrip.hasDestination(testDestination));
    }

    /**
     * Testet den InputHandler und konkret den Command "add"
     * User gibt alle Daten für einen Trip ein und zum Schluss wird überprüft, ob
     * der erstellte Trip alle Daten enthält
     */
    private static void testAddTripAllInputsGiven() {
        // inputHandler erstellen
        TravelAgency testAgency = new TravelAgency("Test", new ArrayList<>());
        InputHandler inputHandler = new InputHandler(testAgency);

        // input für den InputHandler erzeugen
        String input = "add\rnormal\rPrague by night\r Prague\r CZ\r train\r 133.45\r exit\r";
        InputHandler.scanner = new Scanner(input);

        // führe InputHandler mit diesem Input aus
        inputHandler.run();

        // zum Schluss: Vergleiche erwarteten Output mit tatsächlichem Output
        Trip expectedOutput = new Trip("Prague by night", new Destination("Prague", "CZ"),
                Trip.MeansOfTravel.TRAIN, new BigDecimal("133.45"));

        // wir erwarten einen Trip mit id=1
        expectedOutput.setId(1);

        // erwünschtes Ergebnis: expectedOutput equals calculatedOutput
        Trip calculatedOutput = testAgency.getTrips().get(0);
        System.out.println(calculatedOutput);
        System.out.println(expectedOutput);

        boolean testOk = expectedOutput.equals(calculatedOutput);
        System.out.println("Test: " + testOk);

        // Überprüft ob Wert in Klammern true ergibt
        // true: alles ok, KEIN OUTPUT
        // false: AssertionException
        // Damit assert tatsächlich ausgeführt wird: Run Configurations -> Modify Options -> Add VM Arguments -> "-ea"
        assert(expectedOutput.equals(calculatedOutput));

    }

    private static void testAddRoundTripAllInputsGiven() {
        // inputHandler erstellen
        TravelAgency testAgency = new TravelAgency("Test", new ArrayList<>());
        InputHandler inputHandler = new InputHandler(testAgency);

        // input für den InputHandler erzeugen
        // Veränderter Input für RoundTrip-Erstellung
        String input = "add\rround\rPrague by night\r Prague\r CZ\r yes\r Vienna\r Austria\r no\r train\r 133.45\r exit\r";
        InputHandler.scanner = new Scanner(input);

        // führe InputHandler mit diesem Input aus
        inputHandler.run();

        // zum Schluss: Vergleiche erwarteten Output mit tatsächlichem Output
//        ArrayList<Destination> destinations = new ArrayList<>();
//        destinations.add(new Destination("Prague", "CZ"));
//        destinations.add(new Destination("Vienna", "Austria"));
        Trip expectedOutput = new RoundTrip("Prague by night",
                new ArrayList<>(List.of(new Destination("Prague", "CZ"),
                                        new Destination("Vienna", "Austria"))),
                Trip.MeansOfTravel.TRAIN, new BigDecimal("133.45"));

        // wir erwarten einen Trip mit id=3
        expectedOutput.setId(3);

        // erwünschtes Ergebnis: expectedOutput equals calculatedOutput
        Trip calculatedOutput = testAgency.getTrips().get(0);
        System.out.println(calculatedOutput);
        System.out.println(expectedOutput);

        boolean testOk = expectedOutput.equals(calculatedOutput);
        System.out.println("Test: " + testOk);

        // Überprüft ob Wert in Klammern true ergibt
        // true: alles ok, KEIN OUTPUT
        // false: AssertionException
        // Damit assert tatsächlich ausgeführt wird: Run Configurations -> Modify Options -> Add VM Arguments -> "-ea"
        assert(expectedOutput.equals(calculatedOutput));

    }

    private static void testEqualsForDestination() {
        Destination graz = new Destination("Graz", "Austria");
        Destination graz2 = new Destination("Graz", "Austria");

        if (graz.equals(graz2)) {
            System.out.println(graz + " equals " + graz2);
        } else {
            System.out.println(graz + " does NOT equal " + graz2);
        }

        if (graz == graz2) {
            System.out.println(graz + " points to the same object as " + graz2);
        } else {
            System.out.println(graz + " does not point to the same object as " + graz2);
        }
    }
}

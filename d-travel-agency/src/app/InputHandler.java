package app;

import app.destinations.Destination;
import app.roundtrips.RoundTrip;
import app.trips.Trip;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {

    public static final String ADD = "add";
    public static final String REMOVE = "remove";
    public static final String SHOW = "show";
    public static final String EXIT = "exit";
    private TravelAgency travelAgency;

    // ACHTUNG: böser Hack -> wie könnte man das schöner machen
    public static Scanner scanner = new Scanner(System.in);

    public InputHandler(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    /**
     * Input-Loop liest commands ein und gibt diese an handleCommand weiter.
     * Bei Exit-Command wird Flag auf true gesetzt und Schleife beendet
     */
    public void run() {

        boolean doExit = false;


        System.out.println("Welcome to the jungle of traveling");
        do {

            System.out.println("Enter any of the following commands (add, remove, show, exit)");
            String command = scanner.next();
            doExit = handleCommand(command);

        } while(!doExit);
    }

    private boolean handleCommand(String command) {
        boolean doExit = false;
        switch (command) {
            case ADD:
                handleAddCommand();
                break;
            case REMOVE:
                handleRemoveCommand();
                break;
            case SHOW:
                handleShowCommand();
                break;
            case EXIT:
                doExit = true;
                break;
            default:
                System.out.println("Unknown command");
        }
        return doExit;
    }

    private void handleShowCommand() {
        for (Trip trip : travelAgency.getTrips()) {
            System.out.println(trip);
        }
    }

    private void handleRemoveCommand() {

        System.out.println("Enter the trip id to be removed");
        handleShowCommand();
        long tripId = scanner.nextLong();
        boolean tripRemoved = false;

        for (int i = 0; i < travelAgency.getTrips().size(); i++) {
            Trip trip = travelAgency.getTrips().get(i);
            if (trip.getId() == tripId) {
                travelAgency.getTrips().remove(trip);
                tripRemoved = true;
            }
        }

        if (!tripRemoved) {
            System.out.println("app.trips.Trip with id: " + tripId + " could not be found/removed");
        }

    }

    /**
     * Liest die Informationen für einen neuen app.trips.Trip ein, erzeugt den
     * neuen app.trips.Trip und fügt ihn in die trips der Agency ein
     */
    private void handleAddCommand() {

        System.out.println("Which kind of trip? (round/normal)");
        String addCommand = scanner.next();

        scanner.nextLine(); // \r vom Stream löschen, damit wieder 'sauber'

        // Merkregel: zwischen next...() und nextLine(), füge extra nextLine() ein,
        // damit \r entfernt

        System.out.println("Enter title");
        String title = scanner.nextLine();

        Destination destination = null;
        ArrayList<Destination> destinations = new ArrayList<>();

        if (addCommand.equals("round")) {
            //...ArrayList, hinzufügen von Destination die eingegeben wurde
            // zur Liste, so oft bis user 'exit' eingibt, roundTrip erstellen
            destinations = handleMultipleDestinationsInput();
        } else {
            // ... normaler Trip
             destination = handleDestinationInput();
        }


        System.out.println("Enter means of travel (");
        for (Trip.MeansOfTravel means : Trip.MeansOfTravel.values()) {
            System.out.println(means);
        }
        System.out.println(")");
        String meansOfTravel = scanner.next();

        System.out.println("Enter price");
        BigDecimal price = scanner.nextBigDecimal();

        Trip trip;
        if (addCommand.equals("round")) {
            trip = new RoundTrip(title, destinations,
                    Trip.MeansOfTravel.valueOf(meansOfTravel.toUpperCase()), price);
        } else {
            trip = new Trip(title, destination,
                    Trip.MeansOfTravel.valueOf(meansOfTravel.toUpperCase()), price);
        }

        travelAgency.addTrip(trip);
    }

    private ArrayList<Destination> handleMultipleDestinationsInput() {

        ArrayList<Destination> destinations = new ArrayList<>();
        String exit;
        do {

            destinations.add(handleDestinationInput());
            System.out.println("Another destination? (yes/no)");
            exit = scanner.next();

        } while(!exit.equals("no"));

        return destinations;
    }

    private Destination handleDestinationInput() {

        System.out.println("Enter city");
        String city = scanner.next();
        System.out.println("Enter country");
        String country = scanner.next();

        return new Destination(city, country);
    }



}

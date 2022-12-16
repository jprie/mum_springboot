package app;

import app.trips.Trip;

import java.util.ArrayList;

public class TravelAgency {

    private String name;
    private ArrayList<Trip> trips = new ArrayList<>();

    public TravelAgency(String name, ArrayList<Trip> trips) {
        this.name = name;
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    public void removeTrip(Trip trip) {
        this.trips.remove(trip);
    }

    @Override
    public String toString() {
        return "app.TravelAgency{" +
                "name='" + name + '\'' +
                ", trips=" + trips +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    // entfernt, damit die ArrayList nicht komplett überschrieben werden kann
//    public void setTrips(ArrayList<app.trips.Trip> trips) {
//        this.trips = trips;
//    }
}

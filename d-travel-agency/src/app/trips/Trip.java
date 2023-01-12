package app.trips;

import app.destinations.Destination;

import java.math.BigDecimal;
import java.util.Objects;

public class Trip {

    public enum MeansOfTravel {
        SHIP, CAR, BIKE, PLANE, TRAIN, HELICOPTER
    }

    private static long nextId = 1; // erste vergebene ID = 1

    protected long id;
    protected String title;
    private Destination destination;
    protected MeansOfTravel means;
    protected BigDecimal price;

    public Trip(String title, Destination destination, MeansOfTravel means, BigDecimal price) {
        this.id = nextId++; // id wird automatisch mit jedem neuen Objekt erhöht
        this.title = title;
        this.destination = destination;
        this.means = means;
        this.price = price;
    }

    public boolean hasDestination(Destination destination) {

        // Methode Objects.equals überprüft, ob eins oder beide Argumente ==null
        // Wenn ja, gibt false zurück
        return Objects.equals(this.destination, destination);
    }

    @Override
    public String toString() {
        return "app.trips.Trip{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", destination='" + destination + '\'' +
                ", means=" + means +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id && Objects.equals(title, trip.title) && Objects.equals(destination, trip.destination) && means == trip.means && Objects.equals(price, trip.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, destination, means, price);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public MeansOfTravel getMeans() {
        return means;
    }

    public void setMeans(MeansOfTravel means) {
        this.means = means;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

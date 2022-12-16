package app.trips;

import app.destinations.Destination;

import java.math.BigDecimal;

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

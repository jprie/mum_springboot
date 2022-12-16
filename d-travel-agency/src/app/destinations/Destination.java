package app.destinations;

import java.util.Objects;

public class Destination {

    private String city;
    private String country;

    public Destination(String city, String country) {
        this.city = city;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        // überprüfe ob es sich um dasselbe Objekt handelt
        if (this == o) return true;
        // Überprüfe, ob o==null, und wenn nicht ob es KEIN Destination-Objekt ist
        if (o == null || getClass() != o.getClass()) return false;

        // HIER wissen wir, dass es sich um ein Destination-Objekt handelt!!
        Destination that = (Destination) o;

//        if (!Objects.equals(city, that.city)) return false;
//        return Objects.equals(country, that.country);

        // Vergleiche einzelnen Werte der Attribute
        return Objects.equals(city, that.city) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "app.destinations.Destination{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

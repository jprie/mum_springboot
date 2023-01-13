package app.products;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Newspaper extends Product {

    private String name;

    private String country;

    private LocalDate dateOfAppearance;

    private int number;

    public Newspaper(long id, int units, BigDecimal price,
                     String name, String country, LocalDate dateOfAppearance,
                     int number) {
        super(id, units, price);
        this.name = name;
        this.country = country;
        this.dateOfAppearance = dateOfAppearance;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", dateOfAppearance=" + dateOfAppearance +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean containsKeyword(String keyword) {
        return name.contains(keyword);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDateOfAppearance() {
        return dateOfAppearance;
    }

    public void setDateOfAppearance(LocalDate dateOfAppearance) {
        this.dateOfAppearance = dateOfAppearance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

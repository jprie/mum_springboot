package app.products;

import java.math.BigDecimal;

public class Magazine extends Product {

    private String name;

    private Genre genre;

    private Appearance appearance;

    private int volume;

    public Magazine(long id, int units, BigDecimal price,
                    String name, Genre genre, Appearance appearance,
                    int volume) {
        super(id, units, price);
        this.name = name;
        this.genre = genre;
        this.appearance = appearance;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "name='" + name + '\'' +
                ", genre=" + genre +
                ", appearance=" + appearance +
                ", volume=" + volume +
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}

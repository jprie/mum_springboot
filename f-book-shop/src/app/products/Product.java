package app.products;

import java.math.BigDecimal;

public abstract class Product {

    private long id;

    private int units;

    private BigDecimal price;

    public Product(long id, int units, BigDecimal price) {
        this.id = id;
        this.units = units;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", units=" + units +
                ", price=" + price +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public abstract boolean containsKeyword(String keyword);
}

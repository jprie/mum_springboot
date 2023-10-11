package org.example;

public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
        this.side = side;
    }

    double side;

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public void setHeight(double height) {
        side = height;
        super.setWidth(height);
    }

    @Override
    public void setWidth(double width) {
        side = width;
        super.setHeight(width);
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }
}

package app.graphics;

public class Rectangle implements Printable, Erasable {

    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }


    @Override
    public void print() {
        System.out.println("Height=" + height + ", Width=" + width);
    }

    @Override
    public void printBeautifully() {

        System.out.println("Height: " + height + "cm, width: " + width + "cm");
    }

    @Override
    public void erase() {
        System.out.println("Erase: Rectangle");
    }
}

package app;

import app.graphics.Printable;
import app.graphics.Rectangle;
import app.graphics.Text;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Rectangle rect = new Rectangle(23.4, 57.9);
        Text text = new Text("Hello world", true, false);

        ArrayList<Printable> printables = new ArrayList<>();

        printables.add(rect);
        printables.add(text);

        for (Printable printable : printables) {
            printable.print();
            printable.printBeautifully();
        }

        printables.forEach(p -> { p.print();
                                    p.printBeautifully();});



    }
}
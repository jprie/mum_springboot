import org.example.Rectangle;
import org.example.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void testLiskovPrinciple() {

        // Erzeuge Square, aber verwende Rect Referenz
        Rectangle rect = new Square(3.5);

        // User glaubt es ist ein Rect und sollte sich auch so verhalten
        System.out.println(rect);

        // Annahme, es ist ein Rect, dann müsste height gleich bleiben, wenn ich width ändere
        rect.setWidth(2.4);

        // das stimmt aber nicht, da ich gegen das LP verstoßen habe
        assertEquals(rect.getHeight(), 3.5);

    }

}

import org.example.Rectangle;
import org.example.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void testLiskovPrinciple() {

        Rectangle rect = new Square(3.5);

        System.out.println(rect);

        rect.setWidth(2.4);

        assertEquals(rect.getHeight(), 3.5);

    }

}

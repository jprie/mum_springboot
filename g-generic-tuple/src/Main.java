import app.*;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {


        Tuple<String> stringTuple = new Tuple<>("Left", "Right");

        stringTuple.setL("first");
        stringTuple.setR("1");
        Book spieldesengels = new Book("Spiel des Engels", new Author("Carlos", "Ruiz Zafon"), Genre.HISTORICAL_FICTION,
                Book.BookType.BROCHURE, 437, new BigDecimal("34.99"));

        Book javainsel = new Book("Java ist auch eine Insel", new Author("Christian", "Ullenbloom"), Genre.TECHNOLOGY,
                Book.BookType.HARDCOVER, 1450, new BigDecimal("59.99"));


        Tuple<Book> bookTuple = new Tuple<>(spieldesengels, javainsel);

        // ist ein Object-Tuple besser?

        ObjectTuple bookObjectTuple = new ObjectTuple(spieldesengels, javainsel);

        // Cast, dient dazu, aus einer Referenz einen anderen Referenz-Typ zu machen


        //Book b1 = (Book)bookObjectTuple.getL();
        if (bookObjectTuple.getL() instanceof Book b) {

//            Book b = (Book)bookTuple.getL();
            System.out.println(b);
        }

    }
}
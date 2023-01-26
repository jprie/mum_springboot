import app.Author;
import app.Book;
import app.Genre;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Book kafka = new Book("Kafka am Strand", new Author("Haruki", "Murakami"), Genre.FANTASY,
                Book.BookType.E_BOOK, 230, new BigDecimal("23.56"));

        Book karamasov = new Book("Brüder Karamasov", new Author("Fjodor", "Dostojevski"), Genre.DRAMA,
                Book.BookType.HARDCOVER, 650, new BigDecimal("19.34"));

        Book spieldesengels = new Book("Spiel des Engels", new Author("Carlos", "Ruiz Zafon"), Genre.HISTORICAL_FICTION,
                Book.BookType.BROCHURE, 437, new BigDecimal("34.99"));

        Book javainsel = new Book("Java ist auch eine Insel", new Author("Christian", "Ullenbloom"), Genre.TECHNOLOGY,
                Book.BookType.HARDCOVER, 1450, new BigDecimal("59.99"));

        ArrayList<Book> books = new ArrayList<>(List.of(kafka, karamasov, spieldesengels, javainsel));

        System.out.println(books);

        // Versuch: Books ohne weitere Maßnahmen sortieren
        Collections.sort(books);

        System.out.println("Natürliche Reihenfolge: " + books);

        // Idee: Bücher nach Autor sortieren
        Comparator<Book> authorComparator = new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getAuthor().getLastName().compareTo(o2.getAuthor().getLastName());
            }
        };

        Collections.sort(books, authorComparator);

        System.out.println("Nach Autor sortiert: " + books);

        // Sabotiere mutwillig den PriceComparator

        final int offset = 7; // muss effective-final sein

        Comparator<Book> priceComparator = (b1, b2) -> b1.getPrice().compareTo(b2.getPrice())-offset;

        Collections.sort(books, (book1, book2) -> book1.getGenre().compareTo(book2.getGenre()));


        Collections.sort(books, priceComparator);

        System.out.println("Nach Preis sortiert: " + books);

        Comparator<Book> numberPagesComparator = Comparator.comparing(Book::getNumberPages).reversed().thenComparing(Book::getBookType);

        Collections.sort(books, numberPagesComparator);

        System.out.println("Sortiert nach Seitenanzahl: " + books);
    }
}






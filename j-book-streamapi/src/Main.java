import app.Author;
import app.Book;
import app.Genre;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        // Liste von Autoren erstellen

        var authors = new ArrayList<Author>();
        for (var book : books) {
            authors.add(book.getAuthor());
        }

        System.out.println("Autoren: " + authors);


        //        var authorList = books.stream().map(book -> book.getAuthor()).toList();
        var authorList = books.stream()
                .filter(book -> book.getPrice().compareTo(new BigDecimal("20.3")) > 0)
                .map(Book::getAuthor)
                .sorted(Comparator.comparing(Author::getLastName).reversed())
                .toList();

        System.out.println("Autoren aus Stream: " + authorList);

        // Generators / Erzeuger
        Random random = new Random();
        var randomNumberList = Stream.generate(Author::randomAuthor)
                                                .limit(10)
                                                .toList();


        System.out.println(randomNumberList);

        final long limit = 10_000_000_0l;
        // Iterator
        long start = System.currentTimeMillis();
        var randomIntegerList = Stream.iterate(0, i -> i + 5).limit(limit).toList();
        long end = System.currentTimeMillis();

        System.out.println("Auto-boxed version: " + (end - start));


        System.out.println(randomIntegerList);

        // bessere Performance, da erst zum Schluss geboxed wird!
        start = System.currentTimeMillis();
        var randomIntList = IntStream.iterate(0, i -> i + 5).limit(limit).boxed().toList();
        end = System.currentTimeMillis();

        System.out.println("manual boxed version: " + (end - start));
    }
}
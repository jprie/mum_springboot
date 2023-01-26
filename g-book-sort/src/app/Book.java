package app;

import java.math.BigDecimal;

public class Book implements Comparable<Book> {

    private String title;

    private Author author;

    private Genre genre;

    private BookType bookType;

    private int numberPages;

    private BigDecimal price;

    /* Das Interface Comparable ist dafür da, die natürliche Sortierreihenfolge für Objekte zu definieren
        für Typen der Java API, wie String, BigDecimal, LocalDate, ... ist Comparable implementiert!!
     */
    @Override
    public int compareTo(Book otherBook) {
        // < 0
        // == 0
        // > 0
        return this.title.compareTo(otherBook.title);
    }

    public enum BookType {
        HARDCOVER, BROCHURE, E_BOOK
    }

    public Book(String title, Author author, Genre genre, BookType bookType, int numberPages, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookType = bookType;
        this.numberPages = numberPages;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", bookType=" + bookType +
                ", numberPages=" + numberPages +
                ", price=" + price +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public BookType getBookType() {
        return bookType;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

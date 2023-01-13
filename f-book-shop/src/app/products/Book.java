package app.products;

import java.math.BigDecimal;

public class Book extends Product {

    public enum BookType {
        HARDCOVER, BROCHURE, E_BOOK
    }

    private String title;

    private Author author;

    private Genre genre;

    private BookType type;

    public Book(long id, int units, BigDecimal price,
                String title, Author author, Genre genre, BookType type) {
        super(id, units, price);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean containsKeyword(String keyword) {
        return title.contains(keyword) || author.getFirstName().contains(keyword) ||
                author.getLastName().contains(keyword);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }
}

package app;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.stream.Stream;

public class Author {

    public static Author randomAuthor() {

        Random random = new Random();
        byte[] buffer = new byte[20];

        random.nextBytes(buffer);

        String firstName = null;
        try {
            firstName = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        random.nextBytes(buffer);
        String lastName = null;
        try {
            lastName = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


        var author = new Author(firstName, lastName);
        return author;
    }
    private String firstName;

    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

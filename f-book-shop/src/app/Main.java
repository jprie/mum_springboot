package app;

import app.products.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Book mister = new Book(1, 20, new BigDecimal("25.60"),
                "Mister Aufziehvogel", new Author("Haruki", "Murakami"),
                Genre.DRAMA, Book.BookType.BROCHURE);

        Newspaper todaysPresse = new Newspaper(2, 250, new BigDecimal("5.60"),
                "Die Presse", "Austria", LocalDate.now(), 230458);

        Newspaper todaysWSJ = new Newspaper(3, 40, new BigDecimal("3.40"),
                "Wallstreet Journal", "US", LocalDate.of(2023,1,5),
                150123);

        Magazine autoMotorUndSport = new Magazine(4, 200, new BigDecimal("23.40"),
                "Auto Motor und Sport", Genre.ADVENTURE, Appearance.MONTHLY, 3);

        Magazine cT = new Magazine(5, 150, new BigDecimal("13.40"),
                "C't", Genre.TECHNOLOGY, Appearance.MONTHLY, 5);

        ArrayList<Book> books = new ArrayList<>();
        books.add(mister);
//        books.add(cT);


        ArrayList<Product> products = new ArrayList<>();
        products.add(mister);
        products.add(cT);
        products.add(todaysWSJ);
        products.add(autoMotorUndSport);
        products.add(todaysPresse);
        products.add(todaysWSJ);


        // TODO: filter
        String keyword = "Auto";
        ArrayList<Product> filteredProducts = filterProductsByKeyword(keyword, products);

        System.out.println("Products: " + products);

        System.out.println("Products filtered by '" + keyword + "':" + filteredProducts);
    }

    private static ArrayList<Product> filterProductsByKeyword(String keyword, ArrayList<Product> products) {

        ArrayList<Product> filteredList = new ArrayList<>();
        for (Product product : products) {
            if (product.containsKeyword(keyword)) {
                // füge Product zu filteredList hinzu
                filteredList.add(product);
            }
        }
        return filteredList;
    }
}
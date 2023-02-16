import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        // Stream aus Datei erstellen
        try {
            Optional<Customer> optionalCustomer = Files.lines(Path.of("customers.csv"))
                    .skip(1)
                    // man könnte mit regex filtern
                    .distinct()
                    .map(str -> str.split(";"))
                    .map(Customer::createFromArray)
                    //.anyMatch(customer -> customer.getLastName().equals("Hofer"));
                            .findAny();

            System.out.println("irgendein Customer: " + optionalCustomer);

            if (optionalCustomer.isPresent()) {
                var customer = optionalCustomer.get();
            }

            // Überprüft, ob Customer vorhanden und führt Action nur dann aus
            optionalCustomer.ifPresent(System.out::println);

            // Wenn Customer in Optional nicht vorhanden, gib einen anderen Customer zurück
            Customer c = optionalCustomer.orElse(new Customer());
            // Macht das Gleiche, kann aber Methodenreferenz verwenden
            Customer c2 = optionalCustomer.orElseGet(() -> new Customer());
            // Wirft RuntimeException falls Customer nicht vorhanden
            Customer c3 = optionalCustomer.orElseThrow();


            String lastName = optionalCustomer.map(Customer::getLastName).toString();
            System.out.println(lastName);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
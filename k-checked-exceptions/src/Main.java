import exceptions.SpecialDukeGifFileNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean doExit = false;
        do {
            System.out.println("Enter a gif filename: ");
            String filename = scanner.next();
            // Idee: ich brauche HIER in dieser Methode, Information darüber, ob das Lesen
            // funktioniert hat.
            // Und das geht mit Exceptions!!
            try {
                readFileSize(filename);
                doExit = true;
            } catch (FileNotFoundException e) {
                System.err.println("File not found");
            } catch (IOException e) {
                System.err.println("Error reading from file");
            }
        } while (!doExit);

        try {
            throwsExceptionBeware();
        } catch (SpecialDukeGifFileNotFoundException e) {
            System.err.println(e.getMessage());
        }


    }

    private static void readFileSize(String filename) throws FileNotFoundException, IOException {
        // Runtime Exceptions überprüft der Compiler nicht,
        // aber Checked Exceptions geben einen Fehler, wenn sie nicht
        // behandelt werden.

        // Falls Ressource Auto-Closable kann man 'try mit Ressource' verwenden
        try (RandomAccessFile file = new RandomAccessFile(filename, "r");) {
            file.seek(6);
            System.out.printf("Size: %sx%s",
                    file.read() + (file.read() << 8),
                    file.read() + (file.read() << 8));

        }
//        } catch (FileNotFoundException e) {
//            System.err.println("File not found!");
//        } catch(IOException e) { // FileNotFoundException wird automatisch mitgefangen!!
//            System.err.println("Problem reading from file!");
//
//        }
    }

    public static void throwsExceptionBeware() throws SpecialDukeGifFileNotFoundException {
        throw new SpecialDukeGifFileNotFoundException("Duke file not found");
    }
}
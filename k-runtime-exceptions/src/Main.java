public class Main {
    public static void main(String[] args) {

//        int x = 34/0;

        // Runtime Exceptions überprüft der Compiler nicht,
        // aber Checked Exceptions geben einen Fehler, wenn sie nicht
        // behandelt werden.
        int[] array = {1,2,3,4};
        int x = array[4];

        System.out.println("Ergebnis ist: " + x);

    }
}
/**
 * Das ist die Main-Klasse
 */
public class Main {

    /**
     * Java-Docs Kommentare werden zur Dokumentationserzeugung verwendet
     * @param args Argumente-Array, das dem Java-Programm bei der Ausführung übergeben wurde
     */
    public static void main(String[] args) {

        // Gibt Hello World auf der Console aus

        /*
        Gibt Hello
        World auf
        der Console
        aus
         */
        System.out.println("Hello world!");

        // Primitive Datentypen

        boolean truth = true; // 1 bit

        int number = 23; // 32 bit
        long lNumber = 4_000_000_000_000_000L; // 64 bit

        float fNumber = 3.4f; // 32 bit
        double dNumber = 7.2; // 64 bit

        byte aByte = -128; // 8 bit
        short aShort = 23000; // 16 bit

        char character = 'a'; // 16 bit (unsigned), UNICODE-codiert
        char unicodeCharacter = '\u1234';

        System.out.println(unicodeCharacter);

        // Komplexe Datentypen/ Referenz-Datentypen sind solche hinter denen eine Klasse steht
        String str = "Hello complex world";

        // Arrays
        int[] intArray = {1, 2, 3, 4};

        String[] stringArray = {"Alpha", "Bravo", "Charly"};

        int numberFromArray = intArray[3];

        stringArray[2] = "Neuer Wert";

        System.out.println(numberFromArray);
        System.out.println(stringArray[2]);

        for (String s : stringArray) {
            System.out.println(s);
        }

        Person refToObject = new Person();

        Person differentRefToSameObject = refToObject;

//        refToObject.name = "Johannes";
        refToObject.setName("Johannes");

        System.out.println(differentRefToSameObject.getName());

        refToObject.setAge(32);

        System.out.println(refToObject.getAge());

        System.out.println(refToObject.getName() + " is " + refToObject.getAge() + " years old");

        System.out.println(String.format("%s is %d years old", refToObject.getName(), refToObject.getAge()));
        System.out.printf("%s is %d years old\n", refToObject.getName(), refToObject.getAge());

        System.out.println(refToObject);
    }
}
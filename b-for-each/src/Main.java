import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Hier sprechen wir über Java

        char[] characters = new char[] {'a', 'b', 'c'};
        String[] names = new String[] { "Hugo", "Berta", "Andreas", "Lisa", "Franz"};

        System.out.println(Arrays.toString(names));

        // Mache aus Andreas -> Tina
        for (int i=0; i < names.length; i++) {

            if (i==2) {
                names[i] = "Tina";
            }
        }

        System.out.println(Arrays.toString(names));

        // Versuche Array mit for-each nachhaltig zu verändern
        int index = 0;
        for (String name : names) {

            // Wir sehen, das verändert das Array nicht, sondern nur den String name

            if (name.equals("Tina")) {
                //name = "Rudolf";
                names[index] = "Rudolf";
            }

            index++;
        }

        System.out.println(Arrays.toString(names));

        // do - while Schleife

        int i = 0;
        do {
            // index 4 überspringen
            if (i==4) {
                i++;
                continue;
            }

            if (i>5) {
                break;
            }

            System.out.println("do - while: index=" + i);
            i++;
        } while(i<9);





    }
}
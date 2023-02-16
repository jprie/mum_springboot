import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String[] array = new String[] {"Hello", "World"};

        String string = array[0] + array[1];

        char[] charArray = new char[string.length()];

        string.getChars(0, string.length(), charArray, 0);

        String[] strings = String.valueOf(charArray).split("");

        System.out.println(Arrays.toString(strings));

        var list = Arrays.stream(strings).distinct().toList();

        System.out.println(list);

        ///////////

        var list2 = Arrays.stream(array)
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .toList();

        System.out.println(list2);
    }
}
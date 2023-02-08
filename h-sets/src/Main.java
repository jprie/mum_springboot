import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        // bei 'var' wird der Typ aus dem Kontext bestimmt
        var list = List.of("Hansi", "Otto", "Karli");

        System.out.println(list);

        TreeSet<String> stringTreeSet = new TreeSet<>(list);
        System.out.println(stringTreeSet);


        TreeSet<Student> studentTreeSet = new TreeSet<>();

        // Elemente die man in ein TreeSet einfügen möchte, müssen Comparable<T> implementieren
        studentTreeSet.add(new Student("Karl", "Bauer"));

        System.out.println(studentTreeSet);


        var treeSetWithNull = new TreeSet<Student>(
                (s1, s2) -> {
                    if (s1 != null && s2 != null) {
                        return s1.getFirstName().compareTo(s2.getFirstName());
                    } else {
                        return -1;
                    }
                });



    }
}
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Ein Konstruktor no-args
        ArrayList<String> arrayList = new ArrayList<>();

        List<String> list2 = new ArrayList<>(List.of("Hansi", "Otto", "Karli"));

        // Wenn ich  vorhabe 256 Elemente zu speichern, kann ich die initialCapacity auf 256 setzen
        ArrayList<String> list3 = new ArrayList<>(16);

        List<String> linkedList = new LinkedList<>(list2);

        // List vs. ArrayList

        arrayList.ensureCapacity(256);

//        list2.ensureCapacity(256); geht nicht, weil ich nur List-Methoden sehe

        LinkedList<String> linkedList2 = new LinkedList<>();

        // LIFO - Mechanismus
        linkedList2.push("Element");
        System.out.println(linkedList2);
        linkedList2.push("Element2");
        System.out.println(linkedList2);
        linkedList2.push("Element3");
        System.out.println(linkedList2);

        linkedList2.pop();
        System.out.println(linkedList2);
        linkedList2.pop();
        System.out.println(linkedList2);
        linkedList2.pop();
        System.out.println(linkedList2);


        printList(arrayList);
        printList(linkedList2);


    }

    public static void printList(List<String> list) {

        System.out.println(list);
    }
}
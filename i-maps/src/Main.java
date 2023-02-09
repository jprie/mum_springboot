import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Map<String, Student> stringStudentMap = new HashMap<>();

        stringStudentMap.put("1234", new Student("Johannes", "Priebsch"));

        Student student = stringStudentMap.get("1234");

        stringStudentMap.put("5678", new Student("Ralf", "Bauer"));

        Set<String> keys = stringStudentMap.keySet();

        System.out.println(keys);

        Collection<Student> students = stringStudentMap.values();

        System.out.println(students);
    }
}
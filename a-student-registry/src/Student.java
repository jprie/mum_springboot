import java.util.Arrays;

public class Student {

    private long id; // default: 0L
    private String name; // default: null-Referenz
    private int[] courses; // default: null-Referenz

    /*
    boolean, default: false
    int, default: 0
    float, default: 0.0f
    double, default: 0.0
    char, default: '\u0000'
    byte, default: 0 (8 bit)
    short, default: 0 (16 bit)

    alle Referenzen, default: null
     */

//    public Student(long id, String name, int[] courses) {
//        this.id = id;
//        this.name = name;
//        this.courses = courses;
//    }

    // no-args constructor
    public Student() {

        // kein Code
        // Attribute werden implizit auf default-Werte gesetzt
    }

    // Kanonischer Konstruktor ("canonical")
    public Student(long id, String name, int[] courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getCourses() {
        return courses;
    }

    public void setCourses(int[] courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courses=" + Arrays.toString(courses) +
                '}';
    }
}

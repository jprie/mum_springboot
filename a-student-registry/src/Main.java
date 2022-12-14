public class Main {
    public static void main(String[] args) {


        System.out.println("Hello world!");

        // default-constructor wird nur automatisch
        // bereitgestellt, so lange kein Konstruktor
        // explizit definiert wurde.
        Student defaultStudent = new Student();

        Student student = new Student(1L, "Johannes",
                                    new int[] {1,2,3} );


        // Student auf Null-Werte initialisiert
        System.out.println(student);

        int[] myCourses = {336, 359};

        student.setId(2);
        student.setName("Johannes");
        student.setCourses(myCourses);

        System.out.println(student);
    }
}
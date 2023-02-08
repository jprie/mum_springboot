public class Student implements Comparable<Student> {

    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student otherStudent) {
        return lastName.compareTo(otherStudent.lastName);
    }

    public String getFirstName() {
        return firstName;
    }
}

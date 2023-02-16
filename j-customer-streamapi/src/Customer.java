import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Customer {

    public Customer() {

    }

    public enum Membership {
        PLATIN, SILBER, GOLD
    }

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String address;
    private String email;
    private String phone;
    private Membership membership;

    public static Customer createFromArray(String[] array) {

        // Entpacken des arrays
        long id = Long.parseLong(array[0]);
        String firstName = array[4];
        String lastName = array[3];
        LocalDate birthday;
        try {
            birthday = LocalDate.parse(array[5]); // TODO
        } catch (DateTimeParseException e) {
            System.err.println("Date " + array[5] + " could not be parsed");
            birthday = LocalDate.now();
        }
        String address = array[1];
        String email = array[2];
        String phone = array[6];
        Membership membership = Membership.valueOf(array[7].toUpperCase());

        return new Customer(id, firstName, lastName, birthday, address, email, phone, membership);
    }

    public Customer(long id, String firstName, String lastName, LocalDate birthday,
                    String address, String email, String phone, Membership membership) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", membership=" + membership +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}

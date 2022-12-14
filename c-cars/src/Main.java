import cars.Car;
import cars.SportsCar;

public class Main {

    // Klassen-Attribute und Methoden können aufgerufen werden, BEVOR ein Objekt erstellt wurde
    public static void main(String[] args) {

        // Erzeugen Sie 3 Autos Ihrer Wahl
        Car fiatPunto = new Car("Fiat", "Punto");
        SportsCar ferrariTestarossa = new SportsCar("Ferrari", "Testarossa");
        Car fordFocus = new Car("Ford", "Focus");

        // Für die Klasse die numberWheels verändern
        Car.setNumberWheels(17);

        System.out.println(fiatPunto);
        System.out.println(ferrariTestarossa);
        System.out.println(fordFocus);
    }
}
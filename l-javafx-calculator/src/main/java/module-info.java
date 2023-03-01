module com.example.ljavafxdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ljavafxdemo to javafx.fxml;
    exports com.example.ljavafxdemo;
}
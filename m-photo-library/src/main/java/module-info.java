module com.example.mphotolibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;
    requires org.slf4j;

    opens com.example.mphotolibrary to javafx.fxml;
    exports com.example.mphotolibrary;
    exports com.example.mphotolibrary.tablecell;
    opens com.example.mphotolibrary.tablecell to javafx.fxml;
}
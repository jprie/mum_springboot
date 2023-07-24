module com.example.mphotolibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;
    requires org.slf4j;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.net.http;

    opens com.example.mphotolibrary to javafx.fxml;
    exports com.example.mphotolibrary;
    exports com.example.mphotolibrary.tablecell;
    opens com.example.mphotolibrary.tablecell to javafx.fxml;
}

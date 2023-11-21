module com.practica.coches_hibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;

    opens com.practica.coches_hibernate to javafx.fxml;
    exports com.practica.coches_hibernate;
}
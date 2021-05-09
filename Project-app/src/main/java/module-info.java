module io.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.controlsfx.controls;
    requires org.postgresql.jdbc;
    requires javafx.graphics;

    opens io.project;
    exports io.project;

    exports  io.project.entities;
    opens  io.project.entities;

}
module project.autocar_ticket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens project.autocar_ticket to javafx.fxml;
    exports project.autocar_ticket;
    exports Modules;
    opens Modules to javafx.fxml;
}
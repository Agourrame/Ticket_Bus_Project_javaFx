module project.autocar_ticket {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.autocar_ticket to javafx.fxml;
    exports project.autocar_ticket;
}
package project.autocar_ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class LoginpageController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    void close(ActionEvent event) {
        Stage stage2;
        stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.close();
    }

    @FXML
    void minimize(ActionEvent event) {
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}

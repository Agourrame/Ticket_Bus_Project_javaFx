package project.autocar_ticket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.*;
public class BusController {

    private Button addbus;

    @FXML
    private void Addbus(MouseEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Addbuspage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            Stage stage=new Stage();
            stage.setTitle("Add Bus");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){

        }
    }
}

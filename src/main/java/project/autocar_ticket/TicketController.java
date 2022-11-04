package project.autocar_ticket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class TicketController {
    @FXML
    private Button btnadd;

    @FXML
    private void CreatTicketpage(MouseEvent event){
       try{
           FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Createticketpage.fxml"));
           Scene scene = new Scene(fxmlLoader.load(), 400, 400);
           Stage stage=new Stage();
           stage.setTitle("Create Ticket");
           stage.setScene(scene);
           stage.show();
       }catch (Exception e){

       }
    }



}

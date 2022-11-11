package project.autocar_ticket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML
    private BorderPane AllPage;

    @FXML
    private AnchorPane homepage;

    @FXML
    private void home(MouseEvent event){

        AllPage.setCenter(homepage);
    }
    @FXML
    private void chauffeur(MouseEvent event){

        loadpage("Chauffeur");
    }

    @FXML
    private void bus(MouseEvent event){
        loadpage("Buspage");
    }

    @FXML
    private void reservation(MouseEvent event){

        loadpage("Reservationpage");
    }



    private void loadpage(String page){
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AllPage.setCenter(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

package project.autocar_ticket;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ChauffeurController implements Initializable {


    //database
    String url = "jdbc:mysql://localhost/gestion_de_teckit";
    String username="root";
    String password="";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //--------------------------
    @FXML
    private TextField nameinput;
    @FXML
    private TextField cininput;
    //--------------------------

    /*
    @FXML
    private Button addch;
    @FXML
    private Button updatwch;
    @FXML
    private Button deletech;
    */

    //--------------------------
    @FXML
    private TableColumn tableid;
    @FXML
    private TableColumn tablename;
    @FXML
    private TableColumn tablecin;
    //--------------------------
    @FXML
    void addch(MouseEvent event){

    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            con = DriverManager.getConnection(this.url,username,password);
            System.out.println("Good");
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }


}

package project.autocar_ticket;

import Modules.Bus;
import Modules.chauffeur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChauffeurController implements Initializable {

    //database
    String url = "jdbc:mysql://localhost/gestion_de_teckit";
    String username="root";
    String password="";
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    //--------------------------
    @FXML
    private TextField nameinput;
    @FXML
    private TextField cininput;
    //--------------------------
    @FXML
    private TableView<chauffeur> table;
    @FXML
    private TableColumn<chauffeur,String> tablecin= new TableColumn<>();

    @FXML
    private TableColumn<chauffeur,Integer> tableid= new TableColumn<>();

    @FXML
    private TableColumn<chauffeur,String> tablename= new TableColumn<>();
    //--------------------------
    @FXML
    void addChauffeur(ActionEvent event){
        try {
            stmt=con.prepareStatement("INSERT INTO `chauffeur` (`nom`, `cin`) VALUES ('"+nameinput.getText()+"','"+cininput.getText()+"');");
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void updateChauffeur(ActionEvent event) {
        int id=table.getSelectionModel().getSelectedItem().getId();
        try {
            stmt=con.prepareStatement("UPDATE `chauffeur` SET nom='"+nameinput.getText()+"',cin='"+cininput.getText()+"' WHERE id='"+id+"'");
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void deleteChauffeur(ActionEvent event) {
        int id=table.getSelectionModel().getSelectedItem().getId();
        try {
            stmt=con.prepareStatement("Delete FROM `chauffeur` WHERE id='"+id+"'");
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void uploadChauffeurInfo(){
        try{
            stmt=con.prepareStatement("SELECT * FROM `chauffeur`");
            rs=stmt.executeQuery();

            while (rs.next()){
                chauffeur newchauffeur=new chauffeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("cin")
                );
                this.table.getItems().add(newchauffeur);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    void getSelectedItem(){
        table.setOnMouseClicked(event ->{
            nameinput.setText(table.getSelectionModel().getSelectedItem().getNom());
            cininput.setText(table.getSelectionModel().getSelectedItem().getCin());
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            con = DriverManager.getConnection(this.url,username,password);
            System.out.println("Good");
        }catch (Exception e){
            System.out.println(e.toString());
        }
        uploadChauffeurInfo();
        getSelectedItem();

        tableid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablename.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tablecin.setCellValueFactory(new PropertyValueFactory<>("cin"));
    }
}

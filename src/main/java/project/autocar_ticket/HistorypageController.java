package project.autocar_ticket;
import Modules.History;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class HistorypageController implements Initializable {

    //Database----------------------------------------

    String url = "jdbc:mysql://localhost/gestion_de_teckit";
    String username="root";
    String password="";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //----------------------------------------------

    @FXML
    private TableColumn<History, String> cincol;

    @FXML
    private TableColumn<History, String> datecol;

    @FXML
    private TableColumn<History, String> endcol;

    @FXML
    private TableColumn<History, Integer> idcol;

    @FXML
    private TableColumn<History, String> namecol;

    @FXML
    private TableColumn<History, String> startcol;

    //----------------------------------------

    @FXML
    private DatePicker datesearch;

    //---------------------------------------

    @FXML
    private TableView<History> tablehistory;

    //---------------------------------------
    @FXML
    void searchbtn(MouseEvent event) {


        try {
            ps=con.prepareStatement("SELECT reservation.id,reservation.nom,reservation.today,reservation.cin,bus.Vd,bus.Va FROM reservation, bus WHERE reservation.busid = bus.id AND reservation.today='"+datesearch.getValue().toString()+"'");
            rs=ps.executeQuery();

            while (rs.next()){
                History history=new History(
                        rs.getInt("reservation.id"),
                        rs.getString("reservation.nom"),
                        rs.getString("reservation.cin"),
                        rs.getString("reservation.today"),
                        rs.getString("bus.Vd"),
                        rs.getString("bus.Va")
                );
                this.tablehistory.getItems().add(history);

            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            con = DriverManager.getConnection(this.url,username,password);
            System.out.println("Good");
        }catch (Exception e){
            System.out.println(e.toString());
        }


        idcol.setCellValueFactory(new PropertyValueFactory("id"));
        namecol.setCellValueFactory(new PropertyValueFactory("nom"));
        cincol.setCellValueFactory(new PropertyValueFactory("cin"));
        datecol.setCellValueFactory(new PropertyValueFactory("date"));
        startcol.setCellValueFactory(new PropertyValueFactory("start"));
        endcol.setCellValueFactory(new PropertyValueFactory("end"));

    }

<<<<<<< HEAD
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class HistorypageController {
=======
>>>>>>> a4745a6753ed8f723ba3590ad6cb4b5d4a3cbdfb

    @FXML
    void close(ActionEvent event) throws IOException {
        Stage stage1;
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.close();
    }
}

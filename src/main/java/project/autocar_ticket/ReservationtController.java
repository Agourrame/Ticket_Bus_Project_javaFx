package project.autocar_ticket;

import Modules.Bus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReservationtController implements Initializable {

    //Database----------------------------------------

    String url = "jdbc:mysql://localhost/gestion_de_teciket";
    String username="root";
    String password="";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //----------------------------------------------

    @FXML
    private TextField cininput;
    @FXML
    private TextField dateinput;
    @FXML
    private TextField endinput;
    @FXML
    private TextField nameinput;
    @FXML
    private TextField priceinput;
    @FXML
    private TextField startinput;

    //----------------------------------------------

    @FXML
    private TableView<Bus> tablebus;
    @FXML
    private TableColumn<Bus,String> datecol;
    @FXML
    private TableColumn<Bus,String> endcol;
    @FXML
    private TableColumn<Bus,Integer> nbcol;
    @FXML
    private TableColumn<Bus,Integer> pricecol;
    @FXML
    private TableColumn<Bus,String> startcol;

    //----------------------------------------------

    @FXML
    private ComboBox<String> endCom;
    @FXML
    private ComboBox<String> startCom;

    //----------------------------------------------







    @FXML
    void search(MouseEvent event) {
        this.tablebus.getItems().clear();
        try{
            ps=con.prepareStatement("select Vd,Va,prix,Nplace,date from bus where Vd='"+startCom.getSelectionModel().getSelectedItem()+"' and Va='"+endCom.getSelectionModel().getSelectedItem()+"'");
            rs=ps.executeQuery();

            while (rs.next()){
                 Bus newbus=new Bus(
                         rs.getString("Vd"),
                         rs.getString("Va"),
                         rs.getInt("prix"),
                         rs.getInt("Nplace"),
                         rs.getString("date")
                         );

                 this.tablebus.getItems().add(newbus);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }


    @FXML
    void reserver(MouseEvent event) {

    }





    public void close(MouseEvent event) throws IOException {
        Stage stage1;
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            con = DriverManager.getConnection(this.url,username,password);
            System.out.println("Good");
        }catch (Exception e){
            System.out.println(e.toString());
        }


        this.startCom.getItems().addAll("taroudant","agadir","tanger");
        this.endCom.getItems().addAll("taroudant","agadir","tanger");

        startcol.setCellValueFactory(new PropertyValueFactory("start"));
        endcol.setCellValueFactory(new PropertyValueFactory("end"));
        pricecol.setCellValueFactory(new PropertyValueFactory("prix"));
        nbcol.setCellValueFactory(new PropertyValueFactory("nplace"));
        datecol.setCellValueFactory(new PropertyValueFactory("date"));


        this.tablebus.setOnMouseClicked(event -> {
            startinput.setText(tablebus.getSelectionModel().getSelectedItem().getStart());
            endinput.setText(tablebus.getSelectionModel().getSelectedItem().getEnd());
            //dateinput.setValue(LocalDate.parse(tablebus.getSelectionModel().getSelectedItem().getDate()));

        });

    }
}

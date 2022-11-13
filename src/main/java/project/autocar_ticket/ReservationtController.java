package project.autocar_ticket;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservationtController  {

    public void close(MouseEvent event) throws IOException {
        Stage stage1;
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.close();
    }

    public TextField Name,Cin,Vd,Va,NBus,Price;
    public DatePicker Date;
    public TableView<Rese> tablereservation;

    @FXML
    public TableColumn<Rese,String> colmunName = new TableColumn<>();
    public TableColumn<Rese,Integer> colmunCin = new TableColumn<>();
    public TableColumn<Rese,String> colmunVd = new TableColumn<>();
    public TableColumn<Rese,String> colmunVa = new TableColumn<>();
    public TableColumn<Rese,String> colmunDate = new TableColumn<>();
    public TableColumn<Rese,String> colmunNBus= new TableColumn<>();
    public TableColumn<Rese,String> colmunPrice= new TableColumn<>();

    //Database
    String url = "jdbc:mysql://localhost/test";
    String username="root";
    String password="";
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;


    public void initialize (){
        try {
            con = DriverManager.getConnection(url,username,password);
            System.out.println("Good");
        }catch (Exception e){
            System.out.println(e.toString());
        }

        colmunName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colmunCin.setCellValueFactory(new PropertyValueFactory<>("Cin"));
        colmunVd.setCellValueFactory(new PropertyValueFactory<>("Start Place"));
        colmunVa.setCellValueFactory(new PropertyValueFactory<>("End Place"));
        colmunDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colmunNBus.setCellValueFactory(new PropertyValueFactory<>("Bus No"));
        colmunPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    @FXML
    public void Reserver(){
        int BusNum= Integer.parseInt(NBus.getText());
        Rese rese = new Rese(Name.getText(),Cin.getText(),Vd.getText(),Va.getText(),Date.getValue().toString(),BusNum,Price.getText());

        tablereservation.getItems().add(rese);
    }

}

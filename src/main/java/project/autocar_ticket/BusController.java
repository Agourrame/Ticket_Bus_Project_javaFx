package project.autocar_ticket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    public TextField rn,startplace,endplace,price,nplace;
    public ComboBox<String> chauffeur;
    public DatePicker date;
    public TableView<Autocar>tableautocar;

    @FXML
    public TableColumn<Autocar,Integer> colmunrn = new TableColumn<>();
    public TableColumn<Autocar,String> colmunstartplace = new TableColumn<>();
    public TableColumn<Autocar,String> colmunendplace = new TableColumn<>();
    public TableColumn<Autocar,String> colmunchauffeur= new TableColumn<>();
    public TableColumn<Autocar,String> colmundate = new TableColumn<>();
    public TableColumn<Autocar,Integer> colmunprice= new TableColumn<>();
    public TableColumn<Autocar,Integer> colmunnplace= new TableColumn<>();


    //Database
    String url = "jdbc:mysql://localhost/gestion_de_ticket";
    String username="root";
    String password="roukhmi1234";
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

        chauffeur.getItems().addAll("Taroudant","Agadir","Marrakech","Casalanca","Tanja","Titouane");


        colmunrn.setCellValueFactory(new PropertyValueFactory<>("rn"));
        colmunstartplace.setCellValueFactory(new PropertyValueFactory<>("startplace"));
        colmunendplace.setCellValueFactory(new PropertyValueFactory<>("endplace"));
        colmundate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colmunprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colmunnplace.setCellValueFactory(new PropertyValueFactory<>("nplace"));
        colmunchauffeur.setCellValueFactory(new PropertyValueFactory<>("chauffeur"));
    }

    @FXML
    public void addautocar(){
        int autocarprice=Integer.parseInt(price.getText());
        int autocarnplace=Integer.parseInt(nplace.getText());
        Autocar car = new Autocar(rn.getText(),startplace.getText(),endplace.getText(),date.getValue().toString(),autocarprice,autocarnplace,chauffeur.getSelectionModel().getSelectedItem());
        tableautocar.getItems().add(car);
    }


}


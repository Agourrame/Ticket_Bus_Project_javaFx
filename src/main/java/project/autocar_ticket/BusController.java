package project.autocar_ticket;

import Modules.Bus;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BusController {

    ArrayList<String> startcity=new ArrayList<>();
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

    public TextField startplace,endplace,price,nplace;
    public ComboBox<String> chauffeur;
    public DatePicker date;
    public TableView<Bus>tableautocar;

    @FXML
    public TableColumn<Bus,Integer> colmunid = new TableColumn<>();
    public TableColumn<Bus,String> colmunstartplace = new TableColumn<>();
    public TableColumn<Bus,String> colmunendplace = new TableColumn<>();
    public TableColumn<Bus,String> colmunchauffeur= new TableColumn<>();
    public TableColumn<Bus,String> colmundate = new TableColumn<>();
    public TableColumn<Bus,Integer> colmunprice= new TableColumn<>();
    public TableColumn<Bus,Integer> colmunnplace= new TableColumn<>();


    //Database
    String url = "jdbc:mysql://localhost/gestion_de_teckit";
    String username="root";
    String password="";
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;


    void getStartcity(){
        try {
            stmt=con.prepareStatement("select nom from chauffeur");
            rs=stmt.executeQuery();
            while (rs.next()){
                this.startcity.add(rs.getString("nom"));
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }


    public void initialize (){
        try {
            con = DriverManager.getConnection(url,username,password);
            System.out.println("Good");
        }catch (Exception e){
            System.out.println(e.toString());
        }
        getItem();
        getget();
        getStartcity();
        chauffeur.getItems().addAll(startcity);



        colmunid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmunstartplace.setCellValueFactory(new PropertyValueFactory<>("start"));
        colmunendplace.setCellValueFactory(new PropertyValueFactory<>("end"));
        colmunprice.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colmundate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colmunnplace.setCellValueFactory(new PropertyValueFactory<>("nplace"));
        colmunchauffeur.setCellValueFactory(new PropertyValueFactory<>("chauffeur"));
    }

    @FXML
    public void addautocar(){
        int autocarprice=Integer.parseInt(price.getText());
        int autocarnplace=Integer.parseInt(nplace.getText());
        try {
            stmt=con.prepareStatement("INSERT INTO `bus`(`Vd`, `Va`, `prix`, `Nplace`, `date`, `chauffeur`) VALUES (?,?,?,?,?,?)");
            stmt.setString(1,startplace.getText());
            stmt.setString(2,endplace.getText());
            stmt.setInt(3,autocarprice);
            stmt.setInt(4,autocarnplace);
            stmt.setString(5,date.getValue().toString());
            stmt.setString(6,chauffeur.getSelectionModel().getSelectedItem());
            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    void getget() {

        try{
            stmt=con.prepareStatement("SELECT * FROM `bus`");
            rs=stmt.executeQuery();

            while (rs.next()){
                Bus newbus=new Bus(
                        rs.getInt("id"),
                        rs.getString("Vd"),
                        rs.getString("Va"),
                        rs.getInt("prix"),
                        rs.getInt("Nplace"),
                        rs.getString("date"),
                        rs.getString("chauffeur")
                );

                this.tableautocar.getItems().add(newbus);

            }
        }catch (Exception e){
            System.out.println(e);
        }



    }

    void getItem(){
        tableautocar.setOnMouseClicked(event ->{
            startplace.setText(tableautocar.getSelectionModel().getSelectedItem().getStart());
            endplace.setText(tableautocar.getSelectionModel().getSelectedItem().getEnd());
            chauffeur.setValue(tableautocar.getSelectionModel().getSelectedItem().getChauffeur());
            date.setValue(LocalDate.parse(tableautocar.getSelectionModel().getSelectedItem().getDate()));
            price.setText(String.valueOf(tableautocar.getSelectionModel().getSelectedItem().getPrix()));
            nplace.setText(String.valueOf(tableautocar.getSelectionModel().getSelectedItem().getNplace()));

        });
    }

     @FXML
    void deleteautocar(ActionEvent event){
         int id=tableautocar.getSelectionModel().getSelectedItem().getId();
         try{
             stmt=con.prepareStatement("DELETE FROM bus WHERE id='"+id+"'");
             stmt.executeUpdate();
    }catch (Exception e){
             System.out.println(e);
      }
    }


    @FXML
    void updateautocar(ActionEvent event) {
        int autocarprice=Integer.parseInt(price.getText());
        int autocarnplace=Integer.parseInt(nplace.getText());
        int id=tableautocar.getSelectionModel().getSelectedItem().getId();
        try {
            stmt=con.prepareStatement("UPDATE bus SET Vd=?,Va=?,prix=?,Nplace=?,date=?,chauffeur=? WHERE id='"+id+"'");
            stmt.setString(1,startplace.getText());
            stmt.setString(2,endplace.getText());
            stmt.setInt(3,autocarprice);
            stmt.setInt(4,autocarnplace);
            stmt.setString(5,date.getValue().toString());
            stmt.setString(6,chauffeur.getSelectionModel().getSelectedItem());
            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.toString());
        }


    }


}




package project.autocar_ticket;

import Modules.Bus;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BusController {

    ArrayList<String> chauff=new ArrayList<>();
    ArrayList<String> start=new ArrayList<>();
    ArrayList<String> end=new ArrayList<>();
    private Button addbus;
    @FXML
    void minimize(ActionEvent event){
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }



    @FXML
    void close(ActionEvent event) throws IOException {
        Stage stage1;
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.close();
    }

    public TextField startplace,endplace,price,nplace,time;
    public ComboBox<String> chauffeur,startt,endd;
    public DatePicker date,datee;
    public TableView<Bus>tableautocar;

    @FXML
    public TableColumn<Bus,Integer> colmunid = new TableColumn<>();
    public TableColumn<Bus,String> colmunstartplace = new TableColumn<>();
    public TableColumn<Bus,String> colmunendplace = new TableColumn<>();
    public TableColumn<Bus, String> columntime =new TableColumn<>();
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
            stmt=con.prepareStatement("select chauffeur.nom from chauffeur,bus where chauffeur.nom!=bus.chauffeur group by chauffeur.nom");
            rs=stmt.executeQuery();
            while (rs.next()){
                this.chauff.add(rs.getString("nom"));
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    void getstart(){
        try {
            stmt=con.prepareStatement("select Vd from bus");
            rs=stmt.executeQuery();
            while (rs.next()){
                this.start.add(rs.getString("Vd"));
            }
        }catch (Exception e){
                System.out.println(e.toString());
    }

    }
    void getend(){
        try {
            stmt=con.prepareStatement("select Va from bus");
            rs=stmt.executeQuery();
            while (rs.next()){
                this.end.add(rs.getString("Va"));
            }
        }catch (Exception e){
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
        getstart();
        getend();
        chauffeur.getItems().addAll(chauff);
        this.startt.getItems().addAll(start);
        this.endd.getItems().addAll(end);


        colmunid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmunstartplace.setCellValueFactory(new PropertyValueFactory<>("start"));
        colmunendplace.setCellValueFactory(new PropertyValueFactory<>("end"));
        colmunchauffeur.setCellValueFactory(new PropertyValueFactory<>("chauffeur"));
        colmundate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columntime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colmunprice.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colmunnplace.setCellValueFactory(new PropertyValueFactory<>("nplace"));

    }

    void clear(){
        this.tableautocar.getItems().clear();
    }

    @FXML
    public void addautocar(){
        int autocarprice=Integer.parseInt(price.getText());
        int autocarnplace=Integer.parseInt(nplace.getText());
        try {
            stmt=con.prepareStatement("INSERT INTO `bus`(`Vd`, `Va`, `prix`, `Nplace`, `date`, `chauffeur`,`time`) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1,startplace.getText());
            stmt.setString(2,endplace.getText());
            stmt.setInt(3,autocarprice);
            stmt.setInt(4,autocarnplace);
            stmt.setString(5,date.getValue().toString());
            stmt.setString(6,chauffeur.getSelectionModel().getSelectedItem());
            stmt.setString(7,time.getText());
            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        clear();
        getget();
        emptyinput();
    }

    void emptyinput(){
        this.startplace.clear();
        this.endplace.clear();
        this.time.clear();
        this.price.clear();
        this.nplace.clear();
        this.date.getEditor().clear();
        this.chauffeur.getItems().clear();
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
                        rs.getString("chauffeur"),
                        rs.getString("time")
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
            time.setText(tableautocar.getSelectionModel().getSelectedItem().getTime());
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
         clear();
         getget();
         emptyinput();
    }


    @FXML
    void updateautocar(ActionEvent event) {
        int autocarprice=Integer.parseInt(price.getText());
        int autocarnplace=Integer.parseInt(nplace.getText());
        int id=tableautocar.getSelectionModel().getSelectedItem().getId();
        try {
            stmt=con.prepareStatement("UPDATE bus SET Vd=?,Va=?,prix=?,Nplace=?,date=?,chauffeur=?,time=? WHERE id='"+id+"'");
            stmt.setString(1,startplace.getText());
            stmt.setString(2,endplace.getText());
            stmt.setInt(3,autocarprice);
            stmt.setInt(4,autocarnplace);
            stmt.setString(5,date.getValue().toString());
            stmt.setString(6,chauffeur.getSelectionModel().getSelectedItem());
            stmt.setString(7,time.getText());
            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        clear();
        getget();
        emptyinput();
    }

    public void searche(ActionEvent actionEvent) {
        this.tableautocar.getItems().clear();
        try{
            stmt=con.prepareStatement("select * from bus where Vd='"+startt.getSelectionModel().getSelectedItem()+"' and Va='"+endd.getSelectionModel().getSelectedItem()+"'and date='"+datee.getValue().toString()+"'");
            rs=stmt.executeQuery();

            while (rs.next()){
                Bus newbus=new Bus(
                        rs.getInt("id"),
                        rs.getString("Vd"),
                        rs.getString("Va"),
                        rs.getInt("prix"),
                        rs.getInt("Nplace"),
                        rs.getString("date"),
                        rs.getString("chauffeur"),
                        rs.getString("time")
                );

                this.tableautocar.getItems().add(newbus);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}










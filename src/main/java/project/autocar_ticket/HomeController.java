package project.autocar_ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    void minimize(ActionEvent event){
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    void close(ActionEvent event) throws IOException {
        Stage stage2;
        stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.close();
    }

    HashMap<String,Integer> barchatinfo=new HashMap<>();
    HashMap<String,Integer> linechatinfo=new HashMap<>();

    XYChart.Series set=new XYChart.Series<>();
    XYChart.Series set2=new XYChart.Series<>();

    //Database
    String url = "jdbc:mysql://localhost/gestion_de_teckit";
    String username="root";
    String password="";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //-------------------- barchart

    @FXML
    private CategoryAxis X;

    @FXML
    private NumberAxis Y;

    @FXML
    private BarChart<?, ?> barchartbus;

    //-------------------- linechart
    @FXML
    private CategoryAxis XL;
    @FXML
    private NumberAxis YL;

    @FXML
    private LineChart<?, ?> LineChartbus;
    //--------------------------------------------
    @FXML
    private Label NbAuto;

    @FXML
    private Label NbChauff;

    @FXML
    private Label Nbticket;

    @FXML
    private Label Nbprofits;
    @FXML
    private Label timelabael;
    @FXML
    private Label ttlab;

    void navbarhomeinformation(){

        try{
            ps=con.prepareStatement("SELECT COUNT(*) as nb FROM reservation");
            rs=ps.executeQuery();

            while (rs.next()){
               this.Nbticket.setText(rs.getString("nb"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        try{
            ps=con.prepareStatement("SELECT COUNT(*) as nb FROM bus");
            rs=ps.executeQuery();

            while (rs.next()){
                this.NbAuto.setText(rs.getString("nb"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        try{
            ps=con.prepareStatement("SELECT COUNT(*) as nb FROM chauffeur");
            rs=ps.executeQuery();

            while (rs.next()){
               this.NbChauff.setText(rs.getString("nb"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        try{
            ps=con.prepareStatement("select sum(bus.prix) from bus,reservation where bus.id=reservation.busid;");
            rs=ps.executeQuery();

            while (rs.next()){
                this.Nbprofits.setText(rs.getString("sum(bus.prix)"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //---------------------------------------------

      void insertintobarchart(){

          try{
              ps=con.prepareStatement("select id,Nplace from bus");
              rs=ps.executeQuery();

              while (rs.next()){
                  String ns=String.valueOf(rs.getInt("id"));
                  barchatinfo.put(ns,rs.getInt("Nplace"));
              }
          }catch (Exception e){

          }

      }

      void barchartinformation(){

          this.barchatinfo.forEach((k,v) -> {
              this.set.getData().add(new XYChart.Data(k,v));
          });

          this.barchartbus.getData().addAll(this.set);
      }



    //------------------------------------------

    @FXML
    void logout(MouseEvent event) {



        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loginpage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 417);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            Stage s1=new Stage();
            Image image=new Image(getClass().getResourceAsStream("img/app logo.png"));
            s1.getIcons().add(image);
            s1.setTitle("BUS BLADI");
            s1.setScene(scene);
            s1.initStyle(StageStyle.UNDECORATED);
            s1.show();
        }catch (Exception e){
            System.out.println(e);
        }
        Stage stage4;
        stage4 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage4.close();

    }

    void insertintolinechart(){

        try{
            ps=con.prepareStatement("select count(*) as nb,day(today) from reservation group by today");
            rs=ps.executeQuery();

            while (rs.next()){
                linechatinfo.put(rs.getString("day(today)"),rs.getInt("nb"));
            }
        }catch (Exception e){

        }


    }

    void linechartinformation(){

        this.linechatinfo.forEach((k,v) -> {
            this.set2.getData().add(new XYChart.Data(k,v));
        });

        this.LineChartbus.getData().addAll(this.set2);
    }

    //------------------------------------------

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
    @FXML
    void History(MouseEvent event) {
        loadpage("Historypage");
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
        try {
            con = DriverManager.getConnection(this.url,username,password);
            System.out.println("Good");
        }catch (Exception e){
            System.out.println(e.toString());
        }

        navbarhomeinformation();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.timelabael.setText(java.time.LocalDate.now().toString());
        this.ttlab.setText(time.format(formatter));

        insertintobarchart();
        barchartinformation();
        insertintolinechart();
        linechartinformation();


    }
}

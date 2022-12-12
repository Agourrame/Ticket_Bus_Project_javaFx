package project.autocar_ticket;

import Modules.Bus;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ReservationtController implements Initializable {

    @FXML
    void minimize(ActionEvent event){
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    ArrayList<String> startcity=new ArrayList<>();

    ArrayList<String> endcity=new ArrayList<>();

    @FXML
    void close(ActionEvent event) throws IOException {
        Stage stage1;
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.close();
    }
    //Database----------------------------------------

    String url = "jdbc:mysql://localhost/gestion_de_teckit";
    String username="root";
    String password="";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //----------------------------------------------

    @FXML
    private TextField cininput;
    @FXML
    private TextField endinput;
    @FXML
    private TextField nameinput;
    @FXML
    private DatePicker dateinputt;
    @FXML
    private TextField startinput;
    @FXML
    private TextField priceinput;
    @FXML
    private TextField idbus;
    @FXML
    private TextField timeinput;

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
    @FXML
    private TableColumn<Bus,String> idcol;
    @FXML
    private TableColumn<Bus,String> timecol;

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
            ps=con.prepareStatement("select * from bus where Vd='taroudant' and Va='"+endCom.getSelectionModel().getSelectedItem()+"'");
            rs=ps.executeQuery();

            while (rs.next()){
                 Bus newbus=new Bus(
                         rs.getInt("id"),
                         rs.getString("Vd"),
                         rs.getString("Va"),
                         rs.getInt("prix"),
                         rs.getInt("Nplace"),
                         rs.getString("date"),
                         rs.getString("time")
                         );

                 this.tablebus.getItems().add(newbus);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }


    @FXML
    void reserver(MouseEvent event) throws IOException {
        String name=nameinput.getText();
        String CIN=cininput.getText();
        int idid=Integer.parseInt(idbus.getText());
        int numberofplace=getnumberofplace(idid);
        int newnum=(numberofplace-1);

        try{
            ps=con.prepareStatement("INSERT INTO `reservation` (`nom`, `cin`, `busid` ,`today`) VALUES ('"+name+"','"+CIN+"','"+idid+"','"+java.time.LocalDate.now()+"')");
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.toString());
        }

        try{
            ps=con.prepareStatement("UPDATE bus set Nplace="+newnum+" where id="+idid+"");
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.toString());
        }

        try{
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;

            File reserv=new File("Tickets/ticket_"+dateFormat.format(date)+".txt");

              if(!reserv.exists()){
                reserv.createNewFile();
               }
              PrintWriter pw=new PrintWriter(reserv);
            pw.println("*************** "+java.time.LocalDate.now()+" ***********");
            pw.println("***************** Welcome ************");
            pw.println("*  Start city : "+startinput.getText());
            pw.println("*  end city   : "+endinput.getText());
            pw.println("*  date       : "+dateinputt.getValue().toString());
            pw.println("*  price      : "+priceinput.getText()+"Dhs");
            pw.println("*  Time       : "+timeinput.getText());
            pw.println("*  N°         : "+numberofplace);
            pw.println("**************************************");
            pw.println("************* Bus Bladi **************");
            pw.close();
            System.out.println("your tickt is ready");
            }catch (Exception e){
            System.out.println(e);
            }

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Status");
        alert.setHeaderText(null);
        alert.setContentText("ticket added succssefuly");
        alert.showAndWait();

        clearinput();
    }

    void getStartcity(){
        try {
            ps=con.prepareStatement("select Vd from bus");
            rs=ps.executeQuery();
            while (rs.next()){
                this.startcity.add(rs.getString("Vd"));
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    void getEndcity(){
        try {
            ps=con.prepareStatement("select Va from bus");
            rs=ps.executeQuery();
            while (rs.next()){
                this.endcity.add(rs.getString("Va"));
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    int getnumberofplace(int nb){
        ArrayList<Integer> val=new ArrayList<>();
        try {
            ps=con.prepareStatement("select Nplace from bus where id="+nb+"");
            rs=ps.executeQuery();
            while (rs.next()){
              val.add(rs.getInt("Nplace"));
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return val.get(0);
    }



    public void close(MouseEvent event) throws IOException {
        Stage stage1;
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.close();
    }

    public void clearinput(){
       this.timeinput.setText("");
       this.endinput.setText("");
       this.startinput.setText("");
       this.priceinput.setText("");
       this.cininput.setText("");
       this.dateinputt.setValue(null);
       this.nameinput.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            con = DriverManager.getConnection(this.url,username,password);
            System.out.println("Good");
        }catch (Exception e){
            System.out.println(e.toString());
        }


        getStartcity();
        getEndcity();

      //  this.startCom.getItems().addAll(startcity);
        this.endCom.getItems().addAll(endcity);
        this.startCom.getItems().add("taroudant");

        startcol.setCellValueFactory(new PropertyValueFactory("start"));
        endcol.setCellValueFactory(new PropertyValueFactory("end"));
        pricecol.setCellValueFactory(new PropertyValueFactory("prix"));
        nbcol.setCellValueFactory(new PropertyValueFactory("nplace"));
        datecol.setCellValueFactory(new PropertyValueFactory("date"));
        idcol.setCellValueFactory(new PropertyValueFactory("id"));
        timecol.setCellValueFactory(new PropertyValueFactory("time"));

        this.tablebus.setOnMouseClicked(event -> {
            startinput.setText(tablebus.getSelectionModel().getSelectedItem().getStart());
            endinput.setText(tablebus.getSelectionModel().getSelectedItem().getEnd());
            dateinputt.setValue(LocalDate.parse(tablebus.getSelectionModel().getSelectedItem().getDate()));
            priceinput.setText(String.valueOf(tablebus.getSelectionModel().getSelectedItem().getPrix()));
            idbus.setText(String.valueOf(tablebus.getSelectionModel().getSelectedItem().getId()));
            timeinput.setText(tablebus.getSelectionModel().getSelectedItem().getTime());
        });

    }
}

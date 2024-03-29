package project.autocar_ticket;
import Modules.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class HistorypageController implements Initializable {
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

    //Database----------------------------------------

    String url = "jdbc:mysql://localhost/gestion_de_teckit";
    String username="root";
    String password="";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //----------------------------------------------

    @FXML
    private PieChart piecity;

    //--------------------------

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

    @FXML
    private TableColumn<History, String> timecol;

    //----------------------------------------

    @FXML
    private DatePicker datesearch;

    @FXML
    private Label bestCityid;

    @FXML
    private Label bestdateid;

   

    //---------------------------------------

    @FXML
    private TableView<History> tablehistory;

    //---------------------------------------
    @FXML
    void searchbtn(MouseEvent event) {

        this.tablehistory.getItems().clear();

        try {
            ps=con.prepareStatement("SELECT reservation.id,reservation.nom,reservation.today,reservation.cin,bus.Vd,bus.Va,bus.time FROM reservation, bus WHERE reservation.busid = bus.id AND reservation.today='"+datesearch.getValue().toString()+"'");
            rs=ps.executeQuery();

            while (rs.next()){
                History history=new History(
                        rs.getInt("reservation.id"),
                        rs.getString("reservation.nom"),
                        rs.getString("reservation.cin"),
                        rs.getString("reservation.today"),
                        rs.getString("bus.Vd"),
                        rs.getString("bus.Va"),
                        rs.getString("bus.time")

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
        timecol.setCellValueFactory(new PropertyValueFactory("time"));


       insertintopieChart();
        Best();
    }

    public void ExportFileExcile(){
        Random randI = new Random();
        int myRandInt = randI.nextInt(100);
        //myRandInt = myRandInt+1;

        try {
            ps=con.prepareStatement("SELECT reservation.id as id, reservation.nom as name, reservation.today as dateof, reservation.cin as cin, bus.Vd as startcity, bus.Va as endcity, bus.time as timeof FROM reservation, bus WHERE reservation.busid = bus.id;");
            rs=ps.executeQuery();


            XSSFWorkbook wb=new XSSFWorkbook();
            XSSFSheet sheet=wb.createSheet("history detials");
            XSSFRow header= sheet.createRow(0);
            header.createCell(0).setCellValue("id");
            header.createCell(1).setCellValue("name");
            header.createCell(2).setCellValue("dateof");
            header.createCell(3).setCellValue("cin");
            header.createCell(4).setCellValue("startcity");
            header.createCell(5).setCellValue("endcity");
            header.createCell(6).setCellValue("timeof");
            int index=1;
            while (rs.next()){
                XSSFRow row= sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("name"));
                row.createCell(2).setCellValue(rs.getString("dateof"));
                row.createCell(3).setCellValue(rs.getString("cin"));
                row.createCell(4).setCellValue(rs.getString("startcity"));
                row.createCell(5).setCellValue(rs.getString("endcity"));
                row.createCell(6).setCellValue(rs.getString("timeof"));
                index++;
            }
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;

            FileOutputStream fileOutputStream=new FileOutputStream("ExcelHistory/history_"+dateFormat.format(date)+".xlsx");
            wb.write(fileOutputStream);
            fileOutputStream.close();

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Status");
            alert.setHeaderText(null);
            alert.setContentText("Excel file added succssefuly");
            alert.showAndWait();



        }catch(Exception e){
            System.out.println(e);
        }


    }

    public void Best(){
        try {
            ps=con.prepareStatement("select count(Va) as num,Va from bus inner join reservation on bus.id=reservation.busid GROUP by Va HAVING num in (select max(a.num) from (select count(Va) as num,Va from bus inner join reservation on bus.id=reservation.busid GROUP by Va) a);");
            rs=ps.executeQuery();

            while (rs.next()){
                String bestcity=rs.getString("Va");
                this.bestCityid.setText(bestcity);
            }

        }catch(Exception e){
            System.out.println(e);
        }

        try {
            ps=con.prepareStatement("select max(a.td) from (select count(Va) as num,Va,reservation.today as td from bus inner join reservation on bus.id=reservation.busid GROUP by Va) a");
            rs=ps.executeQuery();

            while (rs.next()){
                String bestcity=rs.getString("max(a.td)");
                this.bestdateid.setText(bestcity);
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insertintopieChart(){
        try{
            ps=con.prepareStatement("select count(Va) as num,Va from bus inner join reservation on bus.id=reservation.busid GROUP by Va;");
            rs=ps.executeQuery();
            ObservableList<PieChart.Data> pie=FXCollections.observableArrayList();

            while (rs.next()){
                String city=rs.getString("Va");
                int num=rs.getInt("num");

                pie.add(new PieChart.Data(city,num));
            }

            piecity.setData(pie);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}

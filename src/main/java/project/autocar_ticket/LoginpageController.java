package project.autocar_ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class LoginpageController implements Initializable {

    private double xOffset=0;
    private double yOffset=0;
    @FXML
    private PasswordField passinput;

    @FXML
    private TextField userinput;



    @FXML
    void login(MouseEvent event) {

        if(  this.userinput.getText().equals("admin") && this.passinput.getText().equals("admin") ){


           try {
               FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
               Scene scene = new Scene(fxmlLoader.load(), 1250, 650);
               scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
               Stage stage=new Stage();
               Image image=new Image(getClass().getResourceAsStream("img/app logo.png"));
               stage.getIcons().add(image);
               stage.setTitle("BUS BLADI");
               stage.setScene(scene);
               stage.initStyle(StageStyle.UNDECORATED);

               scene.setOnMousePressed(mouseEvent -> {
                   xOffset=mouseEvent.getScreenX();
                   yOffset=mouseEvent.getScreenY();
               });
               scene.setOnMouseDragged(mouseEvent -> {
                   stage.setX(mouseEvent.getSceneX() - xOffset);
                   stage.setY(mouseEvent.getSceneY() - yOffset);
               });
               stage.show();

           }catch (Exception e){
               System.out.println(e);
           }

            Stage stage2;
            stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.close();

        }else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Status");
            alert.setContentText("password or username incorrect !!");
            alert.showAndWait();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void close(ActionEvent event) {
        Stage stage2;
        stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.close();
    }

    @FXML
    void minimize(ActionEvent event) {
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


}

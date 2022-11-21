package project.autocar_ticket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    private double xOffset=0;
    private double yOffset=0;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loginpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 417);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image image=new Image(getClass().getResourceAsStream("img/app logo.png"));
        stage.getIcons().add(image);
        stage.setTitle("BUS BLADI");
        stage.initStyle(StageStyle.UNDECORATED);

        scene.setOnMousePressed(mouseEvent -> {
            xOffset=mouseEvent.getScreenX();
            yOffset=mouseEvent.getScreenY();
        });
        scene.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getSceneX() - xOffset);
            stage.setY(mouseEvent.getSceneY() - yOffset);
        });

        stage.setScene(scene);
        stage.show();
    }









        public static void main(String[] args) {launch();}
}
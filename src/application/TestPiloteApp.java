package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class TestPiloteApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
         AnchorPane loginPage = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(loginPage);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Connexion Agent");
            primaryStage.show();
            
            
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.AgentDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    // Gérer la connexion de l'utilisateur
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        AgentDAO agentDAO = new AgentDAO();
        boolean isAuthenticated = agentDAO.login(username, password);

        if (isAuthenticated) {
            System.out.println("Connexion réussie !");
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Bienvenue");
            alert.setHeaderText(null);
            alert.setContentText("Bienvenue");
            alert.showAndWait();
            
            AnchorPane pilotPage;
            try {
                pilotPage = FXMLLoader.load(getClass().getResource("/view/Pilotes.fxml"));
                 Scene scene = new Scene(pilotPage);
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
       
            
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Nom d'utilisateur ou mot de passe incorrect");
            alert.showAndWait();
        }
    }

   @FXML
private void goToRegisterPage(ActionEvent event) {
    try {
        AnchorPane registerPage = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
        Scene scene = new Scene(registerPage);
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

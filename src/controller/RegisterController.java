package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.AgentDAO;
import model.AgentProgrammation;

public class RegisterController {

    @FXML
    private TextField usernameField, nomField, prenomField, emailField, telephoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;
    @FXML
    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String telephone = telephoneField.getText();
        String password = passwordField.getText();

        AgentProgrammation agent = new AgentProgrammation(username, password, nom, prenom, email, telephone);
        AgentDAO agentDAO = new AgentDAO();
        boolean isRegistered = agentDAO.register(agent);
        if (isRegistered) {
            messageLabel.setText("Inscription réussie !");
            goToLoginPage(event);
        } else {
            messageLabel.setText("Erreur lors de l'inscription !");
        }
    }
    @FXML
    private void goToLoginPage(ActionEvent event) {
        try {
            AnchorPane loginPage = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(loginPage);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

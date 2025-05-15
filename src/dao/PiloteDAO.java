package dao;

import model.Pilote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PiloteDAO {
    private Connection conn;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
   
    public PiloteDAO() {
        conn = LaConnexion.seConnecter();
    }

    public List<Pilote> getAllPilotes() {
        List<Pilote> pilotes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pilotes";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pilote p = new Pilote();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setLicence_numero(rs.getString("licence_numero"));
                p.setDisponible(rs.getBoolean("disponible"));
                pilotes.add(p);
            }
        } catch (SQLException e) {
            System.err.print(e.toString());
        }
        return pilotes;
    }

    public void ajouterPilote(Pilote p) {
        try {
            String sql = "INSERT INTO pilotes (nom, licence_numero, disponible) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNom());
            stmt.setString(2, p.getLicence_numero());
            stmt.setBoolean(3, p.isDisponible());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.print(e.toString());
        }
    }
    public void archiverPilote(Pilote p) {
        try {
            String sql = "delete from pilotes where nom=? and licence_numero=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNom());
            stmt.setString(2, p.getLicence_numero());
            System.out.print("trying to delete");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.print(e.toString());
        }
    }
   public void modifierPilote(Pilote p) {
    try {
        boolean newState = !p.isDisponible(); // Flip the state
        String sql = "UPDATE pilotes SET disponible=? WHERE licence_numero=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setBoolean(1, newState);
        stmt.setString(2, p.getLicence_numero());

        stmt.executeUpdate();
        System.out.println("Pilote updated to disponible=" + newState);
    } catch (SQLException e) {
        System.err.println("Erreur lors de la modification du pilote: " + e.getMessage());
    }
}
   public void goToPilotesPage(ActionEvent event) {
    try {
        AnchorPane registerPage = FXMLLoader.load(getClass().getResource("/view/Pilotes.fxml"));
        Scene scene = new Scene(registerPage);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void goToAvionsPage(ActionEvent event) {
    try {
        AnchorPane registerPage = FXMLLoader.load(getClass().getResource("/view/Avions.fxml"));
        Scene scene = new Scene(registerPage);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void goToVolsPage(ActionEvent event) {
    try {
        AnchorPane registerPage = FXMLLoader.load(getClass().getResource("/view/Vols.fxml"));
        Scene scene = new Scene(registerPage);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void goToEquipePage(ActionEvent event){
        try {
        AnchorPane registerPage = FXMLLoader.load(getClass().getResource("/view/Equipage.fxml"));
        Scene scene = new Scene(registerPage);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}

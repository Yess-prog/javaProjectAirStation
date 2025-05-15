package controller;

import dao.PiloteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Pilote;

public class PilotesController {
   @FXML private TextField nomField;
    @FXML private TextField licenceField;
    @FXML private TableView<Pilote> tablePilotes;
    @FXML private TableColumn<Pilote, String> colNom;
    @FXML private TableColumn<Pilote, String> colLicence;
    @FXML private TableColumn<Pilote, Boolean> colDisponible;
    
    private PiloteDAO piloteDAO;
    private ObservableList<Pilote> data;
    @FXML
    public void initialize() {
        piloteDAO = new PiloteDAO();
        colNom.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nom"));
        colLicence.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("licence_numero"));
        colDisponible.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("disponible"));
        loadPilotes();
    }

    private void loadPilotes() {
        data = FXCollections.observableArrayList(piloteDAO.getAllPilotes());
        tablePilotes.setItems(data);
    }

    @FXML
    public void ajouterPilote() {
        String nom = nomField.getText();
        String licence = licenceField.getText();

        if (nom.isEmpty() || licence.isEmpty()) {
            showAlert("Tous les champs sont obligatoires.");
            return;
        }

        Pilote p = new Pilote();
        p.setNom(nom);
        p.setLicence_numero(licence);
        p.setDisponible(true);
        piloteDAO.ajouterPilote(p);
        loadPilotes();
        clearFields();
    }
    @FXML
    public void archiverPilote() {
        String nom = nomField.getText();
        String licence = licenceField.getText();

        if (nom.isEmpty() || licence.isEmpty()) {
            showAlert("Tous les champs sont obligatoires.");
            return;
        }

        Pilote p = new Pilote();
        p.setNom(nom);
        p.setLicence_numero(licence);
        p.setDisponible(true);
        piloteDAO.archiverPilote(p);
        loadPilotes();
        clearFields();
    }
    @FXML
    public void modifierPilote() {
          Pilote selected = tablePilotes.getSelectionModel().getSelectedItem();
    if (selected == null) {
        showAlert("Veuillez sélectionner un pilote.");
        return;
    }
        piloteDAO.modifierPilote(selected);
        loadPilotes();
        clearFields();
    }
    

    private void clearFields() {
        nomField.clear();
        licenceField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.show();
    }
    public void goToPilotesPage(ActionEvent event) {
    piloteDAO.goToPilotesPage(event);
}
    public void goToAvionsPage(ActionEvent event) {
    piloteDAO.goToAvionsPage(event);
}
    public void goToVolsPage(ActionEvent event) {
    piloteDAO.goToVolsPage(event);
}
    public void goToEquipePage(ActionEvent event) {
    piloteDAO.goToEquipePage(event);
}
}

package controller;

import dao.PiloteDAO;
import dao.equipageDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Equipage;

import java.util.Optional;
import javafx.event.ActionEvent;

public class equipageController {
    PiloteDAO piloteDAO=new PiloteDAO();


    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private ComboBox<String> fonctionBox;
    @FXML private TableView<Equipage> tableEquipages;
    @FXML private TableColumn<Equipage, String> colNom;
    @FXML private TableColumn<Equipage, String> colPrenom;
    @FXML private TableColumn<Equipage, String> colFonction;
    @FXML private TableColumn<Equipage, Boolean> colDisponible;

    private equipageDAO equipageDAO = new equipageDAO();
    private ObservableList<Equipage> equipageList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize combo box items
        fonctionBox.getItems().addAll("hotesse", "steward");

        // Set cell value factories
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colFonction.setCellValueFactory(new PropertyValueFactory<>("fonction"));
        colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        loadEquipages();
    }

    private void loadEquipages() {
        equipageList.clear();
        equipageList.addAll(equipageDAO.getAllEquipages());
        tableEquipages.setItems(equipageList);
    }

    @FXML
    public void ajouterEquipage() {
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String fonction = fonctionBox.getValue();

        if (nom.isEmpty() || prenom.isEmpty() || fonction == null) {
            showAlert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.");
            return;
        }

        Equipage e = new Equipage();
        e.setNom(nom);
        e.setPrenom(prenom);
        e.setFonction(fonction.toLowerCase());
        e.setDisponible(true);

        if (equipageDAO.ajouterEquipage(e)) {
            loadEquipages();
            clearInputs();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur lors de l'ajout de l'équipage.");
        }
    }

    @FXML
    public void modifierEquipage() {
        Equipage selected = tableEquipages.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Veuillez sélectionner un équipage à modifier.");
            return;
        }

        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String fonction = fonctionBox.getValue();

        if (nom.isEmpty() || prenom.isEmpty() || fonction == null) {
            showAlert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.");
            return;
        }

        selected.setNom(nom);
        selected.setPrenom(prenom);
        selected.setFonction(fonction.toLowerCase());

        if (equipageDAO.modifierEquipage(selected)) {
            loadEquipages();
            clearInputs();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur lors de la modification.");
        }
    }

    @FXML
    public void archiverEquipage() {
        Equipage selected = tableEquipages.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Veuillez sélectionner un équipage à archiver.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation d'archivage");
        alert.setHeaderText("Archiver l'équipage " + selected.getNom() + " ?");
        alert.setContentText("Cette action rendra cet équipage indisponible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (equipageDAO.archiverEquipage(selected.getId())) {
                loadEquipages();
                clearInputs();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur lors de l'archivage.");
            }
        }
    }

    @FXML
    public void remplirFormulaire() {
        Equipage selected = tableEquipages.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nomField.setText(selected.getNom());
            prenomField.setText(selected.getPrenom());
            fonctionBox.setValue(selected.getFonction());
        }
    }

    private void clearInputs() {
        nomField.clear();
        prenomField.clear();
        fonctionBox.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
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

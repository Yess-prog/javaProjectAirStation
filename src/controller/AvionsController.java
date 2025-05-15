package controller;

import dao.AvionsDAO;
import dao.PiloteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Avion;

public class AvionsController {
    private PiloteDAO piloteDAO = new PiloteDAO(); 
    @FXML private TextField codeField, modeleField, capaciteField;
    @FXML private ComboBox<String> typeVolBox;
   
    @FXML
    private TableView<Avion> tableAvions;

    @FXML
    private TableColumn<Avion, Integer> colId;

    @FXML
    private TableColumn<Avion, String> colCodeAvion;

    @FXML
    private TableColumn<Avion, String> colModele;

    @FXML
    private TableColumn<Avion, Integer> colCapacite;

    @FXML
    private TableColumn<Avion, String> colTypeVol;

    @FXML
    private TableColumn<Avion, String> colEtat;

    @FXML
    private TextField codeAvionField;

   

    @FXML
    private ComboBox<String> etatBox;

    private AvionsDAO avionsDAO;
    private ObservableList<Avion> avionsList;

    private Avion selectedAvion;

    @FXML
    public void initialize() {
        avionsDAO = new AvionsDAO();

       
        typeVolBox.setItems(FXCollections.observableArrayList("court-courrier", "moyen-courrier", "long-courrier"));
        etatBox.setItems(FXCollections.observableArrayList("disponible", "maintenance"));

        
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colCodeAvion.setCellValueFactory(cellData -> cellData.getValue().codeAvionProperty());
        colModele.setCellValueFactory(cellData -> cellData.getValue().modeleProperty());
        colCapacite.setCellValueFactory(cellData -> cellData.getValue().capaciteProperty().asObject());
        colTypeVol.setCellValueFactory(cellData -> cellData.getValue().typeVolProperty());
        colEtat.setCellValueFactory(cellData -> cellData.getValue().etatProperty());

        loadAvions();

      
        tableAvions.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selectAvion(newValue));
    }

    private void loadAvions() {
        avionsList = FXCollections.observableArrayList(avionsDAO.getAllAvions());
        tableAvions.setItems(avionsList);
    }

    private void selectAvion(Avion avion) {
        if (avion != null) {
            selectedAvion = avion;
            codeAvionField.setText(avion.getCodeAvion());
            modeleField.setText(avion.getModele());
            capaciteField.setText(String.valueOf(avion.getCapacite()));
            typeVolBox.setValue(avion.getTypeVol());
            etatBox.setValue(avion.getEtat());
        } else {
            selectedAvion = null;
            codeAvionField.clear();
            modeleField.clear();
            capaciteField.clear();
            typeVolBox.setValue(null);
            etatBox.setValue(null);
        }
    }

    @FXML
    private void ajouterAvion() {
        try {
            String code = codeAvionField.getText();
            String modele = modeleField.getText();
            int capacite = Integer.parseInt(capaciteField.getText());
            String typeVol = typeVolBox.getValue();
            String etat = etatBox.getValue();

            Avion avion = new Avion();
            avion.setCodeAvion(code);
            avion.setModele(modele);
            avion.setCapacite(capacite);
            avion.setTypeVol(typeVol);
            avion.setEtat(etat);

            avionsDAO.ajouterAvion(avion);
            loadAvions();
            clearFields();

        } catch (NumberFormatException e) {
            showAlert("Erreur", "Capacité doit être un nombre entier.");
        }
    }

    @FXML
    private void modifierAvion() {
        if (selectedAvion != null) {
            try {
                selectedAvion.setCodeAvion(codeAvionField.getText());
                selectedAvion.setModele(modeleField.getText());
                selectedAvion.setCapacite(Integer.parseInt(capaciteField.getText()));
                selectedAvion.setTypeVol(typeVolBox.getValue());
                selectedAvion.setEtat(etatBox.getValue());

                avionsDAO.modifierAvion(selectedAvion);
                loadAvions();
                clearFields();

            } catch (NumberFormatException e) {
                showAlert("Erreur", "Capacité doit être un nombre entier.");
            }
        } else {
            showAlert("Information", "Veuillez sélectionner un avion à modifier.");
        }
    }

    @FXML
    private void supprimerAvion() {
        if (selectedAvion != null) {
            avionsDAO.supprimerAvion(selectedAvion.getId());
            loadAvions();
            clearFields();
        } else {
            showAlert("Information", "Veuillez sélectionner un avion à supprimer.");
        }
    }

    private void clearFields() {
        codeAvionField.clear();
        modeleField.clear();
        capaciteField.clear();
        typeVolBox.setValue(null);
        etatBox.setValue(null);
        tableAvions.getSelectionModel().clearSelection();
        selectedAvion = null;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
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

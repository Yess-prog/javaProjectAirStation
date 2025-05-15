package controller;

import dao.PiloteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EquipagesController {
    private PiloteDAO piloteDAO = new PiloteDAO(); 
    @FXML private TextField nomField;
    @FXML private ComboBox<String> fonctionBox;
    @FXML private TableView<?> tableEquipages;
    @FXML private TableColumn<?, ?> colNom;
    @FXML private TableColumn<?, ?> colFonction;
    @FXML private TableColumn<?, ?> colDisponible;

    public void ajouterEquipage() {
 
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

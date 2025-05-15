package controller;

import dao.PiloteDAO;
import dao.VolsDAO;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Pilote;
import model.Vol;

public class VolsController {
    private PiloteDAO piloteDAO = new PiloteDAO(); 
    private VolsDAO volsDAO = new VolsDAO(); 
     @FXML private TextField numeroVolField, origineField, destinationField;
    @FXML private DatePicker dateDepartPicker, dateArriveePicker;
    @FXML private ComboBox<String> avionBox;
    @FXML private ComboBox<String> equipageBox;
    @FXML private TableView<Vol> tableVols;
@FXML private TableColumn<Vol, String> colNumero, colOrigine, colDestination, colAvion, colEquipage;
@FXML private TableColumn<Vol, LocalDateTime> colDepart, colArrivee;

    private ObservableList<Vol> data;
    
    public void initialize() {
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero_vol"));
        colOrigine.setCellValueFactory(new PropertyValueFactory<>("origine"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colDepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        colArrivee.setCellValueFactory(new PropertyValueFactory<>("date_arrivee"));
        colAvion.setCellValueFactory(new PropertyValueFactory<>("avion_id"));
        colEquipage.setCellValueFactory(new PropertyValueFactory<>("equipage"));

        loadVols();
    }
    private void loadVols() {
    data = FXCollections.observableArrayList(volsDAO.getAllVols());
    tableVols.setItems(data);}
     public void ajouterVol() {
        Vol v = new Vol();
        v.setNumero_vol(numeroVolField.getText());
        v.setOrigine(origineField.getText());
        v.setDestination(destinationField.getText());
        v.setDate_depart(dateDepartPicker.getValue().atStartOfDay());
        v.setDate_arrivee(dateArriveePicker.getValue().atStartOfDay());
        v.setAvion_id(Integer.parseInt(avionBox.getValue()));
        

        volsDAO.ajouterVol(v);
        loadVols();
        clearFields();
    }


     @FXML
    public void modifierVol() {
        Vol selected = tableVols.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setOrigine(origineField.getText());
            selected.setDestination(destinationField.getText());
            selected.setDate_depart(dateDepartPicker.getValue().atStartOfDay());
            selected.setDate_arrivee(dateArriveePicker.getValue().atStartOfDay());
            selected.setAvion_id(Integer.parseInt(avionBox.getValue()));
            

            volsDAO.modifierVol(selected);
            loadVols();
            clearFields();
        }
    }
     @FXML
    public void archiverVol() {
        Vol selected = tableVols.getSelectionModel().getSelectedItem();
        if (selected != null) {
            volsDAO.archiverVol(selected.getNumero_vol());
            loadVols();
        }
    }

    private void clearFields() {
        numeroVolField.clear();
        origineField.clear();
        destinationField.clear();
        dateDepartPicker.setValue(null);
        dateArriveePicker.setValue(null);
        avionBox.setValue(null);
        equipageBox.setValue(null);
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

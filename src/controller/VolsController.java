package controller;

import dao.AvionsDAO;
import dao.equipageDAO;
import dao.PiloteDAO;
import dao.VolsDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Avion;
import model.Equipage;
import model.Pilote;
import model.Vol;

public class VolsController {
    private PiloteDAO piloteDAO = new PiloteDAO(); 
    private VolsDAO volsDAO = new VolsDAO(); 
    private AvionsDAO AvionsDAO=new AvionsDAO();
    private equipageDAO equipageDAO=new equipageDAO();
     @FXML private TextField numeroVolField, origineField, destinationField;
    @FXML private DatePicker dateDepartPicker, dateArriveePicker;
    @FXML private ComboBox<Integer> avionBox;
    @FXML private ComboBox<Integer> equipageBox;
    @FXML private TableView<Vol> tableVols;
    @FXML private TableColumn<Vol, String> colPilote1;
@FXML private TableColumn<Vol, String> colPilote2;

@FXML private TableColumn<Vol, String> colNumero, colOrigine, colDestination, colAvion, colEquipage;
@FXML private TableColumn<Vol, LocalDateTime> colDepart, colArrivee;
@FXML private ComboBox<String> pilote1Box;
@FXML private ComboBox<String> pilote2Box;


    private ObservableList<Vol> data;
    
    public void initialize() {
         List<Avion> avionsList = AvionsDAO.getAllAvions();
ObservableList<Integer> avionsStrings = FXCollections.observableArrayList();
for (Avion a : avionsList) {
    avionsStrings.add(a.getId());
}
avionBox.setItems(avionsStrings);

    List<Equipage> equipageList = equipageDAO.getAllEquipages();
ObservableList<Integer> equipageStrings = FXCollections.observableArrayList();
for (Equipage e : equipageList) {
    equipageStrings.add(e.getId());
}
equipageBox.setItems(equipageStrings);
        ObservableList<String> pilotes = FXCollections.observableArrayList();
        for (Iterator<Pilote> it = piloteDAO.getAllPilotesActive() .iterator(); it.hasNext();) {
            Pilote p = it.next();
            pilotes.add(p.getNom());
        }
pilote1Box.setItems(pilotes);
pilote2Box.setItems(pilotes);
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero_vol"));
        colOrigine.setCellValueFactory(new PropertyValueFactory<>("origine"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colDepart.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        colArrivee.setCellValueFactory(new PropertyValueFactory<>("date_arrivee"));
        colAvion.setCellValueFactory(new PropertyValueFactory<>("avion_id"));
        colAvion.setCellValueFactory(new PropertyValueFactory<>("avion_id"));
        
        colEquipage.setCellValueFactory(new PropertyValueFactory<>("equipage"));
        colPilote1.setCellValueFactory(cellData -> {
    Pilote p1 = cellData.getValue().getPilote1();
    return new ReadOnlyStringWrapper(p1 != null ? p1.getNom() : "");
});

colPilote2.setCellValueFactory(cellData -> {
    Pilote p2 = cellData.getValue().getPilote2();
    return new ReadOnlyStringWrapper(p2 != null ? p2.getNom() : "");
});

colEquipage.setCellValueFactory(cellData -> {
    Equipage eq = cellData.getValue().getEquipage();
    return new ReadOnlyStringWrapper(eq != null ? String.valueOf(eq.getId()) : "");
});
        
        loadVols();
    }
    private void loadVols() {
    data = FXCollections.observableArrayList(volsDAO.getAllVols());
    tableVols.setItems(data);}
     public void ajouterVol() {
         boolean allocate=true;
        Vol v = new Vol();
        String nv = numeroVolField.getText();
        Integer eq = equipageBox.getValue();
        String o = origineField.getText();
        
        String dest = destinationField.getText();
        
        LocalDateTime dd = dateDepartPicker.getValue().atStartOfDay();
        
        LocalDateTime da = dateArriveePicker.getValue().atStartOfDay();
        
        Integer aId = avionBox.getValue();
        
        Integer equipageId = equipageBox.getValue();
        
        String nomPilote1 = pilote1Box.getValue();
    String nomPilote2 = pilote2Box.getValue();
Pilote pilote1 = piloteDAO.getPiloteByNom(nomPilote1);
    Pilote pilote2 = piloteDAO.getPiloteByNom(nomPilote2);
    if (nv.isEmpty() || o.isEmpty() || dest.isEmpty() ||
        dd == null || da == null || aId == null ||
        equipageId == null || nomPilote1 == null || nomPilote2 == null) {
        System.err.println("Données manquantes.");
        allocate=false;
    }
     if (nomPilote1.equals(nomPilote2)) {
        System.err.println("Les deux pilotes ne peuvent pas être les mêmes.");
        return;
    }
        if(dd.isAfter(da)){
            allocate=false;
        }
         List<Vol> tousLesVols = volsDAO.getAllVols();
    for (Vol volExistant : tousLesVols) {
        LocalDateTime departExistant = volExistant.getDate_depart();
        LocalDateTime arriveeExistante = volExistant.getDate_arrivee();

        

        
            boolean chevauche = dd.isBefore(arriveeExistante) && da.isAfter(departExistant);
if (chevauche && (
    volExistant.getPilote1().getId() == pilote1.getId() ||
    volExistant.getPilote2().getId() == pilote1.getId() ||
    volExistant.getPilote1().getId() == pilote2.getId() ||
    volExistant.getPilote2().getId() == pilote2.getId())) {
    System.err.println("Un des pilotes est déjà affecté à un autre vol pendant cette période.");
    allocate = false;
}

        
        
            if(allocate){
                v.setNumero_vol(nv);
    v.setOrigine(o);
    v.setDestination(dest);
    v.setDate_depart(dd);
        v.setDate_arrivee(da);
        v.setId(aId);
        v.setPilote1(pilote1);
        v.setPilote2(pilote2);
        v.setEquipage(eq);
        

        volsDAO.ajouterVol(v);
        PiloteDAO PiloteDAO=new PiloteDAO();
        PiloteDAO.modifierPilote(pilote2);
        PiloteDAO.modifierPilote(pilote1);
        loadVols();
        clearFields();
            
        
    }
    if(allocate==false){
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Vérifiez vos données");
            alert.setHeaderText(null);
            alert.setContentText("Données incorrectes");
            alert.showAndWait();
    }
    
    }
     }


     @FXML
    public void modifierVol() {
        Vol selected = tableVols.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setOrigine(origineField.getText());
            selected.setDestination(destinationField.getText());
            selected.setDate_depart(dateDepartPicker.getValue().atStartOfDay());
            selected.setDate_arrivee(dateArriveePicker.getValue().atStartOfDay());
            selected.setId((avionBox.getValue()));
            selected.setPilote1(piloteDAO.getPiloteByNom((pilote1Box.getValue())));
            selected.setPilote2(piloteDAO.getPiloteByNom((pilote2Box.getValue())));
            

            volsDAO.modifierVol(selected);
            loadVols();
            clearFields();
        }
    }
     @FXML
    public void supprimerVol() {
        Vol selected = tableVols.getSelectionModel().getSelectedItem();
         if (selected != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Supprimer le vol " + selected.getNumero_vol());
        alert.setContentText("Es-tu sûr de vouloir supprimer ce vol définitivement ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            volsDAO.supprimerVol(selected.getNumero_vol());
            loadVols(); // Recharge la table
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucun vol sélectionné");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un vol à supprimer.");
        alert.showAndWait();
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
    private void handleDateSelection() {
    LocalDate depart = dateDepartPicker.getValue();
    LocalDate arrivee = dateArriveePicker.getValue();

    if (depart != null && arrivee != null) {
        LocalDateTime dateDepartTime = depart.atStartOfDay();
        LocalDateTime dateArriveeTime = arrivee.atTime(23, 59);

        List<String> disponibles = piloteDAO.getPilotesDisponibles(dateDepartTime, dateArriveeTime);
        ObservableList<String> items = FXCollections.observableArrayList(disponibles);
        
        pilote1Box.setItems(items);
        pilote2Box.setItems(items);
    }
}
}

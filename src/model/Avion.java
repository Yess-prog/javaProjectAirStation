package model;

import javafx.beans.property.*;

public class Avion {
    private IntegerProperty id;
    private StringProperty codeAvion;
    private StringProperty modele;
    private IntegerProperty capacite;
    private StringProperty typeVol;
    private StringProperty etat;

    public Avion() {
        this.id = new SimpleIntegerProperty();
        this.codeAvion = new SimpleStringProperty();
        this.modele = new SimpleStringProperty();
        this.capacite = new SimpleIntegerProperty();
        this.typeVol = new SimpleStringProperty();
        this.etat = new SimpleStringProperty();
    }

    // id
    public int getId() {
        return id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public IntegerProperty idProperty() {
        return id;
    }

    // codeAvion
    public String getCodeAvion() {
        return codeAvion.get();
    }
    public void setCodeAvion(String codeAvion) {
        this.codeAvion.set(codeAvion);
    }
    public StringProperty codeAvionProperty() {
        return codeAvion;
    }

    // modele
    public String getModele() {
        return modele.get();
    }
    public void setModele(String modele) {
        this.modele.set(modele);
    }
    public StringProperty modeleProperty() {
        return modele;
    }

    // capacite
    public int getCapacite() {
        return capacite.get();
    }
    public void setCapacite(int capacite) {
        this.capacite.set(capacite);
    }
    public IntegerProperty capaciteProperty() {
        return capacite;
    }

    // typeVol
    public String getTypeVol() {
        return typeVol.get();
    }
    public void setTypeVol(String typeVol) {
        this.typeVol.set(typeVol);
    }
    public StringProperty typeVolProperty() {
        return typeVol;
    }

    // etat
    public String getEtat() {
        return etat.get();
    }
    public void setEtat(String etat) {
        this.etat.set(etat);
    }
    public StringProperty etatProperty() {
        return etat;
    }
}

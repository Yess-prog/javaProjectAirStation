package model;

import dao.equipageDAO;
import java.time.LocalDateTime;

public class Vol {
    private int id;
    private String numero_vol;
    private String origine;
    private String destination;
    private LocalDateTime date_depart;
    private LocalDateTime date_arrivee;
    private Pilote pilote1;
    private Pilote pilote2;
    private Equipage equipage;
    
    private String avion_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero_vol() {
        return numero_vol;
    }

    public void setNumero_vol(String numero_vol) {
        this.numero_vol = numero_vol;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(LocalDateTime date_depart) {
        this.date_depart = date_depart;
    }

    public LocalDateTime getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(LocalDateTime date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public String getAvion_id() {
        return avion_id;
    }

    public void setAvion_id(String avion_id) {
        this.avion_id = avion_id;
    }
    public Pilote getPilote1() {
    return this.pilote1;
}

public void setPilote1(Pilote pilote1) {
    this.pilote1 = pilote1;
}

public Pilote getPilote2() {
    return this.pilote2;
}

public void setPilote2(Pilote pilote2) {
    this.pilote2 = pilote2;
}
public void setEquipage(int eq) {
        equipageDAO equipageDAO = new equipageDAO();
    this.equipage = equipageDAO.getEquipageById(eq);
}
public Equipage getEquipage() {
        
    return this.equipage;
}



   
}

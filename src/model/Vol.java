package model;

import java.time.LocalDateTime;

public class Vol {
    private int id;
    private String numero_vol;
    private String origine;
    private String destination;
    private LocalDateTime date_depart;
    private LocalDateTime date_arrivee;
    private int avion_id;

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

    public int getAvion_id() {
        return avion_id;
    }

    public void setAvion_id(int avion_id) {
        this.avion_id = avion_id;
    }


   
}

package model;

public class Pilote {
    private int id;
    private String nom;
    private String licence_numero;
    private boolean disponible;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getLicence_numero() { return licence_numero; }
    public void setLicence_numero(String licence_numero) { this.licence_numero = licence_numero; }

    public boolean isDisponible() { 
        return disponible;
    }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}

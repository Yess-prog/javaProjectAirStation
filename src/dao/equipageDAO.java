package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Equipage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class equipageDAO {
     private Connection conn;

    public equipageDAO() {
        conn = LaConnexion.seConnecter();
    }

     public List<Equipage> getAllEquipages() {
        List<Equipage> equipages = new ArrayList<>();
        String sql = "SELECT * FROM equipages WHERE disponible = 1";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Equipage e = new Equipage();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setFonction(rs.getString("fonction"));
                e.setDisponible(rs.getBoolean("disponible"));
                equipages.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipages;
    }

     public boolean ajouterEquipage(Equipage e) {
        String sql = "INSERT INTO equipages (nom, prenom, fonction, disponible) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getFonction());
            ps.setBoolean(4, e.isDisponible());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean modifierEquipage(Equipage e) {
        String sql = "UPDATE equipages SET nom = ?, prenom = ?, fonction = ?, disponible = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getFonction());
            ps.setBoolean(4, e.isDisponible());
            ps.setInt(5, e.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean archiverEquipage(int id) {
        String sql = "UPDATE equipages SET disponible = 0 WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Equipage getEquipageById(int id) {
        Equipage e = null;
        String sql = "SELECT id, nom, fonction, disponible FROM equipages WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    e = new Equipage();
                    e.setId(rs.getInt("id"));
                    e.setNom(rs.getString("nom"));
                    e.setFonction(rs.getString("fonction"));
                    e.setDisponible(rs.getBoolean("disponible"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }
}




   

    

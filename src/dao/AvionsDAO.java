package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Avion; // suppose que tu as une classe Avion

public class AvionsDAO {
    private Connection conn;

    public AvionsDAO() {
        conn = LaConnexion.seConnecter();
    }

    public List<Avion> getAllAvions() {
        List<Avion> avions = new ArrayList<>();
        String sql = "SELECT * FROM avions";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Avion a = new Avion();
                a.setId(rs.getInt("id"));
                a.setCodeAvion(rs.getString("code_avion"));
                a.setModele(rs.getString("modele"));
                a.setCapacite(rs.getInt("capacite"));
                a.setTypeVol(rs.getString("type_vol"));
                a.setEtat(rs.getString("etat"));
                avions.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avions;
    }

    public void ajouterAvion(Avion avion) {
        String sql = "INSERT INTO avions (code_avion, modele, capacite, type_vol, etat) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, avion.getCodeAvion());
            ps.setString(2, avion.getModele());
            ps.setInt(3, avion.getCapacite());
            ps.setString(4, avion.getTypeVol());
            ps.setString(5, avion.getEtat());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierAvion(Avion avion) {
        String sql = "UPDATE avions SET code_avion=?, modele=?, capacite=?, type_vol=?, etat=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, avion.getCodeAvion());
            ps.setString(2, avion.getModele());
            ps.setInt(3, avion.getCapacite());
            ps.setString(4, avion.getTypeVol());
            ps.setString(5, avion.getEtat());
            ps.setInt(6, avion.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerAvion(int id) {
        try {
            // Delete related vols first
            String deleteVolsSql = "DELETE FROM vols WHERE avion_id = ?";
            try (PreparedStatement psVols = conn.prepareStatement(deleteVolsSql)) {
                psVols.setInt(1, id);
                psVols.executeUpdate();
            }

            // Then delete the avion
            String deleteAvionSql = "DELETE FROM avions WHERE id = ?";
            try (PreparedStatement psAvion = conn.prepareStatement(deleteAvionSql)) {
                psAvion.setInt(1, id);
                psAvion.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

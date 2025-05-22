package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.Vol;
import model.Pilote;

public class VolsDAO {
    private Connection conn;

    public VolsDAO() {
        conn = LaConnexion.seConnecter();
    }

    public List<Vol> getAllVols() {
        PiloteDAO PiloteDAO;
        PiloteDAO = new PiloteDAO();
        
        List<Vol> vols = new ArrayList<>();
        String sql = "SELECT * FROM vols";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                Vol v = new Vol();
                
                v.setId(rs.getInt("id"));
                v.setNumero_vol(rs.getString("numero_vol"));
                v.setOrigine(rs.getString("origine"));
                v.setDestination(rs.getString("destination"));
                v.setDate_depart(rs.getTimestamp("date_depart").toLocalDateTime());
                v.setDate_arrivee(rs.getTimestamp("date_arrivee").toLocalDateTime());
                v.setAvion_id(rs.getString("avion_id"));

                // Pilotes (à remplacer par une vraie récupération si nécessaire)
                Pilote p1 = PiloteDAO.getPiloteById(rs.getInt("pilote1id"));
Pilote p2 = PiloteDAO.getPiloteById(rs.getInt("pilote2id"));

v.setPilote1(p1);
v.setPilote2(p2);

                v.setEquipage(rs.getInt("equipage"));
                vols.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vols;
    }

    public void ajouterVol(Vol vol) {
        String sql = "INSERT INTO vols (numero_vol, origine, destination, date_depart, date_arrivee, avion_id, pilote1id, pilote2id,equipage) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vol.getNumero_vol());
            ps.setString(2, vol.getOrigine());
            ps.setString(3, vol.getDestination());
            ps.setTimestamp(4, Timestamp.valueOf(vol.getDate_depart()));
            ps.setTimestamp(5, Timestamp.valueOf(vol.getDate_arrivee()));
            ps.setInt(6, vol.getId());
            ps.setInt(7, vol.getPilote1().getId());
            ps.setInt(8, vol.getPilote2().getId());
            ps.setInt(9, vol.getEquipage().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public void modifierVol(Vol vol) {
    String sql = "UPDATE vols SET origine = ?, destination = ?, date_depart = ?, date_arrivee = ?, avion_id = ?, pilote1id = ?, pilote2id = ?, equipage = ? WHERE numero_vol = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, vol.getOrigine());
        ps.setString(2, vol.getDestination());
        ps.setTimestamp(3, Timestamp.valueOf(vol.getDate_depart()));
        ps.setTimestamp(4, Timestamp.valueOf(vol.getDate_arrivee()));
        ps.setString(5, vol.getAvion_id());
        ps.setInt(6, vol.getPilote1().getId());
        ps.setInt(7, vol.getPilote2().getId());
        ps.setInt(8, vol.getEquipage().getId()); // You forgot this field
        ps.setString(9, vol.getNumero_vol());

        int rows = ps.executeUpdate();
        if (rows > 0) {
            System.out.println("Vol updated successfully.");
        } else {
            System.out.println("No vol found with numero_vol: " + vol.getNumero_vol());
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la mise à jour du vol: " + e.getMessage());
    }
}


    public void supprimerVol(String numeroVol) {
        String sql = "DELETE FROM vols WHERE numero_vol = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, numeroVol);
        ps.executeUpdate();
        System.out.println("Vol supprimé définitivement.");
    } catch (SQLException e) {
        System.err.println("Erreur lors de la suppression du vol : " + e.getMessage());
    }
    }
}

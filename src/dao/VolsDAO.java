package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Vol;

public class VolsDAO {
    private Connection conn;

    public VolsDAO() {
        conn = LaConnexion.seConnecter();
    }

    public List<Vol> getAllVols() {
    List<Vol> vols = new ArrayList<>();
    String sql = "SELECT * FROM vols ";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Vol v = new Vol();
            v.setId(rs.getInt("id"));
            v.setNumero_vol(rs.getString("numero_vol"));
            v.setOrigine(rs.getString("origine"));
            v.setDestination(rs.getString("destination"));
            v.setDate_depart(rs.getDate("date_depart").toLocalDate().atStartOfDay());
            v.setDate_arrivee(rs.getDate("date_arrivee").toLocalDate().atStartOfDay());
            v.setAvion_id(rs.getInt("avion_id")); // assuming "avion" is an int FK
            
            vols.add(v);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return vols;
}


    public void ajouterVol(Vol vol) {
        String sql = "INSERT INTO vols (numero, origine, destination, date_depart, date_arrivee, avion, equipage, archive) VALUES (?, ?, ?, ?, ?, ?, ?, false)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vol.getNumero_vol());
            ps.setString(2, vol.getOrigine());
            ps.setString(3, vol.getDestination());
            ps.setDate(3, Date.valueOf(vol.getDate_depart().toLocalDate()));
            ps.setDate(3, Date.valueOf(vol.getDate_arrivee().toLocalDate()));
            ps.setInt(6, vol.getAvion_id());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierVol(Vol vol) {
        String sql = "UPDATE vols SET origine=?, destination=?, date_depart=?, date_arrivee=?, avion=?, equipage=? WHERE numero=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vol.getOrigine());
            ps.setString(2, vol.getDestination());
            
            ps.setDate(3, Date.valueOf(vol.getDate_depart().toLocalDate()));
            ps.setDate(3, Date.valueOf(vol.getDate_arrivee().toLocalDate()));
            ps.setInt(5, vol.getAvion_id());
            
            ps.setString(6, vol.getNumero_vol());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void archiverVol(String numeroVol) {
        String sql = "UPDATE vols SET archive = true WHERE numero = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numeroVol);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

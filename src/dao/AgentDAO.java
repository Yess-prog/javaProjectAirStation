package dao;

import model.AgentProgrammation;
import java.sql.*;
import java.security.MessageDigest;

public class AgentDAO {

    public boolean register(AgentProgrammation agent) {
        String hashed = hashPassword(agent.getPassword());
        Connection conn = LaConnexion.seConnecter();
        if (conn == null) {
            System.out.println("Échec de la connexion à la base de données.");
            return false;
        }
        try {
            String sql = "INSERT INTO agents(username, password, nom, prenom, email, telephone) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, agent.getUsername());
            stmt.setString(2, hashed);
            stmt.setString(3, agent.getNom());
            stmt.setString(4, agent.getPrenom());
            stmt.setString(5, agent.getEmail());
            stmt.setString(6, agent.getTelephone());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean login(String username, String password) {
        String hashed = hashPassword(password);
        Connection conn = LaConnexion.seConnecter();
        if (conn == null) {
            System.out.println("Échec de la connexion à la base de données.");
            return false;
        }
        try {
            String sql = "SELECT * FROM agents WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashed);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de la connexion : " + e.getMessage());
            return false;
        }
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            System.out.println("Erreur lors du hash du mot de passe : " + e.getMessage());
            return null;
        }
    }
}

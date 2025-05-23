package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LaConnexion {
    private static Connection con;
    private static String user;
    private static String passWord;

    public static Connection seConnecter() {
        if (con == null) { 
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tunisair", "root", "");
                System.out.println("connexion établie");
            } catch (ClassNotFoundException e) {
                System.out.println("driver non trouvé " + e.getMessage());
            } catch (SQLException ex) {
                System.out.println("bd non trouvé ou problème d'identification " + ex.getMessage());
            }
        }
        return con;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}

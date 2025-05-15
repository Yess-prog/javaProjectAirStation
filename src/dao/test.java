/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;

/**
 *
 * @author besse
 */
public class test {
    public static void main(String[] args) {
        
        Connection conn = LaConnexion.seConnecter();
        if (conn != null) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Connexion échouée.");
        }
    }
}

    


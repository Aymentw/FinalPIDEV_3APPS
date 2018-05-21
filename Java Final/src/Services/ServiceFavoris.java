/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Colocation;
import Entities.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Tech.DataSource;

/**
 *
 * @author Karim
 */
public class ServiceFavoris {
    public Connection cnx = DataSource.GetInstance().GetConnection();
    public Statement ste;
    
    public ServiceFavoris() {
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String afficherFavoris(Colocation c,int id) throws SQLException{
         String req = "SELECT * FROM `favoris_colocation` WHERE id_colocation=? and id_user=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1,c.getId());
        pre.setInt(2, Session.getIdThisUser());   
        ResultSet res = pre.executeQuery();
        String s="file:\\\\\\C:\\Users\\ASUS-PC\\Desktop\\Esprit\\3eme annee\\pidev\\Java\\src\\Resources\\star0.png";
        
         
           
        while (res.next()) {
           s="file:\\\\\\C:\\Users\\ASUS-PC\\Desktop\\Esprit\\3eme annee\\pidev\\Java\\src\\Resources\\star1.png";
        }
        return s;
    }
    
      public void ajouterfavoris(Colocation c) throws SQLException {
        String req = "insert into favoris_colocation (id_user,id_colocation) values(?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1,Session.getIdThisUser());
        pre.setInt(2, c.getId());   
        pre.executeUpdate();
       
      }
      public void supprimerfavoris(Colocation c) throws SQLException {
        String req = "delete from favoris_colocation where id_colocation=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, c.getId());   
        pre.executeUpdate();
       
      }
}

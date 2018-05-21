/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Adresse_cov;
import Tech.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS-PC
 */
public class ServicesAdrCov {
    public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;
    
    public ServicesAdrCov(){
        try {
            ste = connection.createStatement();
        } catch (SQLException e){
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public List<Adresse_cov> getAdresses() {
    List<Adresse_cov> adresses= new ArrayList<>();
        try {
            ResultSet rs=ste.executeQuery("select * from adresses_cov");
            
           
            while(rs.next()){
                Adresse_cov A=new Adresse_cov(rs.getString("nom"),rs.getDouble("lat"),rs.getDouble("lng"));
                adresses.add(A);
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
         return adresses;
    }
    public void AjouterAdresse(Adresse_cov A){
         try {
            PreparedStatement pst=connection.prepareStatement("insert into adresses_cov (nom,lat,lng) values(?,?,?) ");
        pst.setString(1,A.getNom());
        pst.setDouble(2, A.getLat());
        pst.setDouble(3,A.getLng());
       
        pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

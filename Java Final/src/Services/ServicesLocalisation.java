/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Covoiturage;
import Entities.Localisation;
import Entities.Localisation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Tech.DataSource;

/**
 *
 * @author ASUS-PC
 */
public class ServicesLocalisation {
    public Connection con= DataSource.GetInstance().GetConnection();
    public Statement ste;
    public ServicesLocalisation(){
        try {
            ste= con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Localisation> getLocalisations(){
        List<Localisation> localisations= new ArrayList<>();
        try {
            PreparedStatement pst=con.prepareStatement("select * from Localisation");
            
            ResultSet rs=pst.executeQuery();
            
            while(rs.next()){
                Localisation A=new Localisation(rs.getDouble("lat"),rs.getDouble("lng"));
                localisations.add(A);
            }
            return localisations;
        } catch (SQLException ex) {
            Logger.getLogger(ServicesLocalisation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void addLocalisation(Localisation L){
          try {
            PreparedStatement pst=con.prepareStatement("INSERT INTO `localisation` ( `id_a`, `lat`, `lng`, `type`) VALUES ( ?, ?, ?, ?) ");
        pst.setInt(1,L.getid_a());
        pst.setDouble(2, L.getlat());
        pst.setDouble(3, L.getlng());
        pst.setInt(4, L.getType());
     
        pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

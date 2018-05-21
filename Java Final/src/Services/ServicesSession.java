/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Tech.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Houbal
 */
public class ServicesSession {

     public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;
    
    public ServicesSession(){
        try {
            ste = connection.createStatement();
        } catch (SQLException e){
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    

    public Date recevoirDate() {
        Date a = null;

        try {
            String req = "SELECT CURRENT_TIMESTAMP";
            PreparedStatement pre = connection.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                a = rs.getDate(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
return a;
    }

}

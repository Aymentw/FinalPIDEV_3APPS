/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mechlaoui
 */
public class DataSource {

    private static DataSource data;
    final String url ="jdbc:mysql://localhost:3306/bd_pi";
    final String user="root";
    final String password="";
    private Connection connection;
    
    
    
    private DataSource(){
        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch(SQLException ex){
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DataSource GetInstance() {
        if (data == null)
         data = new DataSource();
        return data;
        
    }
    
    public Connection GetConnection(){
        return connection;
    }
    
}

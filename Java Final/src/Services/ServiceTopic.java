/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Topic;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Tech.DataSource;
/**
 *
 * @author Aymen
 */
public class ServiceTopic {

public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;

    public ServiceTopic() {
        try {
            ste = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTopic.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AjouterTopic(Topic t) throws SQLException {
        String req = "INSERT INTO Topic(id_user,sujet,description) VALUES(?,?,?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, t.getId_user());
        pre.setString(2, t.getSujet());
        pre.setString(3, t.getDescription());
        pre.executeUpdate();
        System.out.println("Topic Ajouté");
    }

    public void updateTopic(Topic t) throws SQLException {
        String req = "UPDATE Topic SET sujet=?,description=? WHERE id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, t.getSujet());
        pre.setString(2, t.getDescription());
        pre.setInt(3, t.getId());
        pre.executeUpdate();
    }

    public void deleteTopic(int id) throws SQLException {
        
        String req = "DELETE FROM Topic WHERE id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, id);
        pre.execute();
    }

        public void afficherTopic() throws SQLException{
        ResultSet rs=ste.executeQuery("SELECT * FROM Topic");
        
   
    while(rs.next()){
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
    }
    
   

}
        
                public  ObservableList<Topic> displayTopic() throws SQLException
      {
          
       
         ObservableList<Topic> list=FXCollections.observableArrayList();
            String req="SELECT * FROM Topic";
            PreparedStatement ste= connection.prepareStatement(req);
            ResultSet result=ste.executeQuery();
            
            while(result.next())
            {
            Topic t= new Topic (result.getInt(1),result.getString(3),result.getString(4));
                  
            list.add(t);
            }
           
       return list;
      }
                
                
        
      
}

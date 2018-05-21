/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tech.DataSource;
import Entities.Reclamation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Aymen
 */
public class ServiceReclamation {

public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;
   


    public ServiceReclamation() {
        
        try {
            ste=connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     public void AjouterReclamation(Reclamation r) throws SQLException{
        String req = "INSERT INTO Reclamation(id_user,objet,description) VALUES(?,?,?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, r.getId_user());
        pre.setString(2, r.getObjet());
        pre.setString(2, r.getDescription());
        pre.executeUpdate();
        System.out.println("Réclamation Ajoutée");
    }
    
     
       
        public void updateReclamation(Reclamation r) throws SQLException{
         String req = "UPDATE Reclamation SET objet=?,description=?,date=? WHERE id=?";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setString(1, r.getObjet());
            pre.setString(2, r.getDescription());
            pre.setDate(3, (Date) r.getDate());
            pre.setInt(4, r.getId());
            pre.executeUpdate();
            System.out.println("Réclamation modifiée");
    }
        
           public void deleteReclamation(int id) throws SQLException{
             String req = "DELETE FROM Reclamation WHERE id=?";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setInt(1, id);
            pre.execute();
            System.out.println("Réclamation supprimée");
        }

                      public List<Reclamation> afficherReclamation() throws SQLException{
        ResultSet rs=ste.executeQuery("SELECT objet,description FROM Reclamation");
        
    List<Reclamation> reclamations= new ArrayList<>();
    while(rs.next()){
    Reclamation R=new Reclamation(rs.getString("objet"),rs.getString("description"));
    reclamations.add(R);
    }return reclamations; 
     
      
}
              public  ObservableList<Reclamation> displayReclamation() throws SQLException
      {
          
       
         ObservableList<Reclamation> list=FXCollections.observableArrayList();
            String req="SELECT * FROM Reclamation";
            PreparedStatement ste= connection.prepareStatement(req);
            ResultSet result=ste.executeQuery();
            
            while(result.next())
            {
            Reclamation r= new Reclamation (result.getInt(1),result.getString(2),result.getString(3));
                  
            list.add(r);
            }
           
       return list;
      }                
     
     
     
     
}

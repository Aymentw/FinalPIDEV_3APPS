/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activite;
import Tech.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mechlaoui
 */
public class ServicesActivite {
    public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;
    
    public ServicesActivite(){
        try {
            ste = connection.createStatement();
        } catch (SQLException e){
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public void AjouterActivite(Activite a) throws SQLException{
        String req = "INSERT INTO Activite (Nom,Discipline,Date_Event,Duree,Lieu,Description,Affiche) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, a.getNom());
        pre.setString(2, a.getDiscipline().name());
        pre.setTimestamp(3, Timestamp.valueOf(a.getDate_Event()));
        pre.setString(4, a.getDuree());
        pre.setString(5, a.getLieu());
        pre.setString(6, a.getDescription());
        pre.setString(7, a.getAffiche());
        pre.executeUpdate();
        
        
    }
    
    public void ModifierActivite(Activite a) throws SQLException {
        String req = "UPDATE Activite SET Nom=?, Discipline=?, Date_Event=?, Duree=?, Lieu=?,";
               req +="Description=?, Affiche=? WHERE ID= ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, a.getNom());
        pre.setString(2, a.getDiscipline().name());
        pre.setTimestamp(3, Timestamp.valueOf(a.getDate_Event()));
        pre.setString(4, a.getDuree());
        pre.setString(5, a.getLieu());
        pre.setString(6, a.getDescription());
        pre.setString(7, a.getAffiche());
        pre.setInt(8, a.getID());
        pre.executeUpdate();
        
    }
    
    public void SupprimerActivite(Activite a) throws SQLException {
        String req = "DELETE  FROM Activite WHERE ID =?";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setInt(1, a.getID());
            pre.executeUpdate();
        
    }
    
    public List<Activite> SelectActivite() throws SQLException{
        
        List<Activite> list = new ArrayList<>();
            String req = "SELECT * FROM Activite";
            PreparedStatement pre = connection.prepareStatement(req);
            ResultSet rs = pre.executeQuery();            
            while (rs.next()){
                Activite a = new Activite(rs.getInt("ID"),rs.getString("Nom"),Activite.Discipline.valueOf(rs.getString("Discipline")),
                                             rs.getTimestamp("Date_Event").toLocalDateTime(), rs.getString("Duree"),
                                             rs.getString("Lieu"), rs.getString("Description"), rs.getString("Affiche"));
                                             
                list.add(a);
            }
        return list;        
    }
    
    
}

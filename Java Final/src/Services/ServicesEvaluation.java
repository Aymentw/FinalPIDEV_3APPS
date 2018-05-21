/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;
import Entities.Evenement;
import Entities.Evaluation;
import Tech.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.CallNode;



/**
 *
 * @author Mechlaoui
 */
public class ServicesEvaluation{

public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;
    
    public ServicesEvaluation(){
        try {
            ste = connection.createStatement();
        } catch (SQLException e){
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, e);
        }
    }
   
    public void AjouterNote(Evaluation e) throws SQLException, Exception{
        
        String test = "SELECT Note FROM Evaluation WHERE ID_Evenement=? and ID_User=?";
        PreparedStatement preTest = connection.prepareStatement(test);
        preTest.setInt(1, e.getEvent().getID());
        preTest.setInt(2, e.getID_User());
        ResultSet rs = preTest.executeQuery();
        if (!rs.next() || rs.wasNull()){
            String req = "INSERT INTO Evaluation (ID_Evenement,ID_User,Note)";
               req+=" VALUES (?,?,?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, e.getEvent().getID());
        pre.setInt(2, e.getID_User());
        pre.setInt(3, e.getNote());
        pre.executeUpdate();
        }
        else{
        throw new Exception("Vous avez déjà noté cet évenement");
        }
        
    }
    
    public void ModifierNote(Evaluation e) throws SQLException{
        String req = "UPDATE Evaluation SET Note=? WHERE ID_Evaluation=? and ID_Evenement=? and ID_User=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, e.getNote());
        pre.setInt(2, e.getID_Evaluation());
        pre.setInt(3, e.getEvent().getID());
        pre.setInt(4, e.getID_User());
        pre.executeUpdate();
    }
    
    public void SupprimerNote(Evaluation e) throws SQLException{
        String req = "DELETE FROM Evaluation WHERE ID_Evaluation=? and ID_Evenement=? and ID_User=?";
        PreparedStatement pre = connection.prepareStatement(req);;
        pre.setInt(1, e.getID_Evaluation());
        pre.setInt(2, e.getEvent().getID());
        pre.setInt(3, e.getID_User());
        pre.executeUpdate();
    }
    
    

    
}

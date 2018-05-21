/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.Reservation;
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
 * @author Mechlaoui
 */
public class ServicesReservation {
    public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;
    
    public ServicesReservation(){
        try {
            ste = connection.createStatement();
        } catch (SQLException e){
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public boolean Reserver(Reservation r) throws SQLException{
        
        String reqTest = "SELECT * FROM Reservation WHERE ID_Evenement=? and ID_Participant=? and Etat=?";
        PreparedStatement preTest = connection.prepareStatement(reqTest);
        preTest.setInt(1, r.getEvent().getID());
        preTest.setInt(2, r.getID_Participant());
        preTest.setString(3, r.getEtat().Confirmé.name());
        ResultSet rsTest = preTest.executeQuery();
        
        if(!rsTest.next()){
        
        String req = "INSERT INTO Reservation (ID_Evenement,ID_Participant,Type_Reservation,Tarif,Numero_Ticket,Etat)";
               req+= " VALUES (?,?,?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, r.getEvent().getID());
        pre.setInt(2, r.getID_Participant());
        if(r.getEvent().getType_Reservation().name() == "Payante"){
        pre.setString(3, r.getEvent().getType_Reservation().name());
        pre.setDouble(4, r.getTarif());
        String req1 = "SELECT MAX(Numero_Ticket) FROM Reservation WHERE ID_Evenement=?";
        PreparedStatement pre1 = connection.prepareStatement(req1);
        pre1.setInt(1, r.getEvent().getID());
        ResultSet rs = pre1.executeQuery();
        rs.next();
        if ( rs.getInt(1) > 0){
        pre.setInt(5, rs.getInt(1)+1);
        }
        else{
        pre.setInt(5, 1);
        }
        
        }
        else{
        pre.setString(3, Reservation.Type_Reservation.Gratuite.name());
        pre.setNull(4, java.sql.Types.INTEGER);
        pre.setNull(5, java.sql.Types.INTEGER);
        }
        pre.setString(6, Reservation.Etat_Reservation.Confirmé.name());
        pre.executeUpdate();
             return true;
        }
        else {
            return false;
        }
        
    }
    
    public void AnnulerReservation(Reservation r, int id_res) throws SQLException{
        String req = "SELECT * FROM Reservation WHERE ID_Evenement=? and ID_Participant=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, id_res);
        pre.setInt(2, r.getID_Participant());
        ResultSet rs = pre.executeQuery();
        while(rs.next()){
        int id = rs.getInt("ID_Reservation");
        String req1 = "UPDATE Reservation SET Etat=? WHERE ID_Reservation= ?";
        PreparedStatement pre1 = connection.prepareStatement(req1);
        pre1.setString(1, Reservation.Etat_Reservation.Annulé.name());
        pre1.setInt(2, id);
        pre1.executeUpdate();
        }
   
    }
    
    public void Reconfirmer(Reservation r, int id_res) throws SQLException{
         String req = "SELECT * FROM Reservation WHERE ID_Evenement=? and ID_Participant=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, id_res);
        pre.setInt(2, r.getID_Participant());
        ResultSet rs = pre.executeQuery();
        rs.next();
        int id = rs.getInt("ID_Reservation");
        String req1 = "UPDATE Reservation SET Etat=? WHERE ID_Reservation= ?";
        PreparedStatement pre1 = connection.prepareStatement(req1);
        pre1.setString(1, Reservation.Etat_Reservation.Confirmé.name());
        pre1.setInt(2, id);
        pre1.executeUpdate();

    }
    
    public boolean HasReserved(Evenement event, int ID_User) throws SQLException{
        String req = "SELECT * FROM Reservation WHERE ID_Evenement=? and ID_Participant=? and Etat='Confirmé'";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, event.getID());
        pre.setInt(2, ID_User);
        ResultSet rs = pre.executeQuery();
        return rs.next() ? false : true;
    }
    
    public List<Reservation> SelectReservationstUser(int ID_User) throws SQLException{
        
        List<Reservation> list = new ArrayList<>();
            //String req = "SELECT * FROM evenement E JOIN reservation r ON E.ID=R.ID_Evenement WHERE R.ID_Participant=? and E.Date_Event > NOW()";
            String req = "SELECT * FROM evenement E JOIN reservation r ON E.ID=R.ID_Evenement WHERE R.ID_Participant=?";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setInt(1, ID_User);
            ResultSet rs = pre.executeQuery();            
            while (rs.next()){
                Evenement event = new Evenement(rs.getInt("E.ID"),rs.getInt("E.ID_Organisateur"),rs.getString("E.Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("E.Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("E.Type_Reservation"))),
                                             rs.getTimestamp("E.Date_Event").toLocalDateTime(), rs.getString("E.Duree"),
                                             rs.getString("E.Lieu"),rs.getInt("E.Nombre") , rs.getString("E.Description"),
                                             rs.getString("E.Affiche"),Evenement.EtatEvent.valueOf(rs.getString("E.Etat")),
                                             rs.getInt("E.Prix"));
                if (rs.getInt("R.Tarif") != 0 && rs.getInt("R.Numero_Ticket") != 0 ){
                Reservation res = new Reservation(rs.getInt("R.ID_Reservation"),event,
                                             rs.getInt("R.ID_Participant"), Reservation.Type_Reservation.valueOf(rs.getString("R.Type_Reservation")),
                                             rs.getDouble("R.Tarif"),rs.getInt("R.Numero_Ticket"), Reservation.Etat_Reservation.valueOf(rs.getString("R.Etat")));
                
                                 
                list.add(res);
                }
                else{
                Reservation res = new Reservation(rs.getInt("R.ID_Reservation"),event,
                                             rs.getInt("R.ID_Participant"), Reservation.Type_Reservation.valueOf(rs.getString("R.Type_Reservation")), Reservation.Etat_Reservation.valueOf(rs.getString("R.Etat")));
                
                                 
                list.add(res);    
                
                }
                
            }
        return list;        
    }
}

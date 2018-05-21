/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;
import Entities.Evenement;
import Entities.Reservation;
import Tech.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mechlaoui
 */
public class ServicesEvenement {
    public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;
    
    public ServicesEvenement(){
        try {
            ste = connection.createStatement();
        } catch (SQLException e){
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public void AjouterEvenementUser(Evenement ev, int ID_organisteur) throws SQLException{
        String req = "INSERT INTO Evenement (ID_Organisateur,Nom,Type,Type_Reservation,Date_Event,Duree,Lieu,Nombre,Description,Affiche,Etat,Prix)";
               req+=" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, ID_organisteur);
        pre.setString(2, ev.getNom());        
        pre.setString(3, ev.getType().name());
        pre.setString(4, ev.getType_Reservation().name());
        pre.setTimestamp(5, Timestamp.valueOf(ev.getDate_Event()));
        pre.setString(6, ev.getDuree());
        pre.setString(7, ev.getLieu());
        pre.setInt(8, ev.getNombre());
        pre.setString(9, ev.getDescription());
        pre.setString(10, ev.getAffiche());
        pre.setString(11, ev.getEtat().name());
        if (ev.getType_Reservation().name() == "Payante"){
        pre.setInt(12, ev.getPrix());
        }
        else {
        pre.setNull(12, java.sql.Types.INTEGER);
        }
        pre.executeUpdate();
        
        
    }
    
      public void ModifierEvenementUser(Evenement ev) throws SQLException {
        String req = "UPDATE `evenement` SET Nom=?, Type=?, Type_Reservation=?, Date_Event=?, Duree=?, Lieu=?, Nombre=?, Description=?, Affiche=?, Prix=? WHERE ID=? and `ID_Organisateur`=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, ev.getNom());
        pre.setString(2, ev.getType().name());
        pre.setString(3, ev.getType_Reservation().name());
        pre.setTimestamp(4, Timestamp.valueOf(ev.getDate_Event()));
        pre.setString(5, ev.getDuree());
        pre.setString(6, ev.getLieu());
        pre.setInt(7, ev.getNombre());
        pre.setString(8, ev.getDescription());
        pre.setString(9, ev.getAffiche());
        if (ev.getType_Reservation().name() == "Payante"){
        pre.setInt(10, ev.getPrix());
        }
        else {
            pre.setNull(12, java.sql.Types.INTEGER);            
        }
        pre.setInt(11, ev.getID());
        pre.setInt(12, ev.getID_Organisateur());
        pre.executeUpdate();
        
    }
    
    public void ArchiverEvenement(Evenement ev, int ID_User) throws SQLException {
        String req = "UPDATE Evenement SET Etat=? WHERE ID=? and ID_Organisateur=?";
        
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setString(1, Evenement.EtatEvent.Archivé.name());
            pre.setInt(2, ev.getID());
            pre.setInt(3, ID_User);
            pre.executeUpdate();
        
    }
    
     public List<Evenement> SelectEvenementAll() throws SQLException{
        
        List<Evenement> list = new ArrayList<>();
            String req = "SELECT * FROM Evenement WHERE Etat='Oui'";
            PreparedStatement pre = connection.prepareStatement(req);
            ResultSet rs = pre.executeQuery();            
            while (rs.next()){
                if (rs.getString("Type_Reservation").equals("Payante")){
                Evenement ev = new Evenement(rs.getInt("ID"),rs.getInt("ID_Organisateur"),rs.getString("Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("Type_Reservation"))),
                                             rs.getTimestamp("Date_Event").toLocalDateTime(), rs.getString("Duree"),
                                             rs.getString("Lieu"),rs.getInt("Nombre") , rs.getString("Description"),
                                             rs.getString("Affiche"),Evenement.EtatEvent.valueOf(rs.getString("Etat")),rs.getInt("Prix"));
                list.add(ev);
                }
                else{
                    Evenement ev = new Evenement(rs.getInt("ID"),rs.getInt("ID_Organisateur"),rs.getString("Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("Type_Reservation"))),
                                             rs.getTimestamp("Date_Event").toLocalDateTime(), rs.getString("Duree"),
                                             rs.getString("Lieu"),rs.getInt("Nombre") , rs.getString("Description"),
                                             rs.getString("Affiche"),Evenement.EtatEvent.valueOf(rs.getString("Etat")));
                list.add(ev);
                }
            }
        return list;        
    }
    
    public List<Evenement> SelectEvenementUser(int ID_User) throws SQLException{
        
        List<Evenement> list = new ArrayList<>();
            String req = "SELECT * FROM Evenement WHERE ID_Organisateur=? and Etat !='Archivé' ";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setInt(1, ID_User);
            ResultSet rs = pre.executeQuery();            
            while (rs.next()){
                if (rs.getString("Type_Reservation").equals("Payante")){
                Evenement ev = new Evenement(rs.getInt("ID"),rs.getInt("ID_Organisateur"),rs.getString("Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("Type_Reservation"))),
                                             rs.getTimestamp("Date_Event").toLocalDateTime(), rs.getString("Duree"),
                                             rs.getString("Lieu"),rs.getInt("Nombre") , rs.getString("Description"),
                                             rs.getString("Affiche"),Evenement.EtatEvent.valueOf(rs.getString("Etat")),rs.getInt("Prix"));
                list.add(ev);
                }
                else{
                    Evenement ev = new Evenement(rs.getInt("ID"),rs.getInt("ID_Organisateur"),rs.getString("Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("Type_Reservation"))),
                                             rs.getTimestamp("Date_Event").toLocalDateTime(), rs.getString("Duree"),
                                             rs.getString("Lieu"),rs.getInt("Nombre") , rs.getString("Description"),
                                             rs.getString("Affiche"),Evenement.EtatEvent.valueOf(rs.getString("Etat")));
                list.add(ev);
                }
            }
        return list;        
    }
    
    public Evenement SelectEvenementByID(int ID) throws SQLException{
        
        List<Evenement> list = new ArrayList<>();
            String req = "SELECT * FROM Evenement WHERE ID=?";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setInt(1, ID);
            ResultSet rs = pre.executeQuery();            
            rs.next();
                Evenement ev = new Evenement(rs.getInt("ID"),rs.getInt("ID_Organisateur"),rs.getString("Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("Type_Reservation"))),
                                             rs.getTimestamp("Date_Event").toLocalDateTime(), rs.getString("Duree"),
                                             rs.getString("Lieu"),rs.getInt("Nombre") , rs.getString("Description"),
                                             rs.getString("Affiche"),Evenement.EtatEvent.valueOf(rs.getString("Etat")),rs.getInt("Prix"));
                                             
                return ev;
       
    }
    
    
    public void ValiderEvenement(Evenement ev) throws SQLException{
        String req = "UPDATE Evenement SET Etat=? WHERE ID=?";
        
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setString(1, Evenement.EtatEvent.Oui.name());
            pre.setInt(2, ev.getID());
            pre.executeUpdate();
    }
    
    public void SupprimerEvenement(Evenement e) throws SQLException{
        String req = "DELETE FROM Evenement WHERE ID=?";
        
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setInt(1, e.getID());
            pre.executeUpdate();
    }
    
    
    public HashMap<Evenement,List<Commentaire>> SelectEventsWeek() throws SQLException{
        
        HashMap<Evenement,List<Commentaire>> list = new HashMap<>();
            //String req = "SELECT * FROM evenement JOIN commentaire ON evenement.ID=commentaire.ID_Evenement where WEEK(evenement.Date_Event,1) = WEEK(CURRENT_TIMESTAMP,1) and Evenement.Etat='oui'";
            String req = "SELECT * FROM evenement E LEFT JOIN commentaire C ON E.ID=C.ID_Evenement where E.Etat='oui'";    
            PreparedStatement pre = connection.prepareStatement(req);
            ResultSet rs = pre.executeQuery();   
            while (rs.next()){
                  Evenement event = new Evenement(rs.getInt("E.ID"),rs.getInt("E.ID_Organisateur"),rs.getString("E.Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("E.Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("E.Type_Reservation"))),
                                             rs.getTimestamp("E.Date_Event").toLocalDateTime(), rs.getString("E.Duree"),
                                             rs.getString("E.Lieu"),rs.getInt("E.Nombre") , rs.getString("E.Description"),
                                             rs.getString("E.Affiche"),Evenement.EtatEvent.valueOf(rs.getString("E.Etat")),
                                             rs.getInt("E.Prix"));
                if (rs.getString("C.Contenu") != null){
                Commentaire c = new Commentaire(rs.getInt("C.id"), event, rs.getInt("C.id_user"),
                                                rs.getTimestamp("C.Post").toLocalDateTime(),rs.getString("C.Contenu"),
                                                Commentaire.Etat_Commentaire.valueOf(rs.getString("C.Etat_Commentaire")));
                                             
                list.computeIfAbsent(event, listcom -> new ArrayList<>()).add(c);
                }
                else {
                    list.computeIfAbsent(event, listcom -> new ArrayList<>());
                }
                
            } 

                
        
        return list;        
    }
    
    public HashMap<Evenement,List<Reservation>> SelectReservationsEvents() throws SQLException{
        
        HashMap<Evenement,List<Reservation>> list = new HashMap<>();
            String req = "SELECT * FROM evenement E LEFT JOIN reservation R ON E.ID=R.ID_Evenement where E.Etat='oui'";    
            PreparedStatement pre = connection.prepareStatement(req);
            ResultSet rs = pre.executeQuery();   
            while (rs.next()){
                  Evenement event = new Evenement(rs.getInt("E.ID"),rs.getInt("E.ID_Organisateur"),rs.getString("E.Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("E.Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("E.Type_Reservation"))),
                                             rs.getTimestamp("E.Date_Event").toLocalDateTime(), rs.getString("E.Duree"),
                                             rs.getString("E.Lieu"),rs.getInt("E.Nombre") , rs.getString("E.Description"),
                                             rs.getString("E.Affiche"),Evenement.EtatEvent.valueOf(rs.getString("E.Etat")),rs.getInt("E.Prix"));
                if (rs.getInt("R.ID_Reservation") != 0){
                Reservation r = new Reservation(rs.getInt("R.ID_Reservation"), event, rs.getInt("R.ID_Participant"),
                                                Reservation.Type_Reservation.valueOf(event.getType_Reservation().name()),
                                                event.getPrix(), rs.getInt("R.Numero_Ticket"),
                                                Reservation.Etat_Reservation.valueOf(rs.getString("R.Etat")));
                                                
                list.computeIfAbsent(event, listcom -> new ArrayList<>()).add(r);
                }
                else {
                    //list.put(event, new ArrayList<>());
                    list.computeIfAbsent(event, listcom -> new ArrayList<>());
                }
                
            } 

                
        
        return list;        
    }
    
    
    
  
    
    
}

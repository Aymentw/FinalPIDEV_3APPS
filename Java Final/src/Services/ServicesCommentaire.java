/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.*;
import Tech.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mechlaoui
 */
public class ServicesCommentaire {
    
    public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;
    
    public ServicesCommentaire(){
        try {
            ste = connection.createStatement();
        } catch (SQLException e){
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, e);
        }
    }
      
       public int CommenterEvent(Commentaire c, int ID_user) throws SQLException{

       String req = "INSERT INTO Commentaire (ID_Evenement,Post,Contenu,Etat_Commentaire,id_user)";
               req+=" VALUES (?,?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pre.setInt(1, c.getEvent().getID());
        pre.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        pre.setString(3, c.getContenu());
        pre.setString(4, c.getEtat_Commentaire().name());
        pre.setInt(5, ID_user);
        pre.executeUpdate();
        ResultSet rs = pre.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
   } 
       
       public int getGeneratedKey(){
           return 0;
       }
       
        public void ModifierCommentaireEvent(Commentaire c, int ID_User) throws SQLException {
        String req = "UPDATE Commentaire SET Post=?, Contenu=? WHERE id=? and ID_Evenement=? and id_user=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        pre.setString(2, c.getContenu());
        pre.setInt(3, c.getId());
        pre.setInt(4, c.getEvent().getID());
        pre.setInt(5, ID_User);
        pre.executeUpdate();
        
    }
         public void SupprimerCommentaireEvent(Commentaire c, int ID_User) throws SQLException {
        String req = "DELETE  FROM Commentaire WHERE id=? and ID_Evenement=? and id_user=?";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setInt(1, c.getId());
            pre.setInt(2, c.getEvent().getID());
            pre.setInt(3, ID_User);
            pre.executeUpdate();
        
    }
        public void ReporterCommentaireEvent(Commentaire c) throws SQLException {
        String req = "UPDATE Commentaire SET Etat_Commentaire=? WHERE id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, Commentaire.Etat_Commentaire.Reported.name());
        pre.setInt(2, c.getId());
        pre.executeUpdate(); 
        }
         
         
         public List<Commentaire> SelectCommentaireByEvent(Evenement ev) throws SQLException{
        
        List<Commentaire> ListCommentaires = new ArrayList<>();
            String req = "SELECT * FROM Commentaire JOIN Evenement ON Commentaire.ID_Evenement=Evenement.ID and evenement.ID=?";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setInt(1, ev.getID());
            ResultSet rs = pre.executeQuery();            
            while (rs.next()){
                Commentaire c = new Commentaire(rs.getInt("id"), ev, rs.getInt("id_user"),
                                                rs.getTimestamp("Post").toLocalDateTime(),rs.getString("Contenu"),
                                                Commentaire.Etat_Commentaire.valueOf(rs.getString("Etat_Commentaire")));
                                             
            ListCommentaires.add(c);
            } 
                
        
        return ListCommentaires;        
    }
         
          public HashMap<Evenement,List<Commentaire>> SelectCommentaireEvent(Evenement ev) throws SQLException{
        
        HashMap<Evenement,List<Commentaire>> list = new HashMap<>();
        List<Commentaire> ListCommentaires = new ArrayList<>();
            String req = "SELECT * FROM Commentaire JOIN Evenement ON Commentaire.ID_Evenement=Evenement.ID WHERE Evenement.ID=?";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setInt(1, ev.getID());
            ResultSet rs = pre.executeQuery();            
            while (rs.next()){
                  Evenement event = new Evenement(rs.getInt("ID"),rs.getInt("ID_Organisateur"),rs.getString("Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("Type_Reservation"))),
                                             rs.getTimestamp("Date_Event").toLocalDateTime(), rs.getString("Duree"),
                                             rs.getString("Lieu"),rs.getInt("Nombre") , rs.getString("Description"),
                                             rs.getString("Affiche"),Evenement.EtatEvent.valueOf(rs.getString("Etat")));
                Commentaire c = new Commentaire(rs.getInt("id"), event, rs.getInt("ID_User"),
                                                rs.getTimestamp("Post").toLocalDateTime(),rs.getString("Contenu"),
                                                Commentaire.Etat_Commentaire.valueOf(rs.getString("Etat_Commentaire")));
                                             
            ListCommentaires.add(c);
            } 
            list.put(ev, ListCommentaires);
                
        
        return list;        
    }
          
          
          public HashMap<Evenement,List<Commentaire>> SelectCommentaires() throws SQLException{
        
        HashMap<Evenement,List<Commentaire>> list = new HashMap<>();
            String req = "SELECT * FROM Commentaire JOIN Evenement ON Commentaire.ID_Evenement=Evenement.ID";
            PreparedStatement pre = connection.prepareStatement(req);
            ResultSet rs = pre.executeQuery();            
            while (rs.next()){
                  Evenement event = new Evenement(rs.getInt("ID"),rs.getInt("ID_Organisateur"),rs.getString("Nom"),
                                            (Evenement.TypeEvent.valueOf(rs.getString("Type"))),
                                            (Evenement.Type_Reservation.valueOf(rs.getString("Type_Reservation"))),
                                             rs.getTimestamp("Date_Event").toLocalDateTime(), rs.getString("Duree"),
                                             rs.getString("Lieu"),rs.getInt("Nombre") , rs.getString("Description"),
                                             rs.getString("Affiche"),Evenement.EtatEvent.valueOf(rs.getString("Etat")));
                Commentaire c = new Commentaire(rs.getInt("id"), event, rs.getInt("id_user"),
                                                rs.getTimestamp("Post").toLocalDateTime(),rs.getString("Contenu"),
                                                Commentaire.Etat_Commentaire.valueOf(rs.getString("Etat_Commentaire")));
                                             
            list.computeIfAbsent(event, listcom -> new ArrayList<>()).add(c);
            } 

                
        
        return list;        
    }
          
          ////////////////////////commentaire Document////////////////////////
          
              public void AjouterCommentaireDoc(Commentaire c) throws SQLException
    {
        String req="insert into commentaire(Contenu, id_document, id_user) values(?,?,?)";
        PreparedStatement pre=connection.prepareStatement(req);
        pre.setString(1, c.getContenu());
        pre.setInt(2, c.getId_doc());
        pre.setInt(3, c.getID_User());
        pre.executeUpdate();
    }
    
     public void SupprimerCommentaireDoc(int n) throws SQLException
    {
        String req="delete from commentaire where id='"+n+"'";
        Statement st = connection.createStatement();
        st.execute(req);
    }
     
      public void MAJCommentaireDoc(int id, String contenu, int rating) throws SQLException
    {
         String requete3 = "update commentaire set Contenu=?, rating=? where id='"+id+"'" ;
         PreparedStatement pst2 = connection.prepareStatement(requete3) ;
         pst2.setString(1, contenu);
         pst2.setInt(2, rating);
         pst2.executeUpdate() ; 
    }
    
    public ObservableList<Commentaire> data;
   
    public ObservableList<Commentaire> ListComDoc(int id) throws SQLException
    {
        try
        {
            Connection conn = DataSource.GetInstance().GetConnection() ;
            data = FXCollections.observableArrayList();          
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM commentaire where id_document = '"+id+"'");
            while (rs.next()) 
            { 
                data.add(new Commentaire(rs.getString("contenu"), rs.getInt(3)));
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Error"+ex);
        }
                    return data;
    }
        public void AjouterCommentaireTopic(Commentaire c) throws SQLException {
        String req = "insert into commentaire(Contenu,id_topic) values(?,?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, c.getContenu());
        pre.setInt(2, c.getId_topic());
        pre.execute();
    }

    public ObservableList<Commentaire> CommentaireTopic(int id) throws SQLException {
        try {
            Connection conn = DataSource.GetInstance().GetConnection();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM commentaire where id_topic = '" + id + "'");
            while (rs.next()) {
                data.add(new Commentaire(rs.getString("Contenu"),rs.getInt("id"),""));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        return data;
    }

    public void MAJCommentaireTopic(Commentaire c) throws SQLException {
        String requete3 = "UPDATE commentaire SET Contenu=? WHERE id=?";
        PreparedStatement pst2 = connection.prepareStatement(requete3);
        pst2.setString(1, c.getContenu());
        pst2.setInt(2, c.getId());
        pst2.executeUpdate();
    }
    
     public void SupprimerCommentaireTopic(int id) throws SQLException {
        String req = "DELETE FROM commentaire WHERE id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, id);
        pre.execute();
    } 
         
}

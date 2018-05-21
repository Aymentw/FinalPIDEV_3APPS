/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Covoiturage;
import Entities.Covoiturage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Tech.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS-PC
 */
public class ServicesCovoiturage {
    public Connection con= DataSource.GetInstance().GetConnection();
    public Statement ste;
    public ServicesCovoiturage(){
        try {
            ste= con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Covoiturage> getCovoiturage() {
    List<Covoiturage> annonces= new ArrayList<>();
        try {
            ResultSet rs=ste.executeQuery("select * from covoiturage");
            
           
            while(rs.next()){
                Covoiturage A=new Covoiturage(rs.getInt("id"), rs.getInt("id_u"),rs.getInt("type"), rs.getString("depart"),rs.getString("destination"),(Date)rs.getDate("date"),rs.getBoolean("vue"));
                annonces.add(A);
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
         return annonces;
    }
    
    public Covoiturage getAnnonce(int id) throws SQLException{
   PreparedStatement pst=con.prepareStatement("select * from covoiturage where id=?");
   pst.setInt(1, id);
   ResultSet rs=pst.executeQuery();
    List<Covoiturage> annonces= new ArrayList<>();
    while(rs.next()){
    Covoiturage A=new Covoiturage(rs.getInt("id"), rs.getInt("id_u"),rs.getInt("type"), rs.getString("depart"),rs.getString("destination"),rs.getDate("date"),rs.getBoolean("vue"));
    annonces.add(A);
    }
  return annonces.get(0);
}
    public List<Covoiturage> getCovoiturageByUser(String nom){
     List<Covoiturage> annonces= new ArrayList<>();
        try {
           
             PreparedStatement pst=con.prepareStatement("SELECT C.* from covoiturage C JOIN user U on C.id_u=U.id where U.username like '"+nom+"%'");
  
   ResultSet rs=pst.executeQuery();          
            while(rs.next()){
                Covoiturage A=new Covoiturage(rs.getInt("id"), rs.getInt("id_u"),rs.getInt("type"), rs.getString("depart"),rs.getString("destination"),(Date)rs.getDate("date"),rs.getBoolean("vue"));
                annonces.add(A);
            }
            System.out.println(annonces.size());
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
         return annonces;
    }
      public List<Covoiturage> getCovoiturageSortedbyusername(){
     List<Covoiturage> annonces= new ArrayList<>();
        try {
           
             PreparedStatement pst=con.prepareStatement("SELECT C.* FROM `covoiturage` C join user U on u.id=C.id_u ORDER BY U.username");
  
   ResultSet rs=pst.executeQuery();          
            while(rs.next()){
                Covoiturage A=new Covoiturage(rs.getInt("id"), rs.getInt("id_u"),rs.getInt("type"), rs.getString("depart"),rs.getString("destination"),(Date)rs.getDate("date"),rs.getBoolean("vue"));
                annonces.add(A);
            }
            System.out.println(annonces.size());
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
         return annonces;
    }
    public void insertAnnonce(Covoiturage A){
        try {
            PreparedStatement pst=con.prepareStatement("insert into covoiturage (id_u,etat,type,depart,destination,date,vue) values(?,?,?,?,?,?,?) ");
        pst.setInt(1, A.getId_u());
        pst.setInt(2, A.getEtat());
        pst.setInt(3, A.getType());
        pst.setString(4,A.getDepart());
        pst.setString(5,A.getDestination());
        pst.setDate(6, (Date) A.getDate());
        pst.setBoolean(7,A.getVue());
        pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public void deleteAnnonce(Covoiturage A){
        try {
            PreparedStatement pst=con.prepareStatement("delete  from covoiturage where id=? ");
        pst.setInt(1,A.getId());
        pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public void modifyAnnonce(Covoiturage A){
    try {
            PreparedStatement pst=con.prepareStatement("update covoiturage set depart=?, destination=?,etat=?,type=?,date=?,vue=? where id=? ");
     
       pst.setString(1,A.getDepart());
        pst.setString(2,A.getDestination());
        pst.setInt(3, A.getEtat());
        pst.setInt(4, A.getType());
        System.out.println(A.getDate());
        pst.setDate(5, A.getDate());
        pst.setBoolean(6,A.getVue());
       pst.setInt(7, A.getId());
        pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public ObservableList<Covoiturage> affichercovoiturage() throws SQLException {
        
        ObservableList<Covoiturage> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM covoiturage ";
        PreparedStatement ste = con.prepareStatement(req);
        ResultSet rs = ste.executeQuery();
        
        while (rs.next()) {
            Covoiturage c = new Covoiturage(rs.getInt("id"), rs.getInt("id_u"),rs.getInt("type"), rs.getString("depart"),rs.getString("destination"),(Date)rs.getDate("date"),rs.getBoolean("vue"));
            
            list.add(c);
        }
        
        return list;
    }
}
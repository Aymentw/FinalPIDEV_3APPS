/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Colocation;
import Entities.Session;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Tech.DataSource;
import java.net.URISyntaxException;

/**
 *
 * @author Karim
 */
public class ServiceCol {
    
    public Connection cnx = DataSource.GetInstance().GetConnection();
    public Statement ste;
    
    public ServiceCol() {
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterColocation(Colocation c,int id_u) throws SQLException {
        String req = "insert into colocation (type_colocation,adresse,sexe,prix,place_dispo,type_maison,description,path,id_user) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, c.getType());
        pre.setString(2, c.getAdresse());
        pre.setString(3, c.getSexe());
        pre.setFloat(4, c.getPrix());
        pre.setInt(5, c.getPlace_dispo());
        pre.setString(6, c.getType_maison());
        pre.setString(7, c.getDescription());
        pre.setString(8, c.getPath());
        pre.setInt(9,id_u);
        pre.executeUpdate();
        System.out.println("colocation ajoutée");
    }
    
    public void ChercherColocation(Colocation c) throws SQLException {
        String req = "insert into colocation (type_colocation,sexe,place_dispo,type_maison,description) values(?,?,?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, c.getType());
        pre.setString(2, c.getSexe());
        
        pre.setInt(3, c.getPlace_dispo());
        pre.setString(4, c.getType_maison());
        pre.setString(5, c.getDescription());
        pre.executeUpdate();
        System.out.println("Demande ajoutée");
    }
    
    public void deleteColocation(int id) {
        String requete = "delete from Colocation where id=" + id;
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("colocation supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCol.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateCol(Colocation c) throws SQLException {
        String requete3 = "update colocation set type_colocation=? ,adresse=?, sexe=?, prix=?, place_dispo=?, type_maison=?, description=?, path=? where id=?";
        PreparedStatement pst2 = cnx.prepareStatement(requete3);
        pst2.setString(1, c.getType());
        pst2.setString(2, c.getAdresse());
        pst2.setString(3, c.getSexe());
        pst2.setFloat(4, c.getPrix());
        pst2.setInt(5, c.getPlace_dispo());
        pst2.setString(6, c.getType_maison());
        
        pst2.setString(7, c.getDescription());
        pst2.setString(8, c.getPath());
        pst2.setInt(9, c.getId());
        pst2.executeUpdate();
        System.out.println("colocation modifiée");
    }
    
    public void SearchCol() throws SQLException {
        ResultSet res = ste.executeQuery("Select * from colocation where ");
        while (res.next()) {
            System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getFloat(4) + " " + res.getInt(5) + " " + res.getString(6) + " " + res.getString(7) + " " + res.getString(8) + " " + res.getInt(9));
        }
    }

    /* public List<Colocation> getColocations() {
    try {
    String req = "SELECT * FROM colocation WHERE `type_colocation` = annonce";
    ResultSet res = ste.executeQuery(req);
    List<Colocation> colocations = new ArrayList<>();
    while (res.next()) {
    Colocation col = new Colocation();
    col.setAdresse(res.getString(3));
    col.setDescription(res.getString(8));
    col.setSexe(res.getString(4));
    col.setPrix(res.getFloat(5));
    col.setPlace_dispo(res.getInt(6));
    col.setType_maison(res.getString(7));
    colocations.add(col);
    
    }
    return colocations;
    
    } catch (SQLException ex) {
    Logger.getLogger(ServiceCol.class.getName()).log(Level.SEVERE, null, ex);
    return null;
    }
    
    }*/
    public ObservableList<Colocation> affichercolocation() throws SQLException {
        
        ObservableList<Colocation> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM colocation where type_colocation='annonce' ";
        PreparedStatement ste = cnx.prepareStatement(req);
        ResultSet result = ste.executeQuery();
        
        while (result.next()) {
            Colocation c = new Colocation(result.getInt("id"), result.getString("adresse"), result.getString("sexe"),
                     result.getFloat("prix"), result.getInt("place_dispo"), result.getString("type_maison"), result.getString("description"), result.getInt("id_user"));
            
            try {
                c.setStar1(new ImageView(new Image(getClass().getResource("/Resources/star1.png").toURI().toString(), 30, 30, true, true)));
            } catch (URISyntaxException ex) {
                Logger.getLogger(ServiceCol.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            list.add(c);
        }
        
        return list;
    }

    public ObservableList<Colocation> affichercolocdem() throws SQLException {
        
        ObservableList<Colocation> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM colocation where type_colocation='demande'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ResultSet result = ste.executeQuery();
        
        while (result.next()) {
            Colocation c = new Colocation(result.getString("sexe"),
                     result.getInt("place_dispo"), result.getString("type_maison"), result.getString("description"), result.getInt("id_user"));
            list.add(c);
        }
        
        return list;
    }

    public ObservableList<Colocation> affichercolocation1(int id_u) throws SQLException {
        
        ObservableList<Colocation> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM `colocation` WHERE  type_colocation='annonce' and id_user=?";
         PreparedStatement pre = cnx.prepareStatement(req);
         pre.setInt(1, id_u);
        ResultSet result = pre.executeQuery();
        
        while (result.next()) {
            Colocation c = new Colocation(result.getInt("id"), result.getString("adresse"), result.getString("sexe"),
                     result.getFloat("prix"), result.getInt("place_dispo"), result.getString("type_maison"), result.getString("description"), result.getInt("id_user"));
            list.add(c);
        }
        
        return list;
    }
     public ObservableList<Colocation> afficherfavoris() throws SQLException {
        
        ObservableList<Colocation> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM colocation c join favoris_colocation fc on c.id=fc.id_colocation ";
        PreparedStatement ste = cnx.prepareStatement(req);
        ResultSet result = ste.executeQuery();
        
                while (result.next()) {
            Colocation c = new Colocation(result.getInt("id"), result.getString("adresse"), result.getString("sexe"),
                     result.getFloat("prix"), result.getInt("place_dispo"), result.getString("type_maison"), result.getString("description"), result.getInt("id_user"));
            list.add(c);
        }
        
        return list;
    }
    public ObservableList<Colocation> affichercolocation_all() throws SQLException {
        
        ObservableList<Colocation> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM colocation ";
        PreparedStatement ste = cnx.prepareStatement(req);
        ResultSet result = ste.executeQuery();
        
        while (result.next()) {
            Colocation c = new Colocation(result.getInt("id"), result.getString("adresse"), result.getString("sexe"),
                     result.getFloat("prix"), result.getInt("place_dispo"), result.getString("type_maison"), result.getString("description"), result.getInt("id_user"));
            
            try {
                c.setStar1(new ImageView(new Image(getClass().getResource("/Resources/star1.png").toURI().toString(), 30, 30, true, true)));
            } catch (URISyntaxException ex) {
                Logger.getLogger(ServiceCol.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            list.add(c);
        }
        
        return list;
    }
}

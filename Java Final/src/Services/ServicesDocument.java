/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Tech.DataSource;
import java.sql.*;
import Entities.Document;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Manai
 */
public class ServicesDocument {
    
    public Connection cnx= DataSource.GetInstance().GetConnection() ;
    public Statement st;
    public ObservableList<Document> data;
    
    public ServicesDocument()
    {
    
        try 
        {
            st=cnx.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesDocument.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void ValiderDocument(int id) throws SQLException
    {
        String req = "update document set etat = '"+"publique"+"' where id='"+id+"'" ;
         st = cnx.createStatement();
        st.execute(req);
    }
    
    public void AjouterDocument(Document d) throws SQLException
    {
        String req="insert into document(titre,description, path, niveau, matiere, etat, id_user) values(?,?,?,?,?,?,?)";
        PreparedStatement pre=cnx.prepareStatement(req);
        pre.setString(1,d.getTitre());
        pre.setString(2,d.getDescription());
        pre.setString(3,d.getPath());
        pre.setString(4,d.getNiveau());
        pre.setString(5,d.getMatiere());
        pre.setString(6, d.getEtat());
        pre.setInt(7, d.getId_user());
        pre.executeUpdate();
    }
    
    public void SupprimerDocument(int n) throws SQLException
    {
        String req="delete from document where id='"+n+"'";
        Statement st = cnx.createStatement();
        st.execute(req);
    }
    
    public void MAJDocument(Document d, int id) throws SQLException
    {
         String requete3 = "update document set titre=?, description=?, path=?, niveau=?, matiere=? where id='"+id+"'" ;
         PreparedStatement pst2 = cnx.prepareStatement(requete3) ;
         pst2.setString(1, d.getTitre());
         pst2.setString(2, d.getDescription());
         pst2.setString(3, d.getPath());
         pst2.setString(4,d.getNiveau());
         pst2.setString(5,d.getMatiere());
         pst2.executeUpdate() ; 
    }
    
    public ObservableList<Document> ListDoc() throws SQLException
    {
        String etat="publique";
         try
        {
            Connection conn = DataSource.GetInstance().GetConnection() ;
            data = FXCollections.observableArrayList();          
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM document");
            while (rs.next()) 
            { 
                if (etat.equals(rs.getString("etat")))
                data.add(new Document(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getString("path"), rs.getString("niveau"), rs.getString("matiere"), rs.getInt("id_user")));
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Error"+ex);
        } 
         return data;
    }
    
    public ObservableList<Document> ListDocAdmin() throws SQLException
    {
        String etat="en attente";
         try
        {
            Connection conn = DataSource.GetInstance().GetConnection() ;
            data = FXCollections.observableArrayList();          
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM document where etat='"+etat+"'");
            while (rs.next()) 
            { 
                if (etat.equals(rs.getString("etat")))
                data.add(new Document(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getString("path"), rs.getString("niveau"), rs.getString("matiere"), rs.getInt("id_user")));
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Error"+ex);
        } 
         return data;
    }
     public ObservableList<Document> ListDoc_all() throws SQLException
    {
      
         try
        {
            Connection conn = DataSource.GetInstance().GetConnection() ;
            data = FXCollections.observableArrayList();          
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM document");
            while (rs.next()) 
            { 
                
                data.add(new Document(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getString("path"), rs.getString("niveau"), rs.getString("matiere"), rs.getInt("id_user")));
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Error"+ex);
        } 
         return data;
    }
    
    
    public ObservableList<Document> SearchDocNiveau(String niveau) throws SQLException
    {
        try
        {
            Connection conn = DataSource.GetInstance().GetConnection() ;
            data = FXCollections.observableArrayList();
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM document where niveau = '"+ niveau +"' and etat = '"+"publique"+"'");
                while (rs.next()) 
                { 
                   data.add(new Document(rs.getString("titre"), rs.getString("niveau"), rs.getString("matiere")));
                }
        }
        catch (SQLException ex)
        {
            System.err.println("Error"+ex);
        }
        return data;
    }
    
    public ObservableList<Document> SearchDocMatiere(String matiere) throws SQLException
    {
        try
        {
            Connection conn = DataSource.GetInstance().GetConnection() ;
            data = FXCollections.observableArrayList();
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM document where matiere like '"+ matiere +"%' and etat = '"+"publique"+"'");
                while (rs.next()) 
                { 
                   data.add(new Document(rs.getString("titre"), rs.getString("niveau"), rs.getString("matiere")));
                }
        }
        catch (SQLException ex)
        {
            System.err.println("Error"+ex);
        }
        return data;
    }
    
}

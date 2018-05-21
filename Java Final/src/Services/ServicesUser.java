/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Document;
import Entities.User;
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
import javafx.collections.FXCollections;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Manai
 */
public class ServicesUser {
    
    public Connection cnx= DataSource.GetInstance().GetConnection() ;
    public Statement st;
    User U = new User();
    
    public ServicesUser()
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
    
    public void AjouterUser(User u) throws SQLException
    {
        String req="insert into user(username, username_canonical, email, email_canonical, password, roles,photo) values(?,?,?,?,?,?,'classic.png')";
        PreparedStatement pre=cnx.prepareStatement(req);
        pre.setString(1,u.getUsername());
        pre.setString(2,u.getUsername());
        pre.setString(3,u.getMail());
        pre.setString(4,u.getMail());
        String password= BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(13));
        pre.setString(5, password.replaceAll("2a", "2y"));
        pre.setString(6,"a:0:{}");
        pre.executeUpdate();
    }
    
    public Boolean verif_username(String username) {
   
          try {
              Statement ste=cnx.createStatement();
              ResultSet rs=ste.executeQuery("select * from user where username ='"+username+"'");
              
            
              while(rs.next()){
                  
                  return true;
              }
              
          } catch (SQLException ex) {
              Logger.getLogger(ServicesUser.class.getName()).log(Level.SEVERE, null, ex);
          }
          return false;
    }
    public User login(String userName) throws SQLException 
    {   
        U = null;
        String req = "SELECT * FROM user WHERE username=?";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setString(1, userName);
            ResultSet rs = pre.executeQuery();            
            while (rs.next()){

              U= new User(rs.getInt("id"),rs.getString("email"),rs.getString("username"),rs.getString("password").replace("2y", "2a"),rs.getString("roles"),rs.getString("niveau"),rs.getString("photo"));       
            }
           
         return U;       
        
    }
    
    public String Username(int id) throws SQLException{
        String req = "SELECT username FROM user WHERE username=?";
        PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery(); 
            rs.next();
            return rs.getString("username");
    }
    public void update(User U){
        try {
            PreparedStatement pst=cnx.prepareStatement("update user set username=?, password=?,niveau=? where id=? ");
            
            pst.setString(1,U.getUsername());
            pst.setString(2,BCrypt.hashpw(U.getPassword(), BCrypt.gensalt(13)));
            pst.setString(3, U.getNiveau());
            pst.setInt(4, U.getId());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updatephoto(User U){
        try {
            PreparedStatement pst=cnx.prepareStatement("update user set photo=? where id=? ");
            
            pst.setString(1,U.getPhoto());
            pst.setInt(2,U.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public User getUserById(int id) {
        try {
            U = null;
            String req = "SELECT * FROM user WHERE id=?";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();            
            while (rs.next()){
                
                U= new User(rs.getInt("id"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getString("niveau"),rs.getString("photo"));       
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U; 
    }
    
}

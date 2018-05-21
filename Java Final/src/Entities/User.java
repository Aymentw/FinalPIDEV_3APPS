/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Manai
 */
public class User {
    
    String email, username, password, role,niveau,photo;

    
    int id;
    
    public User (){
        
    }

    public User(int id,String email, String username, String password, String role, String niveau, String photo) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.niveau = niveau;
        this.photo = photo;
        this.id = id;
    }

 

   
    public User(String email, String username, String password, String role,String niveau) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.niveau=niveau;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public User( int id,String email, String username, String password, String role,String niveau) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = id;
        this.niveau=niveau;
    }
    
  public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    
}

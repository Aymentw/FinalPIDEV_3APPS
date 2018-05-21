/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;


import java.util.Date;

/**
 *
 * @author Mechlaoui
 */
public class Commentaire {
    private int id;
    private int id_doc;
    private int rating;
    private int id_topic;

    public int getId_topic() {
        return id_topic;
    }

    public void setId_topic(int id_topic) {
        this.id_topic = id_topic;
    }
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId_doc() {
        return id_doc;
    }

    public void setId_doc(int id_doc) {
        this.id_doc = id_doc;
    }
    private Evenement Event;
    private int ID_User;
    private Date Post;
    private String Contenu;
    public enum Etat_Commentaire {
        OK,
        Reported,
    }
    private Etat_Commentaire Etat_Commentaire;
    
    public Commentaire(){
        
    }
    
    public Commentaire(String Contenu, int id_doc, int ID_User) {
        this.id_doc = id_doc;
        this.ID_User = ID_User;
        this.Contenu = Contenu;
    }
    
    public Commentaire(String contenu, int id_doc) {
        this.Contenu = contenu;
        this.id_doc = id_doc;
    }

    public Commentaire(Evenement Event, int ID_User, Date Post, String Contenu, Etat_Commentaire Etat_Commentaire) {
        this.Event = Event;
        this.ID_User = ID_User;
        this.Post = Post;
        this.Contenu = Contenu;
        this.Etat_Commentaire = Etat_Commentaire;
    }

    public Commentaire(int ID_Commentaire, Evenement Event, int ID_User, Date Post, String Contenu, Etat_Commentaire Etat_Commentaire) {
        this.id = ID_Commentaire;
        this.Event = Event;
        this.ID_User = ID_User;
        this.Post = Post;
        this.Contenu = Contenu;
        this.Etat_Commentaire = Etat_Commentaire;
    }
    
    

    public int getId() {
        return id;
    }

    public void setID_Commentaire(int ID_Commentaire) {
        this.id = ID_Commentaire;
    }

    public Evenement getEvent() {
        return Event;
    }

    public void setEvent(Evenement Event) {
        this.Event = Event;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public Date getPost() {
        return Post;
    }

    public void setPost() {
        this.Post = new Date();
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Etat_Commentaire getEtat_Commentaire() {
        return Etat_Commentaire;
    }

    public void setEtat_Commentaire(Etat_Commentaire Etat_Commentaire) {
        this.Etat_Commentaire = Etat_Commentaire;
    }

    public Commentaire(String contenu, int id_etg,String type) {
        this.Contenu = contenu;
        if(type.equals("topic")) this.id_topic=id_etg;
        else if(type.equals("document"))this.id_doc=id_etg;
        else if(type.equals("")) this.id=id_etg;
    }
     public Commentaire(int id,String contenu, int id_etg,String type) {
        this.Contenu = contenu;
        this.id=id;
        if(type.equals("topic")) this.id_topic=id_etg;
        else if(type.equals("document"))this.id_doc=id_etg;
    }


    @Override
    public String toString() {
        return "Commentaire{" + "ID_Commentaire=" + id + ", Event=" + Event.getNom() + ", ID_User=" + ID_User + ", Post=" + Post + ", Contenu=" + Contenu + ", Etat_Commentaire=" + Etat_Commentaire + '}';
    }

    

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Date;

/**
 *
 * @author sana
 */
public class Topic {
   private int id;
   private String sujet;
   private String description, image_name;
   private int idUser;
   private Date date;
   private String ParseDate;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getParseDate() {
        return ParseDate;
    }

    public void setParseDate(String ParseDate) {
        this.ParseDate = ParseDate;
    }
   
    public Topic(int id,int idUser, String sujet, String description, String image_name ) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.image_name = image_name;
        this.idUser = idUser;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    public Topic(String sujet, String description, Date date) {
//        this.sujet = sujet;
//        this.description = description;
//        this.date = date;
//    }

    @Override
    public String toString() {
        return "Topic{" + "id=" + id + ", sujet=" + sujet + ", description=" + description + ", image_name=" + image_name + ", idUser=" + idUser + ", date=" + date + '}';
    }

  

    public Topic() {
    }

//    public Topic(int id, String sujet, String description) {
//        this.id = id;
//        this.sujet = sujet;
//        this.description = description;
//    }

    public Topic(String sujet, String description, String image_name) {
        this.sujet = sujet;
        this.description = description;
        this.image_name = image_name;
    }
    
    
    

   
}

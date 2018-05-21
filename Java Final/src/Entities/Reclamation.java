/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Aymen
 */
public class Reclamation {
    
    public int id;
    public String objet;
    public String description;
    public Date date;
    public int id_user;

    public Reclamation(String objet, String description, int id_user) {
        this.objet = objet;
        this.description = description;
        this.id_user = id_user;
    }

    public Reclamation(String objet, String description, Date date, int id_user) {
        this.objet = objet;
        this.description = description;
        this.date = date;
        this.id_user = id_user;
    }

    public Reclamation(int id, String objet, String description, Date date, int id_user) {
        this.id = id;
        this.objet = objet;
        this.description = description;
        this.date = date;
        this.id_user = id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public Reclamation(int id, String objet, String description) {
        this.id = id;
        this.objet = objet;
        this.description = description;
    }

    public Reclamation(String objet, String description, Date date) {
        this.objet = objet;
        this.description = description;
        this.date = date;
    }

    public Reclamation(String objet, String description) {
        this.objet = objet;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Reclamation(int id, String objet, String description, Date date) {
        this.id = id;
        this.objet = objet;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", objet=" + objet + ", description=" + description + ", date=" + date + '}';
    }
    
    
    
}

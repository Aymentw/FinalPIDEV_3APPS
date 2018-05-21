    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Aymen
 */
public class Topic {
    
    public int id;
    public String sujet;
    public String description;
    public int id_user;

    public Topic(String sujet, String description, int id_user) {
        this.sujet = sujet;
        this.description = description;
        this.id_user = id_user;
    }
    
    

    public int getId() {
        return id;
    }

    public Topic(int id_user, int id, String sujet, String description) {
        this.id_user = id_user;
        this.id = id;
        this.sujet = sujet;
        this.description = description;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public Topic(String sujet, String description) {
        this.sujet = sujet;
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
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
        final Topic other = (Topic) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Topic(int id, String sujet, String description) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Topic{" + "sujet=" + sujet + ", description=" + description + '}';
    }
    
    
    
    
}

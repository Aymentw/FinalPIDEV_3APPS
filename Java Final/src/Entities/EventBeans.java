/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mechlaoui
 */
public class EventBeans extends RecursiveTreeObject<EventBeans> {
    
     public StringProperty ID;
     public StringProperty ID_Organisteur;
     public StringProperty Nom;
     public StringProperty Type;    
     public StringProperty Type_Reservation;
     public StringProperty Date_Event;
     public StringProperty Duree;
     public StringProperty Lieu;
     public StringProperty Nombre;
     public StringProperty Description;
     public StringProperty Affiche;
     public StringProperty Etat;
     public StringProperty Prix;

    public StringProperty getID() {
        return ID;
    }
    
    public String getID_String() {
        return ID.get();
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getID_Organisteur_String() {
        return ID_Organisteur.get();
    }
    
    public StringProperty getID_Organisteur() {
        return ID_Organisteur;
    }
    

    public void setID_Organisteur(String ID_Organisteur) {
        this.ID_Organisteur.set(ID_Organisteur);
    }

    public StringProperty getNom() {
        return Nom;
    }
    
    public String getNom_String() {
        return Nom.get();
    }
    

    public void setNom(String Nom) {
        this.Nom.set(Nom);
    }

    public StringProperty getType() {
        return Type;
    }
    
    public String getType_String() {
        return Type.get();
    }

    public void setType(String Type) {
        this.Type.set(Type);
    }

    public StringProperty getType_Reservation() {
        return Type_Reservation;
    }
    
    public String getType_Reservation_String() {
        return Type_Reservation.get();
    }

    public void setType_Reservation(String Type_Reservation) {
        this.Type_Reservation.set(Type_Reservation);
    }

    public StringProperty getDate_Event() {
        return Date_Event;
    }
    
    public String getDate_Event_String() {
        return Date_Event.get();
    }

    public void setDate_Event(String Date_Event) {
        this.Date_Event.set(Date_Event);
    }

    public StringProperty getDuree() {
        return Duree;
    }
    
    public String getDuree_String() {
        return Duree.get();
    }

    public void setDuree(String Duree) {
        this.Duree.set(Duree);
    }

    public StringProperty getLieu() {
        return Lieu;
    }
    
    public String getLieu_String() {
        return Lieu.get();
    }

    public void setLieu(String Lieu) {
        this.Lieu.set(Lieu);
    }

    public StringProperty getNombre() {
        return Nombre;
    }
    
    public String getNombre_String() {
        return Nombre.get();
    }

    public void setNombre(String Nombre) {
        this.Nombre.set(Nombre);
    }

    public StringProperty getDescription() {
        return Description;
    }
    
    public String getDescription_String() {
        return Description.get();
    }

    public void setDescription(String Description) {
        this.Description.set(Description);
    }

    public StringProperty getAffiche() {
        return Affiche;
    }
    
    public String getAffiche_String() {
        return Affiche.get();
    }

    public void setAffiche(String Affiche) {
        this.Affiche.set(Affiche);
    }

    public StringProperty getEtat() {
        return Etat;
    }
    
    public String getEtat_String() {
        return Etat.get();
    }

    public void setEtat(String Etat) {
        this.Etat.set(Etat);
    }

    public StringProperty getPrix() {
        return Prix;
    }
    
    public String getPrix_String() {
        return Prix.get();
    }

    public void setPrix(String Prix) {
        this.Prix.set(Prix);
    }
    
    public EventBeans(){
        
    }

    public EventBeans(String ID, String ID_Organisteur, String Nom, String Type, String Type_Reservation,
            String Date_Event, String Duree, String Lieu, String Nombre, String Description, String Affiche,
            String Etat, String Prix) {
        this.ID = new SimpleStringProperty(ID);
        this.ID_Organisteur = new SimpleStringProperty(ID_Organisteur);
        this.Nom = new SimpleStringProperty(Nom);
        this.Type = new SimpleStringProperty(Type);
        this.Type_Reservation = new SimpleStringProperty(Type_Reservation);
        this.Date_Event = new SimpleStringProperty(Date_Event);
        this.Duree = new SimpleStringProperty(Duree);
        this.Lieu = new SimpleStringProperty(Lieu);
        this.Nombre = new SimpleStringProperty(Nombre);
        this.Description = new SimpleStringProperty(Description);
        this.Affiche = new SimpleStringProperty(Affiche);
        this.Etat = new SimpleStringProperty(Etat);
        this.Prix = new SimpleStringProperty(Prix);
    }
    
    public EventBeans( String ID_Organisteur, String Nom, String Type, String Type_Reservation,
            String Date_Event, String Duree, String Lieu, String Nombre, String Description, String Affiche,
            String Etat, String Prix) {
        this.ID_Organisteur = new SimpleStringProperty(ID_Organisteur);
        this.Nom = new SimpleStringProperty(Nom);
        this.Type = new SimpleStringProperty(Type);
        this.Type_Reservation = new SimpleStringProperty(Type_Reservation);
        this.Date_Event = new SimpleStringProperty(Date_Event);
        this.Duree = new SimpleStringProperty(Duree);
        this.Lieu = new SimpleStringProperty(Lieu);
        this.Nombre = new SimpleStringProperty(Nombre);
        this.Description = new SimpleStringProperty(Description);
        this.Affiche = new SimpleStringProperty(Affiche);
        this.Etat = new SimpleStringProperty(Etat);
        this.Prix = new SimpleStringProperty(Prix);
    }
    
    
    public EventBeans(String ID_Organisteur, String Nom, String Type, String Type_Reservation,
            String Date_Event, String Duree, String Lieu, String Nombre, String Description, String Affiche,
            String Etat) {
        this.ID_Organisteur = new SimpleStringProperty(ID_Organisteur);
        this.Nom = new SimpleStringProperty(Nom);
        this.Type = new SimpleStringProperty(Type);
        this.Type_Reservation = new SimpleStringProperty(Type_Reservation);
        this.Date_Event = new SimpleStringProperty(Date_Event);
        this.Duree = new SimpleStringProperty(Duree);
        this.Lieu = new SimpleStringProperty(Lieu);
        this.Nombre = new SimpleStringProperty(Nombre);
        this.Description = new SimpleStringProperty(Description);
        this.Affiche = new SimpleStringProperty(Affiche);
        this.Etat = new SimpleStringProperty(Etat);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.ID);
        hash = 23 * hash + Objects.hashCode(this.ID_Organisteur);
        hash = 23 * hash + Objects.hashCode(this.Nom);
        hash = 23 * hash + Objects.hashCode(this.Type);
        hash = 23 * hash + Objects.hashCode(this.Type_Reservation);
        hash = 23 * hash + Objects.hashCode(this.Date_Event);
        hash = 23 * hash + Objects.hashCode(this.Duree);
        hash = 23 * hash + Objects.hashCode(this.Lieu);
        hash = 23 * hash + Objects.hashCode(this.Nombre);
        hash = 23 * hash + Objects.hashCode(this.Description);
        hash = 23 * hash + Objects.hashCode(this.Affiche);
        hash = 23 * hash + Objects.hashCode(this.Etat);
        hash = 23 * hash + Objects.hashCode(this.Prix);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventBeans other = (EventBeans) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.ID_Organisteur, other.ID_Organisteur)) {
            return false;
        }
        if (!Objects.equals(this.Nom, other.Nom)) {
            return false;
        }
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        if (!Objects.equals(this.Type_Reservation, other.Type_Reservation)) {
            return false;
        }
        if (!Objects.equals(this.Date_Event, other.Date_Event)) {
            return false;
        }
        if (!Objects.equals(this.Duree, other.Duree)) {
            return false;
        }
        if (!Objects.equals(this.Lieu, other.Lieu)) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.Affiche, other.Affiche)) {
            return false;
        }
        if (!Objects.equals(this.Etat, other.Etat)) {
            return false;
        }
        if (!Objects.equals(this.Prix, other.Prix)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EventBeans{" + "ID=" + ID + ", ID_Organisteur=" + ID_Organisteur + ", Nom=" + Nom + ", Type=" + Type + ", Type_Reservation=" + Type_Reservation + ", Date_Event=" + Date_Event + ", Duree=" + Duree + ", Lieu=" + Lieu + ", Nombre=" + Nombre + ", Description=" + Description + ", Affiche=" + Affiche + ", Etat=" + Etat + ", Prix=" + Prix + '}';
    }
    
    
    
    

    
    
}

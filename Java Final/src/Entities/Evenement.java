/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mechlaoui
 */
public class Evenement extends RecursiveTreeObject<Evenement> {
    StringProperty nomProperty;

    public StringProperty getNomProperty() {
        return nomProperty;
    }

    public void setNomProperty(String nomProperty) {
        this.nomProperty.set(nomProperty);
    }

    public StringProperty getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(String typeProperty) {
        this.typeProperty.set(typeProperty);
    }

    public StringProperty getType_ResProperty() {
        return Type_ResProperty;
    }

    public void setType_ResProperty(String Type_ResProperty) {
        this.Type_ResProperty.set(Type_ResProperty);
    }

    public StringProperty getEtatProperty() {
        return EtatProperty;
    }

    public void setEtatProperty(String Etat) {
        this.EtatProperty.set(Etat);
    }
    StringProperty typeProperty;
    StringProperty Type_ResProperty;
    StringProperty EtatProperty;
    
    
    
    private int ID;
    private int ID_Organisateur;
    private String Nom;


    public enum TypeEvent {
        Culturel,
        Associatif,
        Autres;
    }
    private TypeEvent Type;
    public enum Type_Reservation {
        Payante,
        Gratuite;
    }
    private Type_Reservation Type_Reservation;
    private LocalDateTime Date_Event;
    private String Duree;
    private String Lieu;
    private int Nombre;
    private String Description;
    private String Affiche;
    private int Prix;
    private LocalTime heure;

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    private LocalDate date;

    
    public enum EtatEvent {
        Oui,
        Non,
        Archivé,
    }
    private EtatEvent Etat;
    
    public Evenement(){
        
    }
    
    public Evenement(int id){
        this.ID = id;
        
    }

    public Evenement(String nomProperty, String typeProperty, String Type_ResProperty, String EtatProperty) {
        this.nomProperty = new SimpleStringProperty(nomProperty);
        this.typeProperty = new SimpleStringProperty(typeProperty);
        this.Type_ResProperty = new SimpleStringProperty(Type_ResProperty);
        this.EtatProperty = new SimpleStringProperty(EtatProperty);
    }
    
    
    
    public Evenement(int ID, int ID_Organisateur, String Nom, TypeEvent Type, Type_Reservation Type_Reservation, LocalDateTime Date_Event, String Duree, String Lieu, int Nombre, String Description, String Affiche, EtatEvent Etat) {
        this.ID = ID;
        this.ID_Organisateur = ID_Organisateur;
        this.Nom = Nom;
        this.Type = Type;
        this.Type_Reservation = Type_Reservation;
        this.Date_Event = Date_Event;
        this.Duree = Duree;
        this.Lieu = Lieu;
        this.Nombre = Nombre;
        this.Description = Description;
        this.Affiche = Affiche;
        this.Etat = Etat;

    }
    
    public Evenement(int ID_Organisateur, String Nom, TypeEvent Type, Type_Reservation Type_Reservation, LocalDateTime Date_Event, String Duree, String Lieu, int Nombre, String Description, String Affiche, EtatEvent Etat) {
        this.ID = ID;
        this.ID_Organisateur = ID_Organisateur;
        this.Nom = Nom;
        this.Type = Type;
        this.Type_Reservation = Type_Reservation;
        this.Date_Event = Date_Event;
        this.Duree = Duree;
        this.Lieu = Lieu;
        this.Nombre = Nombre;
        this.Description = Description;
        this.Affiche = Affiche;
        this.Etat = Etat;

    }

    public Evenement(int ID, int ID_Organisateur, String Nom, TypeEvent Type, Type_Reservation Type_Reservation, LocalDateTime Date_Event, String Duree, String Lieu, int Nombre, String Description, String Affiche, EtatEvent Etat,int Prix) {
        this.ID = ID;
        this.ID_Organisateur = ID_Organisateur;
        this.Nom = Nom;
        this.Type = Type;
        this.Type_Reservation = Type_Reservation;
        this.Date_Event = Date_Event;
        this.Duree = Duree;
        this.Lieu = Lieu;
        this.Nombre = Nombre;
        this.Description = Description;
        this.Affiche = Affiche;
        this.Etat = Etat;
        this.Prix = Prix;
    }

    public Evenement(int ID_Organisateur, String Nom, TypeEvent Type, Type_Reservation Type_Reservation, LocalDateTime Date_Event, String Duree, String Lieu, int Nombre, String Description, String Affiche, EtatEvent Etat,int prix) {
        this.ID_Organisateur = ID_Organisateur;
        this.Nom = Nom;
        this.Type = Type;
        this.Type_Reservation = Type_Reservation;
        this.Date_Event = Date_Event;
        this.Duree = Duree;
        this.Lieu = Lieu;
        this.Nombre = Nombre;
        this.Description = Description;
        this.Affiche = Affiche;
        this.Etat = Etat;
        this.Prix = prix;
    }
    
    public void setHeure(){
        
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public int getID_Organisateur() {
        return ID_Organisateur;
    }

    public void setID_Organisateur(int ID_Organisateur) {
        this.ID_Organisateur = ID_Organisateur;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public TypeEvent getType() {
        return Type;
    }

    public void setType(TypeEvent Type) {
        this.Type = Type;
    }

    public Type_Reservation getType_Reservation() {
        return Type_Reservation;
    }

    public void setType_Reservation(Type_Reservation Type_Reservation) {
        this.Type_Reservation = Type_Reservation;
    }

    public LocalDateTime getDate_Event() {
        return Date_Event;
    }

    public void setDate_Event(LocalDateTime Date_Event) {
        this.Date_Event = Date_Event;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String Duree) {
        this.Duree = Duree;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public int getNombre() {
        return Nombre;
    }

    public void setNombre(int Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getAffiche() {
        return Affiche;
    }

    public void setAffiche(String Affiche) {
        this.Affiche = Affiche;
    }
    
    public EtatEvent getEtat() {
        return Etat;
    }

    public void setEtat(EtatEvent Etat) {
        this.Etat = Etat;
    }
    
    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.ID;
        hash = 83 * hash + this.ID_Organisateur;
        hash = 83 * hash + Objects.hashCode(this.Nom);
        hash = 83 * hash + Objects.hashCode(this.Type);
        hash = 83 * hash + Objects.hashCode(this.Type_Reservation);
        hash = 83 * hash + Objects.hashCode(this.Date_Event);
        hash = 83 * hash + Objects.hashCode(this.Duree);
        hash = 83 * hash + Objects.hashCode(this.Lieu);
        hash = 83 * hash + this.Nombre;
        hash = 83 * hash + Objects.hashCode(this.Description);
        hash = 83 * hash + Objects.hashCode(this.Affiche);
        hash = 83 * hash + Objects.hashCode(this.Etat);
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
        final Evenement other = (Evenement) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.ID_Organisateur != other.ID_Organisateur) {
            return false;
        }
        if (!Objects.equals(this.Nom, other.Nom)) {
            return false;
        }
        if (this.Type != other.Type) {
            return false;
        }
        if (this.Type_Reservation != other.Type_Reservation) {
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
        if (this.Nombre != other.Nombre) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.Affiche, other.Affiche)) {
            return false;
        }
        if (this.Etat != other.Etat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "ID=" + ID + ", ID_Organisateur=" + ID_Organisateur + ", Nom=" + Nom + ", Type=" + Type.name() + ", Type_Reservation=" + Type_Reservation.name() + ", Date_Event=" + Date_Event + ", Duree=" + Duree + ", Lieu=" + Lieu + ", Nombre=" + Nombre + ", Description=" + Description + ", Affiche=" + Affiche + ", Etat=" + Etat.name() + "Prix" + Prix+'}';
    }

    
    

}

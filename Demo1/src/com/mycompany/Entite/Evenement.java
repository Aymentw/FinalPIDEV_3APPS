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
public class Evenement {
    
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
    private Date Date_Event;
    private String Duree;
    private String Lieu;
    private int Nombre;
    private String Description;
    private String Affiche;
    private int Prix;
    private int Disponible;
   


    
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
    
    
    public Evenement(int ID, int ID_Organisateur, String Nom, TypeEvent Type, Type_Reservation Type_Reservation, Date Date_Event, String Duree, String Lieu, int Nombre, String Description, String Affiche, EtatEvent Etat, int dispo) {
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
        this.Disponible = dispo;

    }
    
    public Evenement(int ID_Organisateur, String Nom, TypeEvent Type, Type_Reservation Type_Reservation, Date Date_Event, String Duree, String Lieu, int Nombre, String Description, String Affiche, EtatEvent Etat) {
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

    public Evenement(int ID, int ID_Organisateur, String Nom, TypeEvent Type, Type_Reservation Type_Reservation, Date Date_Event, String Duree, String Lieu, int Nombre, String Description, String Affiche, EtatEvent Etat,int Prix,int dispo) {
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
        this.Disponible = dispo;
    }

    public Evenement(int ID_Organisateur, String Nom, TypeEvent Type, Type_Reservation Type_Reservation, Date Date_Event, String Duree, String Lieu, int Nombre, String Description, String Affiche, EtatEvent Etat,int prix) {
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

    public Date getDate_Event() {
        return Date_Event;
    }

    public void setDate_Event(Date Date_Event) {
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

    public int getDisponible() {
        return Disponible;
    }

    public void setDisponible(int Disponible) {
        this.Disponible = Disponible;
    }
    
    


    @Override
    public String toString() {
        return "Evenement{" + "ID=" + ID + ", ID_Organisateur=" + ID_Organisateur + ", Nom=" + Nom + ", Type=" + Type.name() + ", Type_Reservation=" + Type_Reservation.name() + ", Date_Event=" + Date_Event + ", Duree=" + Duree + ", Lieu=" + Lieu + ", Nombre=" + Nombre + ", Description=" + Description + ", Affiche=" + Affiche + ", Etat=" + Etat.name() + ",Prix=" + Prix+'}';
    }

    
    

}

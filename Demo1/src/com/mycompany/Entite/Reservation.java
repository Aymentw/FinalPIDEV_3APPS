/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;



/**
 *
 * @author Mechlaoui
 */
public class Reservation {
    private int ID_Reservation;
    private Evenement Event;
    private int ID_Participant;
    private int Numero_Ticket;
    public enum Type_Reservation {
        Payante,
        Gratuite;
    }
    public double Tarif;
    private Type_Reservation Type_Reservation;
    public enum Etat_Reservation {
        Confirmé,
        Annulé;
    }
    private Etat_Reservation Etat;
    
    public Reservation(){
        
    }

    public Reservation(int ID_Reservation, Evenement Event, int ID_Participant, Type_Reservation Type_Reservation, Etat_Reservation Etat) {
        this.ID_Reservation = ID_Reservation;
        this.Event = Event;
        this.ID_Participant = ID_Participant;
        this.Type_Reservation = Type_Reservation;
        this.Etat = Etat;
    }

    public Reservation(Evenement Event, int ID_Participant, Type_Reservation Type_Reservation, Etat_Reservation Etat) {
        this.Event = Event;
        this.ID_Participant = ID_Participant;
        this.Type_Reservation = Type_Reservation;
        this.Etat = Etat;
    }
    
    

    public Reservation(int ID_Reservation, Evenement Event, int ID_Participant, Type_Reservation Type_Reservation,
                       double Tarif,int Numero_Ticket,Etat_Reservation Etat) {
        this.ID_Reservation = ID_Reservation;
        this.Event= Event;
        this.ID_Participant = ID_Participant;
        this.Type_Reservation = Type_Reservation;
        this.Tarif = Tarif;
        this.Numero_Ticket = Numero_Ticket;
        this.Etat = Etat;
    }

    public Reservation(Evenement Event, int ID_Participant, Type_Reservation Type_Reservation,
                       double Tarif,int Numero_Ticket,Etat_Reservation Etat) {  
        this.Event = Event;
        this.ID_Participant = ID_Participant;
        this.Type_Reservation = Type_Reservation;
        this.Tarif = Tarif;
        this.Numero_Ticket = Numero_Ticket;
        this.Etat = Etat;
    }

    public int getID_Reservation() {
        return ID_Reservation;
    }

    public void setID_Reservation(int ID_Reservation) {
        this.ID_Reservation = ID_Reservation;
    }

    public Evenement getEvent() {
        return Event;
    }

    public void setEvent(Evenement Event) {
        this.Event = Event;
    }

    public int getID_Participant() {
        return ID_Participant;
    }

    public void setID_Participant(int ID_Participant) {
        this.ID_Participant = ID_Participant;
    }

    public int getNumero_Ticket() {
        return Numero_Ticket;
    }

    public void setNumero_Ticket(int Numero_Ticket) {
        this.Numero_Ticket = Numero_Ticket;
    }

    public Type_Reservation getType_Reservation() {
        return Type_Reservation;
    }

    public void setType_Reservation(Type_Reservation Type_Reservation) {
        this.Type_Reservation = Type_Reservation;
    }

    public double getTarif() {
        return Tarif;
    }

    public void setTarif(double Tarif) {
        this.Tarif = Tarif;
    }
    
    public Etat_Reservation getEtat() {
        return Etat;
    }

    public void setEtat(Etat_Reservation Etat) {
        this.Etat = Etat;
    }

    @Override
    public String toString() {
        return "Reservation{" + "ID_Reservation=" + ID_Reservation + ", Evenement=" + this.Event.getNom()
                +" Date_Evenement=" + this.Event.getDate_Event()
                + ", ID_Participant=" + ID_Participant + ", Numero_Ticket=" + Numero_Ticket + 
                ", Tarif=" + Tarif + ", Type_Reservation=" + Type_Reservation + ", Etat=" + Etat + '}';
    }

   
  
    
    
}

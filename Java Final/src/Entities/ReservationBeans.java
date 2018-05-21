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
public class ReservationBeans extends RecursiveTreeObject<ReservationBeans>{
    
    
     public StringProperty ID_Reservation;
     public EventBeans Event;
     public StringProperty ID_Participant;
     public StringProperty Type_Reservation;
     public StringProperty Numero_Ticket;    
     public StringProperty Tarif;
     public StringProperty Etat;

    public StringProperty getID_Reservation() {
        return ID_Reservation;
    }

    public void setID_Reservation(String ID_Reservation) {
        this.ID_Reservation.set(ID_Reservation);
    }

    public EventBeans getEvent() {
        return Event;
    }

    public void setEvent(EventBeans Event) {
        this.Event = Event;
    }

    public StringProperty getID_Participant() {
        return ID_Participant;
    }

    public void setID_Participant(String ID_Participant) {
        this.ID_Participant.set(ID_Participant);
    }

    public StringProperty getNumero_Ticket() {
        return Numero_Ticket;
    }

    public void setNumero_Ticket(String Numero_Ticket) {
        this.Numero_Ticket.set(Numero_Ticket);
    }

    public StringProperty getType_Reservation() {
        return Type_Reservation;
    }

    public void setType_Reservation(String Type_Reservation) {
        this.Type_Reservation.set(Type_Reservation);
    }

    public StringProperty getTarif() {
        return Tarif;
    }

    public void setTarif(String Tarif) {
        this.Tarif.set(Tarif);
    }

    public StringProperty getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat.set(Etat);
    }

    public ReservationBeans(String ID_Reservation, EventBeans Event, String ID_Participant, String Type_Reservation, String Etat) {
        this.ID_Reservation = new SimpleStringProperty(ID_Reservation);
        this.Event = Event;
        this.ID_Participant = new SimpleStringProperty(ID_Participant);
        this.Type_Reservation = new SimpleStringProperty(Type_Reservation);
        this.Etat = new SimpleStringProperty(Etat);
    }

    public ReservationBeans(EventBeans Event, String ID_Participant, String Type_Reservation, String Etat) {
        this.Event = Event;
        this.ID_Participant = new SimpleStringProperty(ID_Participant);
        this.Type_Reservation = new SimpleStringProperty(Type_Reservation);
        this.Etat = new SimpleStringProperty(Etat);
    }

    public ReservationBeans(String ID_Reservation, EventBeans Event, String ID_Participant, String Type_Reservation, String Numero_Ticket, String Tarif, String Etat) {
        this.ID_Reservation =new SimpleStringProperty(ID_Reservation);
        this.Event = Event;
        this.ID_Participant = new SimpleStringProperty(ID_Participant);
        this.Type_Reservation = new SimpleStringProperty(Type_Reservation);
        this.Numero_Ticket = new SimpleStringProperty(Numero_Ticket);
        this.Tarif = new SimpleStringProperty(Tarif);
        this.Etat = new SimpleStringProperty(Etat);
    }

    public ReservationBeans(EventBeans Event, String ID_Participant, String Type_Reservation, String Numero_Ticket, String Tarif, String Etat) {
        this.Event = Event;
        this.ID_Participant = new SimpleStringProperty(ID_Participant);
        this.Type_Reservation = new SimpleStringProperty(Type_Reservation);
        this.Numero_Ticket = new SimpleStringProperty(Numero_Ticket);
        this.Tarif = new SimpleStringProperty(Tarif);
        this.Etat = new SimpleStringProperty(Etat);
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.ID_Reservation);
        hash = 79 * hash + Objects.hashCode(this.Event);
        hash = 79 * hash + Objects.hashCode(this.ID_Participant);
        hash = 79 * hash + Objects.hashCode(this.Numero_Ticket);
        hash = 79 * hash + Objects.hashCode(this.Type_Reservation);
        hash = 79 * hash + Objects.hashCode(this.Tarif);
        hash = 79 * hash + Objects.hashCode(this.Etat);
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
        final ReservationBeans other = (ReservationBeans) obj;
        if (!Objects.equals(this.ID_Reservation, other.ID_Reservation)) {
            return false;
        }
        if (!Objects.equals(this.Event, other.Event)) {
            return false;
        }
        if (!Objects.equals(this.ID_Participant, other.ID_Participant)) {
            return false;
        }
        if (!Objects.equals(this.Numero_Ticket, other.Numero_Ticket)) {
            return false;
        }
        if (!Objects.equals(this.Type_Reservation, other.Type_Reservation)) {
            return false;
        }
        if (!Objects.equals(this.Tarif, other.Tarif)) {
            return false;
        }
        if (!Objects.equals(this.Etat, other.Etat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReservationBeans{" + "ID_Reservation=" + ID_Reservation + ", Event=" + Event + ", ID_Participant=" + ID_Participant + ", Numero_Ticket=" + Numero_Ticket + ", Type_Reservation=" + Type_Reservation + ", Tarif=" + Tarif + ", Etat=" + Etat + '}';
    }
    
    
    
    
    
     
    
}

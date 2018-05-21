package Entities;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.Commentaire;
import Entities.Evenement;
import java.util.Objects;

/**
 *
 * @author Mechlaoui
 */
public class Evaluation implements Comparable<Evaluation>{
    
    public static final int MAX_VALUE = 100;
    public static final int MIN_VALUE= 0;
    private int ID_Evaluation;
    private Evenement Event;
    private int ID_User;
    private int Note;
    
    public Evaluation(Evenement Event, int ID_User,int Note) {
        this.Event = Event;
        this.ID_User = ID_User;
        this.Note = Note;
    }

    public Evaluation(int ID_Evaluation, Evenement Event, int ID_User,int Note) {
        this.ID_Evaluation = ID_Evaluation;
        this.Event = Event;
        this.ID_User = ID_User;
        this.Note = Note;
    }

    public int getID_Evaluation() {
        return ID_Evaluation;
    }

    public void setID_Evaluation(int ID_Evaluation) {
        this.ID_Evaluation = ID_Evaluation;
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
    
        public int getNote() {
        return Note;
    }

    public void setNote(int Note) {
        if (Note < MAX_VALUE && Note > MIN_VALUE)
        this.Note = Note;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.ID_Evaluation;
        hash = 89 * hash + Objects.hashCode(this.Event);
        hash = 89 * hash + this.ID_User;
        hash = 89 * hash + this.Note;
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
        final Evaluation other = (Evaluation) obj;
        if (this.ID_Evaluation != other.ID_Evaluation) {
            return false;
        }
        if (!Objects.equals(this.Event, other.Event.getID())) {
            return false;
        }
        if (this.ID_User != other.ID_User) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "ID_Evaluation=" + ID_Evaluation + ", Event=" + Event + ", ID_User=" + ID_User + ", Note=" + Note + '}';
    }

    

    @Override
    public int compareTo(Evaluation o) {
        return this.getNote() - o.getNote();
    }
    
    
    


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Mechlaoui
 */
public class Activite {
    private int ID;
    private String Nom;
    public enum Discipline {
        Football,
        Handball,
        Basketball,
        Volleyball;
    }
    private Discipline Discipline;
    private LocalDateTime Date_Event;
    private String Duree;
    private String Lieu;
    private String Description;
    private String Affiche;

    public Activite(int ID, String Nom, Discipline Discipline, LocalDateTime Date_Event,
                    String Duree, String Lieu, String Description, String Affiche)
    {
        this.ID = ID;
        this.Nom = Nom;
        this.Discipline = Discipline;
        this.Date_Event = Date_Event;
        this.Duree = Duree;
        this.Lieu = Lieu;
        this.Description = Description;
        this.Affiche = Affiche;
    }
    
    public Activite(String Nom, Discipline Discipline, LocalDateTime Date_Event, String Duree,
                    String Lieu, String Description, String Affiche)
    {
        this.Nom = Nom;
        this.Discipline = Discipline;
        this.Date_Event = Date_Event;
        this.Duree = Duree;
        this.Lieu = Lieu;
        this.Description = Description;
        this.Affiche = Affiche;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public Discipline getDiscipline() {
        return Discipline;
    }

    public void setDiscipline(Discipline Discipline) {
        this.Discipline = Discipline;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.ID;
        hash = 59 * hash + Objects.hashCode(this.Discipline);
        hash = 59 * hash + Objects.hashCode(this.Date_Event);
        hash = 59 * hash + Objects.hashCode(this.Lieu);
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
        final Activite other = (Activite) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.Discipline != other.Discipline) {
            return false;
        }
        if (!Objects.equals(this.Date_Event, other.Date_Event)) {
            return false;
        }
        if (!Objects.equals(this.Lieu, other.Lieu)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Activite{" + "ID=" + ID + ", Nom=" + Nom + ", Discipline=" + Discipline + ", Date_Event=" + Date_Event + ", Duree=" + Duree + ", Lieu=" + Lieu + ", Description=" + Description + ", Affiche=" + Affiche + '}';
    }
    
    
}

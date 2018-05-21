/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author Manai
 */
public class Document {
    
    private int id, id_user;
    private String titre, path, description, niveau, matiere, etat;

    public Document() {
    }

    public Document(String titre, String path, String description, String niveau, String matiere, String etat) {
        this.id = id;
        this.titre = titre;
        this.path = path;
        this.description = description;
        this.niveau = niveau;
        this.matiere = matiere;
        this.etat = etat;
    }
    
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Document(int id, String titre, String description, String path, String niveau, String matiere, int id_user) {
        this.id = id;
        this.titre = titre;
        this.path = path;
        this.description = description;
        this.niveau = niveau;
        this.matiere = matiere;
        this.id_user = id_user;
    }
    
    

    public Document(String titre, String description, String niveau, String matiere, String path) {
        this.titre = titre;
        this.path = path;
        this.description = description;
        this.niveau = niveau;
        this.matiere = matiere;
    }

    public Document(int id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    
    
    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public Document(String titre, String desciption) {
        this.titre = titre;
        this.description = desciption;
    }

    public Document(String titre, String path, String description, String niveau, String matiere, String etat, int id_user) {
        this.titre = titre;
        this.path = path;
        this.description = description;
        this.niveau = niveau;
        this.matiere = matiere;
        this.etat = etat;
        this.id_user = id_user;
    }
    
     public Document(String titre, String niveau, String matiere) {
        this.titre = titre;
        this.niveau = niveau;
        this.matiere = matiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}

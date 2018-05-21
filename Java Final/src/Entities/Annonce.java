/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Aymen
 */
public class Annonce extends RecursiveTreeObject<Annonce>{
    
    public int id;
    public String libelle;
    public String description;
    public String path;
    public int id_user;
    private ImageView img;
    //img type img
    //lis annonce

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
         img.setFitHeight(150);
img.setFitWidth(150);
img.setPreserveRatio(false);
    }

    public Annonce(int id, int id_user, String libelle, String description, String path) {
        this.id = id;
        this.id_user = id_user;
        this.libelle = libelle;
        this.description = description;
        this.path = path;
    }
    public static List<Annonce> generateImageViews(List<Annonce> annonces) {
        List<Annonce> liste = new ArrayList<Annonce>();

        for (Annonce annonce : annonces) {
            File f = new File("C:\\wamp64\\www\\ressources\\" + annonce.getPath());
            annonce.setImg(new ImageView(new Image(f.toURI().toString())));
            liste.add(annonce);
        }
        return liste;
    }
    

    public Annonce(int id, String libelle, String description, String path, int id_user) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.path = path;
        this.id_user = id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Annonce(int id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }

    public Annonce(int id, String libelle, String description, String path) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.path = path;
    }

    public Annonce(String libelle, String description, String path, int id_user) {
        this.libelle = libelle;
        this.description = description;
        this.path = path;
        this.id_user = id_user;
    }

 
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
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
        final Annonce other = (Annonce) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Annonce(String libelle) {
        this.libelle = libelle;
    }

    public Annonce(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Annonce(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Annonce(String libelle, String description, String path) {
        this.libelle = libelle;
        this.description = description;
        this.path = path;
    }
    
    
    
}

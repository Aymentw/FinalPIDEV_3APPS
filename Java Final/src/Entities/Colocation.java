/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Karim
 */
public class Colocation {

    private int id, id_user;
    private String type;
    private String adresse;
    private String sexe;
    private float prix;
    private int place_dispo;

    private String type_maison;
    private String description;
    private String path;
    private ImageView star1;
    private ImageView star0;

    public ImageView getStar0() {
        return star0;
    }

    public void setStar0(ImageView star0) {
        this.star0 = star0;
    }
    
    

    public ImageView getStar1() {
        
        return star1;
    }

    public void setStar1(ImageView star1) {
        this.star1 = star1;
    }

    public Colocation(int id, String type, String adresse, String sexe, float prix, int place_dispo, String type_maison, String description, String path) {
        this.id = id;
        this.type = type;
        this.adresse = adresse;
        this.sexe = sexe;
        this.prix = prix;
        this.place_dispo = place_dispo;
        this.type_maison = type_maison;
        this.description = description;
        this.path = path;

    }

    public Colocation(String type, String adresse, String sexe, float prix, int place_dispo, String type_maison, String description, String path, int id_user) {

        this.type = type;
        this.adresse = adresse;
        this.sexe = sexe;
        this.prix = prix;
        this.place_dispo = place_dispo;
        this.type_maison = type_maison;
        this.description = description;
        this.path = path;
        this.id_user = id_user;

    }

    public Colocation(String type, String adresse, String sexe, float prix, int place_dispo, String type_maison, String description, String path) {
        this.type = type;
        this.adresse = adresse;
        this.sexe = sexe;
        this.prix = prix;
        this.place_dispo = place_dispo;
        this.type_maison = type_maison;
        this.description = description;
        this.path = path;

    }

    public Colocation(String adresse, float prix, int place_dispo, String type_maison, String description) {
        this.adresse = adresse;
        this.prix = prix;
        this.place_dispo = place_dispo;
        this.type_maison = type_maison;
        this.description = description;
    }

    public Colocation() {
    }

    public Colocation(String type, String adresse, String sexe, float prix, int place_dispo, String type_maison, String description) {
        this.sexe = sexe;
        this.type = type;
        this.adresse = adresse;
        this.prix = prix;
        this.place_dispo = place_dispo;
        this.type_maison = type_maison;
        this.description = description;

    }

    public Colocation(String adresse, String sexe, float prix, int place_dispo, String type_maison, String description, int id_user) {
        this.adresse = adresse;
        this.sexe = sexe;
        this.prix = prix;
        this.place_dispo = place_dispo;
        this.type_maison = type_maison;
        this.description = description;
        this.id_user = id_user;

    }

    public Colocation(String type, String sexe, int place_dispo, String type_maison, String description) {
        this.type = type;
        this.sexe = sexe;
        this.place_dispo = place_dispo;
        this.type_maison = type_maison;
        this.description = description;
    }

    public Colocation(String sexe, int place_dispo, String type_maison, String description, int id_user) {
        this.sexe = sexe;
        this.place_dispo = place_dispo;
        this.type_maison = type_maison;
        this.description = description;
        this.id_user = id_user;
    }

    public Colocation(int id, String adresse, String sexe, float prix, int place_dispo, String type_maison, String description, int id_user) {
        this.id = id;
        this.id_user = id_user;
        this.type = type;
        this.adresse = adresse;
        this.sexe = sexe;
        this.prix = prix;
        this.place_dispo = place_dispo;
        this.type_maison = type_maison;
        this.description = description;

    }

    public Colocation(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getPlace_dispo() {
        return place_dispo;
    }

    public void setPlace_dispo(int place_dispo) {
        this.place_dispo = place_dispo;
    }

    public String getType_maison() {
        return type_maison;
    }

    public void setType_maison(String type_maison) {
        this.type_maison = type_maison;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.type);
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
        final Colocation other = (Colocation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

}

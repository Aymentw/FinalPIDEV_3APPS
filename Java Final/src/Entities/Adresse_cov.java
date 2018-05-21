/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS-PC
 */
public class Adresse_cov {
    private int id;
    private String nom;
    private double lat,lng;

    public Adresse_cov() {
        this.id=0;
        this.lat=0;
        this.lng=0;
    }

    public Adresse_cov(String nom, double lat, double lng) {
        this.nom = nom;
        this.lat = lat;
        this.lng = lng;
        this.id=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    
}

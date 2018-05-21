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
public class Localisation {
 private int id_a,type;
    private double lat,lng;
 public Localisation(){
 this.lat=0;
 this.lng=0;
 id_a=0;
 };
 public Localisation(double lat,double lng){
     this.lat=lat;
     this.lng=lng;
    
 }
 public double getlat(){return this.lat;}
 public double getlng(){return this.lng;}
 public void setid_a(int id_a){
     this.id_a=id_a;
 }
 public int getid_a(){return this.id_a;}
  public int getType(){return this.type;}
 
}

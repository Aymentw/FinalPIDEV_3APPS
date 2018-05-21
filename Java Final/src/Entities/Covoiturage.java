/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.sql.Date;
import java.time.LocalDate;


/**
 *
 * @author ASUS-PC
 */
public class Covoiturage {
    private int id,id_u,etat,type;
     
    private String depart,destination,type_ann;
    private Date  date;
    private Boolean vue;
    public Covoiturage(){
    this.depart="";
    this.destination="";
    this.id=0;
    this.id_u=0;
    this.etat=0;
    this.type=0;
    this.date=Date.valueOf(LocalDate.now());
    this.vue=true;
    };
    public Covoiturage(int id,int id_u,int type,String depart,String destination,Date date,Boolean vue){
    this.depart=depart;
    this.destination=destination;
    this.id=id;
    this.id_u=id_u;
    this.etat=1;
    if(date.before(Date.valueOf(LocalDate.now()))){
        etat=0;
    }
    this.etat=etat;
    this.type=type;
    if(type==0) this.type_ann="Offre";
            else this.type_ann="Demande";
    this.date=date;
    this.vue=vue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType_ann() {
        return type_ann;
    }

    public void setType_ann(String type_ann) {
        this.type_ann = type_ann;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getVue() {
        return vue;
    }

    public void setVue(Boolean vue) {
        this.vue = vue;
    }
   
    @Override
    public String toString(){
        return depart+" "+destination;
    }
    
    
}

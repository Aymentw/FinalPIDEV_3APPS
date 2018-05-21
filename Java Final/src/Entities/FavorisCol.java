/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Karim
 */
public class FavorisCol {
    private int id_fav;
    private int id_colocation;
    private int id_user;
   
    private ImageView star1 =new ImageView(new Image("file:///C:/Users/Karim/Documents/NetBeansProjects/Colocation/src/GUI/star1.png",30,30, true, true));

    public FavorisCol(int id_fav, int id_colocation, int id_user) {
        this.id_fav = id_fav;
        this.id_colocation = id_colocation;
        this.id_user = id_user;
       
        star1 = new ImageView(new Image("file:///C:/Users/Karim/Documents/NetBeansProjects/Colocation/src/GUI/star1.png",30,30, true, true));
  
    }

   
    public ImageView getStar1() {
        return star1;
    }

    public void setStar1(ImageView star1) {
        this.star1 = star1;
    }
    
    
    

    public int getId_fav() {
        return id_fav;
    }

    public void setId_fav(int id_fav) {
        this.id_fav = id_fav;
    }

    public int getId_colocation() {
        return id_colocation;
    }

    public void setId_colocation(int id_colocation) {
        this.id_colocation = id_colocation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    
}

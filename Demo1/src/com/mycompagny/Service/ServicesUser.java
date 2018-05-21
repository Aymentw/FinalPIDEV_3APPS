/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.SessionManager;
import java.io.IOException;

/**
 *
 * @author Mechlaoui
 */
public class ServicesUser {
    
    public static void EditUser(String username, String password, String email, String niveau, String photo){
        
    String url = "http://127.0.0.1:8000/api/edit_user";
                MultipartRequest req = new MultipartRequest();
                
        try {
            req.setUrl(url);
                req.setPost(true);
                req.addArgument("id", String.valueOf(SessionManager.getId()));
                req.addArgument("username", username);
                req.addArgument("password", password);
                req.addArgument("email", email);
                System.out.println(email);
                req.addArgument("niveau", niveau);
            if(!photo.equals("")){    
            req.addData("photo", photo,"image/jpeg");
            req.setFilename("photo", username+".jpg");
            }
             req.addResponseListener((response)-> {
                        
                        byte[] data = (byte[]) response.getMetaData();
                        String s = new String(data);
                        System.out.println(s);                                                
                        if (s.equals("success")) {                            
                        } else {
                            Dialog.show("Erreur", "Echec de modification", "Ok", null);
                        }                    
                });

                NetworkManager.getInstance().addToQueueAndWait(req);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
                
                       
               
}
    
    public static Image UrlImage(String photo){
        String url ="http://127.0.0.1:8000/api/images_user?img="+photo;
                EncodedImage placeholder = EncodedImage.createFromImage(Resources.getGlobalResources().getImage("photo-profile.jpg"), false);
                Image urli = URLImage.createToStorage(placeholder,"Medium_"+url, url,URLImage.RESIZE_SCALE);
                
        return urli;        
    }
}
    



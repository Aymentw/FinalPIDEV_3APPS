/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Colocation;
import com.mycompany.gui.MesColocation;
import com.mycompany.gui.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Karim
 */
public class ServiceColocation {
     public void ajoutColocation(Colocation c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/piweb-master/web/app_dev.php/api/mobileAdd/" +c.getType()+ "/" +c.getAdresse()+ "/" +c.getSexe()+ "/" +c.getPrix()+ "/" +c.getPlace_dispo()+ "/" +c.getType_maison()+ "/" +c.getDescription()+ "/"+c.getPath()+ "/" +SessionManager.getId();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
              MesColocation a = new MesColocation(Resources.getGlobalResources());
            a.show();
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
    public boolean isAnumber(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Colocation> getList2() {
        ArrayList<Colocation> listcolocation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/api/afficheAnn");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> colocation = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(colocation);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) colocation.get("root");
                    for (Map<String, Object> obj : list) {
                        Colocation coloc = new Colocation();
//                        float id = Float.parseFloat(obj.get("id").toString());
                        float pr = Float.parseFloat(obj.get("prix").toString());

                        
//                        coloc.setId((int) id);
                        coloc.setType(obj.get("typeColocation").toString());
                        coloc.setAdresse(obj.get("adresse").toString());
                        coloc.setSexe(obj.get("sexe").toString());
                        coloc.setType_maison(obj.get("typeMaison").toString());
                        coloc.setDescription(obj.get("description").toString());
                        coloc.setPrix((float)pr);
                        coloc.setPlace_dispo((int)Float.parseFloat(obj.get("placeDispo").toString()));
                        coloc.setPath(obj.get("path").toString());
                        
                        listcolocation.add(coloc);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcolocation;
    }

     public ArrayList<Colocation> ChercherColocation(String c) {
        ArrayList<Colocation> listColoc = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symphonie/web/app_dev.php/Evenement/chercher/" + c);
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    Colocation coloc = new Colocation();
                    float pr = Float.parseFloat(obj.get("prix").toString());

                        
//                        coloc.setId((int) id);
                        coloc.setType(obj.get("typeColocation").toString());
                        coloc.setAdresse(obj.get("adresse").toString());
                        coloc.setSexe(obj.get("sexe").toString());
                        coloc.setType_maison(obj.get("typeMaison").toString());
                        coloc.setDescription(obj.get("description").toString());
                        coloc.setPrix((float)pr);
                        coloc.setPlace_dispo((int)Float.parseFloat(obj.get("placeDispo").toString()));
                        coloc.setPath(obj.get("path").toString());
//                  task.setDate((Date) obj.get("date"));
                    listColoc.add(coloc);
                }
            } catch (IOException ex) {
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listColoc;
    }
     public ArrayList<Colocation> getList() {
        ArrayList<Colocation> listcolocation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/api/mescoloc/"+SessionManager.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> colocation = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(colocation);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) colocation.get("root");
                    for (Map<String, Object> obj : list) {
                        Colocation coloc = new Colocation();
                       float id = Float.parseFloat(obj.get("id").toString());
                        float pr = Float.parseFloat(obj.get("prix").toString());

                        
                        coloc.setId((int) id);
                        coloc.setType(obj.get("typeColocation").toString());
                        coloc.setAdresse(obj.get("adresse").toString());
                        coloc.setSexe(obj.get("sexe").toString());
                        coloc.setType_maison(obj.get("typeMaison").toString());
                        coloc.setDescription(obj.get("description").toString());
                        coloc.setPrix((float)pr);
                        coloc.setPlace_dispo((int)Float.parseFloat(obj.get("placeDispo").toString()));
                        coloc.setPath(obj.get("path").toString());
                        
                        listcolocation.add(coloc);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcolocation;
        
        
    }
     public void supprimerColoction(Colocation e) {
        ConnectionRequest con = new ConnectionRequest();
         System.out.println(e.getId());
        String Url = "http://localhost/piweb-master/web/app_dev.php/api/deleteColMob/" + e.getId();
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Colocation supprimée", "ok", null);

            MesColocation a = new MesColocation(Resources.getGlobalResources());
            a.show();

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
     
       public void modifierColocation(Colocation c) {
        ConnectionRequest con = new ConnectionRequest();
//        String Url = "http://localhost/piweb/piweb/web/app_dev.php/colocation/modifmob/" 
//                +c.getId()+ 
//                "/" +c.getType()+ 
//                "/" +c.getAdresse()+ 
//                "/" +c.getSexe()+
//                "/" +c.getPrix()+ 
//                "/" +c.getPlace_dispo()+
//                "/" +c.getType_maison()+
//                "/" +c.getDescription()+
//                "/"+c.getPath();
        String Url = "http://localhost/piweb-master/web/app_dev.php/api/modifmob"; 
//                +c.getId()+ 
//                "/" +c.getType()+ 
//                "/" +c.getAdresse()+ 
//                "/" +c.getSexe()+
//                "/" +c.getPrix()+ 
//                "/" +c.getPlace_dispo()+
//                "/" +c.getType_maison()+
//                "/" +c.getDescription()+
//                "/"+c.getPath();
        con.setUrl(Url);
        con.setPost(false);
          con.addArgument("id", String.valueOf(c.getId()));
        con.addArgument("type", c.getType());
        con.addArgument("adr", c.getAdresse());
        con.addArgument("sexe", c.getSexe());
        con.addArgument("prix",String.valueOf(c.getPrix()));
        con.addArgument("place", String.valueOf(c.getPlace_dispo()));
        con.addArgument("typeM", c.getType_maison());
          con.addArgument("desc", c.getDescription());
            con.addArgument("path", c.getPath());

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            MesColocation a = new MesColocation(Resources.getGlobalResources());
            a.show();
            
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
     
}

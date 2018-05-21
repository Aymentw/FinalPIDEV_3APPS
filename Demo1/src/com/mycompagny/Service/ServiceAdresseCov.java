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
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Adresse_cov;
import com.mycompany.Entite.Covoiturage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author selim
 */
public class ServiceAdresseCov {
     public void ajoutTask(Adresse_cov ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AjoutAdrCov?nom=" + ta.getNom()+ "&lat=" + ta.getLat()+"&lng="+ta.getLng();
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void updateTask(Adresse_cov ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/updateAdrCov?nom=" + ta.getNom()+"&id="+ta.getId();
          System.out.println(Url);
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

  public Adresse_cov getList2(String nom)  {
        ArrayList<Adresse_cov> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/covoiturage/getAdrByName/aaazzaaa");
         Adresse_cov task = new Adresse_cov();
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                
                     
                      
                    for (Map<String, Object> obj : list) {
                      
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        task.setId((int) id);
                        task.setNom(obj.get("nom").toString());
                        task.setLat(Double.parseDouble(obj.get("lat").toString()));
                        task.setLng(Double.parseDouble(obj.get("lng").toString()));
                    if(task.getNom().equals(nom))
                        break;
                   
                    }
                } catch (IOException ex) {
                } 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
       return task;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.mycompany.Entite.Covoiturage;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Adresse_cov;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author selim
 */
public class ServiceCovoiturage {
      public void ajoutTask(Covoiturage ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/AjoutCov?depart=" + ta.getDepart()+ "&destination=" + ta.getDestination()+"&date="+ta.getDate()+"&type="+ta.getType()+"&idu="+ta.getIduser();
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void updateTask(Covoiturage ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/updateCov?depart=" + ta.getDepart()+ "&destination=" + ta.getDestination()+"&date="+ta.getDate()+"&type="+ta.getType()+"&id="+ta.getId();
          System.out.println(Url);
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    

    public ArrayList<Covoiturage> getList2()  {
        ArrayList<Covoiturage> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/api/Getlist");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Covoiturage task = new Covoiturage();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        task.setId((int) id);
                        task.setDepart(obj.get("depart").toString());
                        task.setDestination(obj.get("destination").toString());
                        task.setType((int) Float.parseFloat(obj.get("type").toString()));
                  
                                            
                  String DateS = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+10,obj.get("date").toString().lastIndexOf("}"));
                   
                  Date currentTime = new Date(Double.valueOf(DateS).longValue()*1000);
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String dateString = formatter.format(currentTime);
                        task.setDate(dateString);
                    listTasks.add(task);

                    }
                } catch (IOException ex) {
                } 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
     public ArrayList<Covoiturage> getList3(String input)  {
        ArrayList<Covoiturage> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/api/cherchermob/"+input);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Covoiturage task = new Covoiturage();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        task.setId((int) id);
                        task.setDepart(obj.get("depart").toString());
                        task.setDestination(obj.get("destination").toString());
                        task.setType((int) Float.parseFloat(obj.get("type").toString()));
                  
                                            
                  String DateS = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+10,obj.get("date").toString().lastIndexOf("}"));
                   
                  Date currentTime = new Date(Double.valueOf(DateS).longValue()*1000);
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String dateString = formatter.format(currentTime);
                        task.setDate(dateString);
                    listTasks.add(task);

                    }
                } catch (IOException ex) {
                } 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
}

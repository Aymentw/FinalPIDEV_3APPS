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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Topic;
import com.mycompany.gui.MesTopics;
import com.mycompany.gui.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author sana
 */
public class ServiceTopic {

    public void ajoutTask(Topic ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/piweb-master/web/app_dev.php/api/mobileAdd/" + ta.getSujet() + "/" + ta.getDescription() + "/" + ta.getImage_name() + "/" +SessionManager.getId();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
             Dialog.show("Succés", "Topic ajouté", "ok", null);

            MesTopics a = new MesTopics(Resources.getGlobalResources());
            a.show();
            a.refreshTheme();
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
   

    public ArrayList<Topic> getList2() {
        ArrayList<Topic> listTopics = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/api/yop");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> topics = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(topics);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) topics.get("root");
                    for (Map<String, Object> obj : list) {
                        Topic task = new Topic();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        task.setId((int) id);
                        task.setSujet(obj.get("sujet").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setImage_name(obj.get("imageName").toString());
                        String DateS = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().indexOf("timestamp") + 21);
                        System.out.println(DateS);
                        Date currentTime = new Date(Double.valueOf(DateS).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        listTopics.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTopics;
    }
     public String getUsername(int idT) {
        ArrayList<String> listTopics = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/api/usernameMob/"+idT);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> topics = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(topics);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) topics.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        listTopics.add(obj.get("username").toString());
                       

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTopics.get(0);
    }
     
          public String getDate(int idT) {
        ArrayList<String> listTopics = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/api/yop");
        String date;
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> topics = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(topics);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) topics.get("root");
                    for (Map<String, Object> obj : list) {
                        String DateS=obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().indexOf("timestamp") + 23);
                        System.out.println(DateS);
                        Date currentTime = new Date(Double.valueOf(DateS).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        float id = Float.parseFloat(obj.get("id").toString());
                        if((int)id==idT){
                        listTopics.add(dateString);                       
                        break;
                        }
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTopics.get(0);
    }
    public ArrayList<Topic> mesTopics() {
        ArrayList<Topic> listTopics = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/api/mesTopicsMobile/"+SessionManager.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> topics = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(topics);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) topics.get("root");
                    for (Map<String, Object> obj : list) {
                        Topic task = new Topic();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        task.setId((int) id);
                        task.setSujet(obj.get("sujet").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setImage_name(obj.get("imageName").toString());
                        String DateS = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().indexOf("timestamp") + 21);
                        Date currentTime = new Date(Double.valueOf(DateS).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        listTopics.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTopics;
    }
    
    public void supprimerTopic(Topic e) {
        ConnectionRequest con = new ConnectionRequest();
         System.out.println(e.getId());
        String Url = "http://localhost/piweb-master/web/app_dev.php/api/deleteTopMob/" + e.getId();
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Topic supprimé", "ok", null);

            MesTopics a = new MesTopics(Resources.getGlobalResources());
            a.show();

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
    
   public ArrayList<Topic> ChercherTopic(String d) {
        ArrayList<Topic> listTopic = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb/web/app_dev.php/mobileSearch/" + d);
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    Topic task = new Topic();
                    float id = Float.parseFloat(obj.get("id").toString());
                    task.setId((int) id);
                    task.setSujet(obj.get("sujet").toString());
                    task.setDescription(obj.get("description").toString());
                    task.setImage_name(obj.get("imageName").toString());
                  /*      String DateS = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().indexOf("timestamp") + 21);
                        Date currentTime = new Date(Double.valueOf(DateS).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);*/
                    listTopic.add(task);
                }
            } catch (IOException ex) {
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTopic;
    }
    
    
            public static Topic EventByID(int id){
        String id_string = String.valueOf(id);
        Map<String, Object> top;
        Topic topic = new Topic();
        String url = "http://localhost/piweb-master/web/app_dev.php/api/mobileDetails/"+topic.getId();
        ConnectionRequest request = new ConnectionRequest(url,false);
        request.addArgument("id", id_string);
        NetworkManager.getInstance().addToQueueAndWait(request);
        
                JSONParser j = new JSONParser();
                String json = new String(request.getResponseData()) + "";
                if (!json.equals("no data")){                            
            try {
                top = j.parseJSON(new CharArrayReader(json.toCharArray()));
                                    
                Map<String, Object> user = (Map<String, Object>)top.get("idUser");
            
                                                              
                                    topic = new Topic((int)Float.parseFloat(top.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),top.get("sujet").toString(),
                                            top.get("description").toString(),
                                            top.get("imageName").toString()
                                            
                                    );
                                    
                                    
                                  
                                    
                            } catch (IOException ex) {
                ex.printStackTrace();
            }
                        
                                       
    }
    return topic;
    }
    

}

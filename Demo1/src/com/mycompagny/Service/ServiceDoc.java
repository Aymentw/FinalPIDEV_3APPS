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
import com.mycompany.Entite.Document;
import com.mycompany.gui.AffichageLDocForm;
import com.mycompany.gui.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Manai
 */
public class ServiceDoc {

    public void ajoutDoc(Document d) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/piweb-master/web/app_dev.php/ajouter_document_mobile?titre=" + d.getTitre() + "&description=" + d.getDescription() + "&niveau=" + d.getNiveau() + "&matiere=" + d.getMatiere() + "&path=" + d.getPath() + "&idUser="+SessionManager.getId();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void supprimerDoc(Integer id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/piweb-master/web/app_dev.php/supprimer_doc_mobile/"+id;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            
            System.out.println(str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Document> getList2() {
        ArrayList<Document> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/liste_document_mobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Document task = new Document();
                        float id = Float.parseFloat(obj.get("id").toString());

                        task.setId((int) id);
                        task.setTitre(obj.get("titre").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setNiveau(obj.get("niveau").toString());
                        task.setMatiere(obj.get("matiere").toString());

                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    public Document DetailDoc(int id, Document task) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/piweb-master/web/app_dev.php/find/" + id;
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent e) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                task.setTitre(obj.get("titre").toString());

            } catch (IOException ex) {
                System.out.println("error sql");
            }
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return null;
    }
    
    public ArrayList<Document> getList(Integer id) {
        ArrayList<Document> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piweb-master/web/app_dev.php/liste_attente_mobile/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Document task = new Document();
                        float id = Float.parseFloat(obj.get("id").toString());

                        task.setId((int) id);
                        task.setTitre(obj.get("titre").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setNiveau(obj.get("niveau").toString());
                        task.setMatiere(obj.get("matiere").toString());

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

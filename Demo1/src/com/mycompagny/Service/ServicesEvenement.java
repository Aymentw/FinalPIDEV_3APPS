/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.mycompany.Entite.Evenement;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;





/**
 *
 * @author Mechlaoui
 */
public class ServicesEvenement {
    
    public static String getDate(Date date){       
        SimpleDateFormat jdf = new SimpleDateFormat("EEEE, dd MMMM, yyyy HH:mm");
        String java_date = jdf.format(date);
        return java_date;
    }   
    
    public static ArrayList<Evenement> AllEvents(){
        ArrayList<Evenement> list = new ArrayList<>();
        String url = "http://127.0.0.1:8000/api/allevents";
        ConnectionRequest request = new ConnectionRequest(url,false);
        NetworkManager.getInstance().addToQueueAndWait(request);
        
                JSONParser j = new JSONParser();
                String json = new String(request.getResponseData()) + "";
                if (!json.equals("no data")){                            
                Map<String, Object> content;
            try {
                content = j.parseJSON(new CharArrayReader(json.toCharArray()));
                List<Map<String, Object>> events = (List<Map<String, Object>>) content.get("root");
                for(Map<String, Object> event : events){
                                    
                Map<String, Object> user = (Map<String, Object>)event.get("idOrganisateur");
                Map<String, Object> time = (Map<String, Object>)event.get("dateEvent");
                Date datEV = new Date((long)Double.parseDouble(time.get("timestamp").toString())*1000L);
                
                                    
                                    if (event.get("typeReservation").toString().equals("Gratuite")){
                                        
                                    
                                    list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,
                                            (int)Float.parseFloat(event.get("disponible").toString())
                                    ));
                                    }
                                    else{
                                        list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,(int)Float.parseFloat(event.get("prix").toString()),
                                            (int)Float.parseFloat(event.get("disponible").toString()))
                                        );
                                        
                                    }
                            }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             
                    
                  //  System.out.println(list);
                    
    }
    return list;
    }
    
    public static Evenement EventByID(int id){
        String id_string = String.valueOf(id);
        Map<String, Object> event;
        Evenement evenement = new Evenement();
        String url = "http://127.0.0.1:8000/api/eventById";
        ConnectionRequest request = new ConnectionRequest(url,false);
        request.addArgument("id", id_string);
        NetworkManager.getInstance().addToQueueAndWait(request);
        
                JSONParser j = new JSONParser();
                String json = new String(request.getResponseData()) + "";
                if (!json.equals("no data")){                            
            try {
                event = j.parseJSON(new CharArrayReader(json.toCharArray()));
                                    
                Map<String, Object> user = (Map<String, Object>)event.get("idOrganisateur");
                Map<String, Object> time = (Map<String, Object>)event.get("dateEvent");
                Date datEV = new Date((long)Double.parseDouble(time.get("timestamp").toString())*1000L);
                
                                    if (event.get("typeReservation").toString().equals("Gratuite")){
                                        
                                    
                                    evenement = new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,
                                            (int)Float.parseFloat(event.get("disponible").toString())
                                    );
                                    
                                    }
                                    else{
                                    evenement = new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,(int)Float.parseFloat(event.get("prix").toString()),
                                            (int)Float.parseFloat(event.get("disponible").toString())
                                    );
                                    }
                                    
                            } catch (IOException ex) {
                ex.printStackTrace();
            }
           
             
                    
                  //  System.out.println(list);
                    
    } else{
                    evenement = null;
                }
         //       System.out.println(evenement);
    return evenement;
    }
    
    public static void addEvent(Evenement e, String df,String nom, String prix,String quota){
        String url = "http://127.0.0.1:8000/api/add_event";
                MultipartRequest req = new MultipartRequest();
                try {
                req.setUrl(url);
                req.setPost(true);
                req.addArgument("nom", nom);
                req.addArgument("type", e.getType().name());
                req.addArgument("res", e.getType_Reservation().name());
                req.addArgument("date", df);
                req.addArgument("duree", e.getDuree());
                req.addArgument("lieu", e.getLieu());
                req.addArgument("quota", quota);
                req.addArgument("description", e.getDescription());
                req.addArgument("etat", e.getEtat().name());
                req.addArgument("id_org", String.valueOf(e.getID_Organisateur()));
                if(!prix.equals("0")){
                req.addArgument("prix", prix);    
                }
                
                if(!e.getAffiche().equals("")){    
                req.addData("photo", e.getAffiche(),"image/jpeg");
                req.setFilename("photo", nom+".jpg");
            }
             req.addResponseListener((response)-> {
                        
                        byte[] data = (byte[]) response.getMetaData();
                        String s = new String(data);
                        System.out.println(s);                                                
                        if (s.equals("success")) {                            
                        } else {
                            Dialog.show("Erreur", "Echec d'ajout", "Ok", null);
                        }                    
                });

                NetworkManager.getInstance().addToQueueAndWait(req);
                } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    
    public static void editEvent(Evenement e, String path,String df){
        String url = "http://127.0.0.1:8000/api/edit_event";
                MultipartRequest req = new MultipartRequest();
                try {
                req.setUrl(url);
                req.setPost(true);
                req.addArgument("id", String.valueOf(e.getID()));
                req.addArgument("nom", e.getNom());
                req.addArgument("type", e.getType().name());
                req.addArgument("res", e.getType_Reservation().name());
                req.addArgument("date", df);
                req.addArgument("duree", e.getDuree());
                req.addArgument("lieu", e.getLieu());
                req.addArgument("quota", String.valueOf(e.getNombre()));
                req.addArgument("description", e.getDescription());
                req.addArgument("etat", e.getEtat().name());
                req.addArgument("id_org", String.valueOf(e.getID_Organisateur()));
                if(e.getPrix() != 0){
                req.addArgument("prix", String.valueOf(e.getPrix()));    
                }
                
                if(!path.equals("")){    
                req.addData("photo", path,"image/jpeg");
                req.setFilename("photo", e.getNom()+".jpg");
            }
             req.addResponseListener((response)-> {
                        
                        byte[] data = (byte[]) response.getMetaData();
                        String s = new String(data);
                        System.out.println(s);                                                
                        if (s.equals("success")) {                            
                        } else {
                            Dialog.show("Erreur", "Echec d'ajout", "Ok", null);
                        }                    
                });

                NetworkManager.getInstance().addToQueueAndWait(req);
                } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    
    
    public static ArrayList<Evenement> EventsWeek(){
        ArrayList<Evenement> list = new ArrayList<>();
        String url = "http://127.0.0.1:8000/api/week";
        ConnectionRequest request = new ConnectionRequest(url,false);
        NetworkManager.getInstance().addToQueueAndWait(request);
        
                JSONParser j = new JSONParser();
                String json = new String(request.getResponseData()) + "";
                if (!json.equals("no data")){                            
                Map<String, Object> content;
            try {
                content = j.parseJSON(new CharArrayReader(json.toCharArray()));
                List<Map<String, Object>> events = (List<Map<String, Object>>) content.get("root");
                for(Map<String, Object> event : events){
                                    
                Map<String, Object> user = (Map<String, Object>)event.get("idOrganisateur");
                Map<String, Object> time = (Map<String, Object>)event.get("dateEvent");
                Date datEV = new Date((long)Double.parseDouble(time.get("timestamp").toString())*1000L);
                
                                    
                                    if (event.get("typeReservation").toString().equals("Gratuite")){
                                        
                                    
                                    list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,
                                            (int)Float.parseFloat(event.get("disponible").toString())
                                    ));
                                    }
                                    else{
                                        list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,(int)Float.parseFloat(event.get("prix").toString()),
                                            (int)Float.parseFloat(event.get("disponible").toString()))
                                        );
                                        
                                    }
                            }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             
                    
                  //  System.out.println(list);
                    
    }
                else{
                    list = null;
                }
    return list;
    }
    
    public static ArrayList<Evenement> CurrentGreater(){
        ArrayList<Evenement> list = new ArrayList<>();
        String url = "http://127.0.0.1:8000/api/current_event";
        ConnectionRequest request = new ConnectionRequest(url,false);
        NetworkManager.getInstance().addToQueueAndWait(request);
        
                JSONParser j = new JSONParser();
                String json = new String(request.getResponseData()) + "";
                if (!json.equals("no data")){                            
                Map<String, Object> content;
            try {
                content = j.parseJSON(new CharArrayReader(json.toCharArray()));
                List<Map<String, Object>> events = (List<Map<String, Object>>) content.get("root");
                for(Map<String, Object> event : events){
                                    
                Map<String, Object> user = (Map<String, Object>)event.get("idOrganisateur");
                Map<String, Object> time = (Map<String, Object>)event.get("dateEvent");
                Date datEV = new Date((long)Double.parseDouble(time.get("timestamp").toString())*1000L);
                
                                    
                                    if (event.get("typeReservation").toString().equals("Gratuite")){
                                        
                                    
                                    list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,
                                            (int)Float.parseFloat(event.get("disponible").toString())
                                    ));
                                    }
                                    else{
                                        list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,(int)Float.parseFloat(event.get("prix").toString()),
                                            (int)Float.parseFloat(event.get("disponible").toString()))
                                        );
                                        
                                    }
                            }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             
    
    }
                else{
                    list = null;
                }
    return list;
    }
    
    public static ArrayList<Evenement> myEvents(String id){
        ArrayList<Evenement> list = new ArrayList<>();
        String url = "http://127.0.0.1:8000/api/my_events";
        ConnectionRequest request = new ConnectionRequest(url,false);
        request.addArgument("id", id);
        NetworkManager.getInstance().addToQueueAndWait(request);
        
                JSONParser j = new JSONParser();
                String json = new String(request.getResponseData()) + "";
                if (!json.equals("no data")){                            
                Map<String, Object> content;
            try {
                content = j.parseJSON(new CharArrayReader(json.toCharArray()));
                List<Map<String, Object>> events = (List<Map<String, Object>>) content.get("root");
                for(Map<String, Object> event : events){
                                    
                Map<String, Object> user = (Map<String, Object>)event.get("idOrganisateur");
                Map<String, Object> time = (Map<String, Object>)event.get("dateEvent");
                Date datEV = new Date((long)Double.parseDouble(time.get("timestamp").toString())*1000L);
                
                                    
                                    if (event.get("typeReservation").toString().equals("Gratuite")){
                                        
                                    
                                    list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,
                                            (int)Float.parseFloat(event.get("disponible").toString())
                                    ));
                                    }
                                    else{
                                        list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,(int)Float.parseFloat(event.get("prix").toString()),
                                            (int)Float.parseFloat(event.get("disponible").toString()))
                                        );
                                        
                                    }
                            }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             
                    
                  //  System.out.println(list);
                    
    }
    return list;
    }
    
    public static void deleteEvent(String id_string){
         String url = "http://127.0.0.1:8000/api/delete_event";
         ConnectionRequest request = new ConnectionRequest(url,false);
         request.addArgument("id", id_string);
         NetworkManager.getInstance().addToQueueAndWait(request);
    }
    
    
    public static ArrayList<Evenement> myResEvent(String id){
        ArrayList<Evenement> list = new ArrayList<>();
        String url = "http://127.0.0.1:8000/api/mes_res";
        ConnectionRequest request = new ConnectionRequest(url,false);
        request.addArgument("id", id);
        NetworkManager.getInstance().addToQueueAndWait(request);
        
                JSONParser j = new JSONParser();
                String json = new String(request.getResponseData()) + "";
                if (!json.equals("no data")){                            
                Map<String, Object> content;
            try {
                content = j.parseJSON(new CharArrayReader(json.toCharArray()));
                List<Map<String, Object>> events = (List<Map<String, Object>>) content.get("root");
                for(Map<String, Object> event : events){
                                    
                Map<String, Object> user = (Map<String, Object>)event.get("idOrganisateur");
                Map<String, Object> time = (Map<String, Object>)event.get("dateEvent");
                Date datEV = new Date((long)Double.parseDouble(time.get("timestamp").toString())*1000L);
                
                                    
                                    if (event.get("typeReservation").toString().equals("Gratuite")){
                                        
                                    
                                    list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,
                                            (int)Float.parseFloat(event.get("disponible").toString())
                                    ));
                                    }
                                    else{
                                        list.add(new Evenement((int)Float.parseFloat(event.get("id").toString()),
                                    (int)Float.parseFloat(user.get("id").toString()),event.get("nom").toString(),
                                            Evenement.TypeEvent.valueOf(event.get("type").toString()),
                                            Evenement.Type_Reservation.valueOf(event.get("typeReservation").toString()),
                                            datEV, event.get("duree").toString(),event.get("lieu").toString(),
                                            (int)Float.parseFloat(event.get("nombre").toString()),
                                            event.get("description").toString(),
                                            event.get("affiche").toString(),
                                            Evenement.EtatEvent.Oui,(int)Float.parseFloat(event.get("prix").toString()),
                                            (int)Float.parseFloat(event.get("disponible").toString()))
                                        );
                                        
                                    }
                            }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             
                    
                  //  System.out.println(list);
                    
    }
    return list;
    }
    
    
    public static void annulerRes(String id_user,String idEvent){
        String url = "http://127.0.0.1:8000/api/annuler_res";
        ConnectionRequest request = new ConnectionRequest(url,false);
        request.addArgument("id", id_user);
        request.addArgument("id_ev", idEvent);
        NetworkManager.getInstance().addToQueueAndWait(request);
    }
    
    public static void confirmerRes(String id_user,String idEvent){
        String url = "http://127.0.0.1:8000/api/confirmer_res";
        ConnectionRequest request = new ConnectionRequest(url,false);
        request.addArgument("id", id_user);
        request.addArgument("id_ev", idEvent);
        NetworkManager.getInstance().addToQueueAndWait(request);
    }

}

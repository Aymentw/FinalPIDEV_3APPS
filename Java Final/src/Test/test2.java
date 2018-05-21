/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entities.*;
import Services.ServicesCommentaire;
import Services.ServicesEvenement;
import Tech.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mechlaoui
 */
public class test2 {
    public static void main(String[] args) throws SQLException {
        /*Connection connection = DataSource.GetInstance().GetConnection();
    ServicesEvenement sev = new ServicesEvenement();
        HashMap<Evenement,List<Commentaire>> list =  sev.SelectEventsWeek();
        list.forEach((k,v) -> System.out.println(k+"  "+v));
        
        System.out.println("-------------------------------------");
        

        List<String> listaff = new ArrayList<String>();
        HashMap<Evenement,List<Commentaire>> aff = sev.SelectEventsWeek();
        aff.forEach((k,v) -> {
            listaff.add("http://localhost:8080/affiches/"+k.getAffiche());
        });
        
        System.out.println(listaff);
        System.out.println("-------------------------");
        
        Evenement ev = new Evenement(1, "Event0", Evenement.TypeEvent.Autres, Evenement.Type_Reservation.Gratuite, LocalDateTime.now(),"2h", "Esprit", 50, "des", "title.png", Evenement.EtatEvent.Oui);
        ev.setID(12);
        ServicesCommentaire sc = new ServicesCommentaire();
        List<Commentaire> listec = sc.SelectCommentaireByEvent(ev);
        System.out.println(listec);*/
                
        
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import Entities.*;
import Services.*;
import Tech.DataSource;
import java.awt.BorderLayout;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Mechlaoui
 */
public class Test {
    public static void main(String[] args) throws SQLException, Exception {
        Connection connection = DataSource.GetInstance().GetConnection();
        //Evenement ev = new Evenement(1, "Event0", Evenement.TypeEvent.Autres, Evenement.Type_Reservation.Gratuite, LocalDateTime.now(),"2h", "Esprit", 50, "des", "title.png", Evenement.EtatEvent.Oui);
       
        Evenement ev1 = new Evenement(17,8, "Eve1", Evenement.TypeEvent.Autres, Evenement.Type_Reservation.Gratuite, LocalDateTime.now(),"2h", "Esprit", 50, "des", "title.png", Evenement.EtatEvent.Oui,25);
        //String = "U"
        ServicesEvenement sev = new ServicesEvenement();
        sev.ModifierEvenementUser(ev1);
        //ev.setID(2);
        //ev.setDescription("Event00");
        //sev.AjouterEvenementUser(ev1,3);
        //sev.ModifierEvenementUser(ev, 1);
        //sev.ArchiverEvenement(ev, 1);
        //Evenement e = sev.SelectEvenementByID(2);
        //System.out.println(e.getAffiche());
        
        //ev1.setID(2);
       /* Reservation r = new Reservation(ev1, 2, Reservation.Type_Reservation.Gratuite, 5, 10, Reservation.Etat_Reservation.Confirmé);
        ServicesReservation sres = new ServicesReservation();
        
        //sres.AnnulerReservation(r);
        if (sres.Reserver(r)){
            System.out.println("OK");
        }
        else {
            System.out.println("Non");
        }
        */

        //sres.Reconfirmer(r);
        

        //ev1.setID(12);
        //ev.setID(2);
        
        /*Commentaire c = new Commentaire(ev, 1, LocalDateTime.now(),"TestCommEv0", Commentaire.Etat_Commentaire.OK);
        Commentaire c1 = new Commentaire(ev1, 1, LocalDateTime.now(),"TestCommEv1", Commentaire.Etat_Commentaire.OK);
        Commentaire c2 = new Commentaire(ev1, 1, LocalDateTime.now(),"TestCommEv2", Commentaire.Etat_Commentaire.OK);
        ServicesCommentaire secom = new ServicesCommentaire();
        //secom.CommenterEvent(c);
        List<Commentaire> Commentaires = secom.SelectCommentaireByEvent(ev1);
        Commentaires.forEach((Commentaire e) -> System.out.println(e.getContenu()));
        HashMap<Evenement,List<Commentaire>> list = secom.SelectCommentaireEvent(ev1);
        list.forEach((k,v) -> {
            System.out.println(k.getNom());
            v.forEach((Commentaire com) -> {System.out.println(com.getContenu());});
        });
        
        System.out.println("----------------");
        
        HashMap<Evenement,List<Commentaire>> listall = secom.SelectCommentaires();
        listall.forEach((k,v) -> System.out.println(k.getNom() + " "+ v));
  
                                
        */

        
        //List<Commentaire> coms = listall.get(ev);
        
        //coms.forEach(e -> System.out.println(e.getEtat_Commentaire().name()));
        
        

        //secom.CommenterEvent(c);
        //secom.CommenterEvent(c1);
        //secom.CommenterEvent(c2);
        //c.setID_Commentaire(2);
        //c.setContenu("ReTestComm");
        //secom.ModifierCommentaireEvent(c);
        //secom.SupprimerCommentaireEvent(c);
        //secom.ReporterCommentaireEvent(c);
        
        //ev.setID(0);
        //Evaluation e = new Evaluation(ev, 4, 5);
        //e.setID_Evaluation(2);
        //e.setNote(71);
        //e.setEtat_Commentaire(Evaluation.Etat_Commentaire.Reported);
        //ServicesEvaluation seval = new ServicesEvaluation();
        //seval.AjouterNote(e);
        //seval.ModifierNote(e);
        //seval.SupprimerNote(e);
        
        //ev1.setID(2);
        //ev1.setNom("Testmodif");
        //ev1.setAffiche("testaff");
        //ServicesEvenement sev = new ServicesEvenement();
        //sev.AjouterEvenement(ev1);
        //ServicesReservation sres = new ServicesReservation();
        //sres.Reserver(ev1, 4);
        //sres.AnnulerReservation(ev1, 4);
        //sres.Reconfirmer(ev1, 4);
        //sres.test(ev1);
        //sres.Reserver(ev1, 1);
        
        //sev.SupprimerEvenement(ev1);
        //sev.ModifierEvenement(ev1);
        //sev.SelectEvenement().forEach(e -> System.out.println(e.getID())); 
        
        //Activite a = new Activite("test", Activite.Discipline.Football,LocalDateTime.now(),
               //                      "2heures" , "lieu", "Desc", "aff");
        //a.setID(2);
        //a.setNom("TestmodifA");
        //ServicesActivite sev = new ServicesActivite();
        //sev.AjouterActivite(a);
        //sev.SupprimerActivite(a);
        //sev.ModifierActivite(a);
        //sev.SelectActivite().forEach(e -> System.out.println(e.getNom()));
    } 
}

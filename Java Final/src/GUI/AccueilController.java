/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.util.NoSuchElementException;

import Entities.Commentaire;
import Entities.Evenement;
import Entities.Reservation;
import Entities.Session;
import Services.ServicesCommentaire;
import Services.ServicesEvenement;
import Services.ServicesReservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.ScaleTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.LoopingListIterator;
/**
 * FXML Controller class
 *
 * @author Mechlaoui
 */
public class AccueilController implements Initializable {
    @FXML
    private Pane event_pane_home;
    @FXML
    private Pane slide_pane;
    @FXML
    private GridPane grid_comment;
    @FXML
    private JFXListView<GridPane> comment_list;
    @FXML
    private JFXTextArea comment_content;
    @FXML
    private JFXButton btn_commenter;
    @FXML
    private Pane top_pane;
    @FXML
    private JFXButton events_home;
    @FXML
    private JFXButton reservation_btn;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Pane sidepane;
    @FXML
    private Pane coloc_pane;
    @FXML
    private Text coloc_text;
    @FXML
    private ImageView img_coloc;
    @FXML
    private Pane cov_pane;
    @FXML
    private ImageView img_cov;
    @FXML
    private Text cov_text;
    @FXML
    private Pane doc_pane;
    @FXML
    private ImageView img_doc;
    @FXML
    private Text doc_text;
    @FXML
    private Pane com_pane;
    @FXML
    private Text com_text;
    @FXML
    private ImageView img_com;
    @FXML
    private Pane user_pane;
    @FXML
    private Pane event_pane;
    @FXML
    private Pane back_pane;
    @FXML
    private ImageView img_back;
    @FXML
    private JFXButton back;
    @FXML
    private JFXHamburger menu;
    @FXML
    private Pane next_pane;
    @FXML
    private ImageView img_next;
    @FXML
    private JFXButton next;
    
    
    @FXML
    private StackPane comment_stackpane;
    
    @FXML
    private JFXDialogLayout report_dialog;
    
    @FXML
    private JFXButton affiche;
    private Evenement event;
    
    private List<Evenement> list = new ArrayList<Evenement>();
    @FXML
    ImageView Affiches;
    Image images[];
    @FXML
    private JFXDialogLayout details_dialog;
    @FXML
    private StackPane stackpane_details;
    @FXML
    private StackPane reservation_stackpane;
    @FXML
    private JFXDialogLayout reservation_dialog;
    @FXML
    private ImageView event_image;
    @FXML 
    private ImageView reserver_image;

    private JFXButton btn_modifier_comment;

    private JFXButton btn_supprimer_comment;
    @FXML
    private ImageView edit_image;
    @FXML
    private ImageView delete_image;
    @FXML
    private ImageView retour_image;
    @FXML
    private ImageView reservation_icon;
    @FXML
    private ImageView report_image;
    private JFXButton btn_oui_res;
    private JFXDialog DialogRes;
    @FXML
    private JFXButton details_event_home;
    @FXML
    private StackPane details_stackpane_home;
    @FXML
    private JFXDialogLayout details_dialog_home;
    private JFXDialog DialogDetails;

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Session.getRoleThisUser());
       event = new Evenement();
       Reservation reservation = new Reservation();
       //Reservation reservation = new Reservation();
               
        
       //drawer
       drawer.setSidePane(sidepane);
       drawer.setResizeContent(true);
       drawer.setOverLayVisible(false);
       drawer.setResizableOnDrag(true);
       comment_list.setVisible(false);
       drawer.setDirection(JFXDrawer.DrawerDirection.TOP);
       drawer.open();
       
       HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(menu);
       transition.setRate(-1);         
       menu.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
       transition.setRate(transition.getRate()*-1);
       transition.play();
       if (transition.getRate() == -1){
           drawer.setVisible(true); 
           comment_list.setVisible(false);
           drawer.open();
           
       }
               else{
           drawer.close();
           drawer.setVisible(false);
           comment_list.setVisible(true);
       }
       });
       
       //end drawer
       
       
       // Observable comment_list "Change Listener"
       
       
       //comments next and back
      try {
      list = getAffiches();
          System.out.println(list);
      list.sort((e,e1) -> e1.getDate_Event().compareTo(e.getDate_Event()));
      } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        LoopingListIterator<Evenement> itr = new LoopingListIterator<Evenement>(list);
        event = itr.next();

        try {
            Affiches.setImage(new Image("http://localhost/piweb-master/web/affiches/"+event.getAffiche()));
            affiche.setGraphic(Affiches);
            setCommentaires_list_view(event);
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        back.setOnAction(e -> {
            comment_list.getItems().clear();
            event = itr.previous();
            
        try {
            Affiches.setImage(new Image("http://localhost/piweb-master/web/affiches/"+event.getAffiche()));
            affiche.setGraphic(Affiches);
            setCommentaires_list_view(event);
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 
        });
        
        next.setOnAction(e -> {

            comment_list.getItems().clear();
            event = itr.next();
        try {
            Affiches.setImage(new Image("http://localhost/piweb-master/web/affiches/"+event.getAffiche()));
            affiche.setGraphic(Affiches);
            setCommentaires_list_view(event);
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
                       

        });
        
        
        

        //Reservation
        
        
        reservation_btn.setOnAction((ActionEvent ae) -> {
                                                
                ServicesEvenement sev = new ServicesEvenement();
                ServicesReservation sres = new ServicesReservation();
                List<Reservation> list_reservations;
                
                String prixText = event.getType_Reservation().name().equals("Payante") ? "Prix: "+event.getPrix()+" DT" : "";
                

                event_image.setVisible(false);
                reserver_image.setVisible(false);
                reservation_btn.setVisible(false);
                events_home.setVisible(false);
                
                
                btn_oui_res = new JFXButton("Reserver");
                JFXButton Btn_non = new JFXButton("Retour");
                
                reservation_dialog.setHeading(new Text("Voulez-vous réserver pour "+ event.getNom()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy HH:mm",Locale.FRANCE);
                 
                    try {
                       list_reservations = sev.SelectReservationsEvents().get(event);
          
                       int places_restantes = 0;                
                       places_restantes = !list_reservations.isEmpty() ? event.getNombre() - (int) list_reservations.stream().count() : event.getNombre();
                
                       if ( sres.HasReserved(event, Session.getIdThisUser())){
                       reservation_dialog.setBody(new Text(
                       
                       "Date  : "+ event.getDate_Event().format(formatter)+ "\n"+
                       "Duree: "+ event.getDuree()  +"\n"+
                       "Localisation: "+event.getLieu() + "\n"+
                       "Type : "+ event.getType().name() + "  "+"Reservation: "+event.getType_Reservation().name()+ "\n"+
                       "Places Disponibles: "+ String.valueOf(places_restantes) + "  "+prixText
                       
                                                    ));
                       }
                       else {
                       reservation_dialog.setBody(new Text("Vous avez déjà réservé pour cet évenement"+"\n"+"\n"+"\n"+
                                                           "PS: pour toute annulation veuillez accéder"+"\n"+
                                                           "sous la rubrique Mes Réservations"
                                                  
                               ));
                       btn_oui_res.setDisable(true);
                       }
                
                       } catch (SQLException ex) {
                       Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                
                DialogRes = new JFXDialog(reservation_stackpane,reservation_dialog, JFXDialog.DialogTransition.CENTER);
                Btn_non.setOnAction((ActionEvent e) ->{
                    DialogRes.close();
                    event_image.setVisible(true);
                    reserver_image.setVisible(true);
                    reservation_btn.setVisible(true);
                    events_home.setVisible(true);
                    
                        });
                btn_oui_res.setOnAction((ActionEvent e) -> {
                       
            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText(event.getNom());
                            alert.setContentText("Merci de confirmer votre réservation");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                 ServicesReservation ServiceRes = new ServicesReservation();
                       Reservation r = new Reservation();
                       r.setEvent(event);
                       r.setID_Participant(Session.getIdThisUser());
                       r.setType_Reservation(Reservation.Type_Reservation.valueOf(event.getType_Reservation().name()));
                       r.setTarif(event.getPrix());
                       
                       try {
                       ServiceRes.Reserver(r);
                       } catch (SQLException ex) {
                       Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                       }    
                       }
                       else {
                       alert.close();
                            }

                       DialogRes.close();
                       event_image.setVisible(true);
                       reserver_image.setVisible(true);
                       reservation_btn.setVisible(true);
                       events_home.setVisible(true);
                      
                });
                
                DialogRes.show();
                reservation_dialog.setActions(retour_image,Btn_non,reservation_icon,btn_oui_res);
           });
        
        //Details Evenements Accueil
        
        details_event_home.setOnAction((ActionEvent e) -> {
            details_event_home.setVisible(false);
            details_stackpane_home.setVisible(true);
            ServicesEvenement sev = new ServicesEvenement();
                ServicesReservation sres = new ServicesReservation();
                List<Reservation> list_reservations;
                
                String prixText = event.getType_Reservation().name().equals("Payante") ? "Prix: "+event.getPrix()+" DT" : "";
                
                JFXButton Btn_non = new JFXButton("Retour");
                
                details_dialog_home.setHeading(new Text("Evenement: "+ event.getNom()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy HH:mm",Locale.FRANCE);
                 
                    try {
                       list_reservations = sev.SelectReservationsEvents().get(event);
          
                       int places_restantes = 0;                
                       places_restantes = !list_reservations.isEmpty() ? event.getNombre() - (int) list_reservations.stream().count() : event.getNombre();
                
                       details_dialog_home.setBody(new Text(
                       
                       "Date  : "+ event.getDate_Event().format(formatter)+ "\n"+
                       "Duree: "+ event.getDuree()  +"\n"+
                       "Type  : "+ event.getType().name() + "  "+"Reservation: "+event.getType_Reservation().name()+ "\n"+        
                       "Localisation: "+event.getLieu() + "\n"+                       
                       "Places Disponibles: "+ String.valueOf(places_restantes) + "  "+prixText+"\n"+"\n"+
                       "Description : " + event.getDescription()
                                                      
                                                    ));
                                       
                       } catch (SQLException ex) {
                       Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                
                DialogDetails = new JFXDialog(details_stackpane_home,details_dialog_home, JFXDialog.DialogTransition.CENTER);
                Btn_non.setOnAction((ActionEvent retour) ->{
                    DialogDetails.close();
                    details_event_home.setVisible(true);
                    details_stackpane_home.setVisible(false);
                        });
                
                DialogDetails.show();
                details_dialog_home.setActions(retour_image,Btn_non);
           
       });
        
        
        

        
        

       
        
        
    }
      
       
    public List<Evenement> getAffiches() throws URISyntaxException, SQLException{
        ServicesEvenement sev = new ServicesEvenement();
        List<Evenement> listaff = new ArrayList<Evenement>(); 
        HashMap<Evenement,List<Commentaire>> aff = sev.SelectEventsWeek();
        aff.forEach((Evenement k,List<Commentaire> v) -> {

                listaff.add(k);

            
        });
        
        return listaff;
    }
    
    
    
    public void setCommentaires_list_view(Evenement event) throws SQLException{
       List<JFXButton> report = new ArrayList<JFXButton>();
       List<JFXButton> details = new ArrayList<JFXButton>();
       List<Label> contenu = new ArrayList<>();
       ServicesCommentaire sc = new ServicesCommentaire();
       List<Commentaire> list_commentaire = sc.SelectCommentaireByEvent(event);
       
       
   
       list_commentaire.sort((e,e1) -> e.getPost().compareTo(e1.getPost()));

       if (!list_commentaire.isEmpty()){
       for (int i=0; i <= list_commentaire.size()-1 ; i++) {
           GridPane comment_space = new GridPane();
            final int index = i;
            TextInputDialog EditDialog = new TextInputDialog(list_commentaire.get(i).getContenu());
            EditDialog.setTitle("Modification de commentaire");
            EditDialog.setHeaderText("Voulez-vous modifier ce commentaire");
           
            Commentaire commentaire_details = list_commentaire.get(i);
            
            
            contenu.add(new Label(list_commentaire.get(i).getContenu())) ;
            comment_space.add(contenu.get(i),i,i);
            Label label_content = contenu.get(i);
            
            details.add(new JFXButton("Détails"));
            report.add(new JFXButton("Reporter"));
            
            report.get(i).setStyle("-fx-text-fill:#F43955");
            details.get(i).setStyle("-fx-text-fill:#F43955");
            
            comment_space.add(report.get(i), i+1, i);
            comment_space.add(details.get(i),i+2,i);
            
            
            JFXButton report_content = report.get(i);
            JFXButton detail_content = details.get(i);
;
            
            
            

            String number = String.valueOf(i);
            
            details.get(i).setOnAction((ActionEvent e) -> {
            
            stackpane_details.setVisible(true);
            comment_stackpane.setVisible(false);
            comment_list.setVisible(false);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy HH:mm",Locale.FRANCE);
            String etat = commentaire_details.getEtat_Commentaire().name().equals("OK") ?  "Rien à signaler" : "Signalé";
            Text dialog_body = new Text("Contenu: "+commentaire_details.getContenu()+ "\n"+
                                        "Date      : "+ commentaire_details.getPost().format(formatter)+"\n"
                                        +"Etat       : "+ etat);
            
            details_dialog.setHeading(new Text("Utilisateur: "+Session.getUsernameThisUser()));            
            details_dialog.setBody(dialog_body);
            JFXButton Btn_retour = new JFXButton("Retour"); 
            btn_modifier_comment = new JFXButton("Modifier");
            btn_supprimer_comment = new JFXButton("Supprimer");
            
            if (Session.getIdThisUser() == commentaire_details.getID_User()){
                btn_modifier_comment.setDisable(false);
                btn_supprimer_comment.setDisable(false);
            }
            else {
                btn_modifier_comment.setDisable(true);;
                btn_supprimer_comment.setDisable(true);
            }
            

     
                    
            details_dialog.setActions(delete_image,btn_supprimer_comment,edit_image,btn_modifier_comment,retour_image,Btn_retour);            
            JFXDialog Dialog = new JFXDialog(stackpane_details,details_dialog, JFXDialog.DialogTransition.CENTER);
            Btn_retour.setOnAction((ActionEvent retour) -> {
                Dialog.close();
                comment_list.setVisible(true);
                    });
            
            
            btn_modifier_comment.setOnAction((ActionEvent modifier) ->{ 

                Optional<String> result = EditDialog.showAndWait();
                result.ifPresent(comment -> {
                    comment_space.getChildren().remove(label_content);
                    comment_space.add(new Label(comment), index, index);
                    commentaire_details.setContenu(comment);
                    try {
                        sc.ModifierCommentaireEvent(commentaire_details, Session.getIdThisUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                Dialog.close();
                comment_list.setVisible(true);
                
            });
            
            btn_supprimer_comment.setOnAction((ActionEvent modifier) ->{ 
                
                Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText(event.getNom());
                            alert.setContentText("Voulez-vous supprimer ce commentaire?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                  try {
                            comment_space.getChildren().remove(label_content);
                            comment_space.getChildren().remove(report_content);
                            comment_space.getChildren().remove(detail_content);
                            comment_list.getItems().remove(comment_space);
                            sc.SupprimerCommentaireEvent(commentaire_details, Session.getIdThisUser());
                                      } catch (SQLException ex) {
                                   Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                            }
                               else {
                                 alert.close();
                                    }
                            Dialog.close();
                            comment_list.setVisible(true);
                
            });
            
            
            
            
            
            Dialog.show();
            
            
            
            });
            
            
            report.get(i).setOnAction((ActionEvent e)-> {
                comment_stackpane.setVisible(true);
                stackpane_details.setVisible(false);
                report_dialog.setHeading(new Text("Reporter un commentaire"));
                report_dialog.setBody(new Text("Voulez-vous reporter ce commentaire"));
                
                JFXButton Btn_oui = new JFXButton("Oui");
                JFXButton Btn_non = new JFXButton("Annuler");
                JFXDialog Dialog = new JFXDialog(comment_stackpane,report_dialog, JFXDialog.DialogTransition.CENTER);
                Dialog.prefHeightProperty().bind(report_dialog.prefHeightProperty());
                Dialog.prefWidthProperty().bind(report_dialog.prefWidthProperty());
                Dialog.show();
                comment_list.setVisible(false);
                
                
                Btn_oui.setOnAction((ActionEvent eventOui) -> {
                    
                    
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText(event.getNom());
                            alert.setContentText("Veuillez confirmer votre report?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                  try {
                            sc.ReporterCommentaireEvent(commentaire_details);
                                      } catch (SQLException ex) {
                                   Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                            }
                               else {
                                 alert.close();
                                    }
                            
                    comment_list.setVisible(true);
                    Dialog.close();
                    comment_stackpane.setVisible(false);

                });
                
                report_dialog.setActions(report_image,Btn_oui,retour_image,Btn_non);
                Btn_non.setOnAction((ActionEvent eventNon) -> {
                    comment_list.setVisible(true);
                    Dialog.close();
                    comment_stackpane.setVisible(false);
                });
                
            });
            
            comment_list.getItems().add(comment_space);
            
        }
       }
       


       btn_commenter.setOnAction((ActionEvent e)-> {
           GridPane comment_space_new = new GridPane();
           Label comment_inserted = new Label(comment_content.getText());
           JFXButton detail_new = new JFXButton("Détails");
           detail_new.setStyle("-fx-text-fill:#F43955");
           Commentaire comm_add = new Commentaire();
           /*comm_add.setEvent(event);
            comm_add.setID_User(Session.getIdThisUser());
            comm_add.setPost();
            comm_add.setContenu(comment_content.getText());
            comm_add.setEtat_Commentaire(Commentaire.Etat_Commentaire.OK);*/
            
          
           
           if (!comment_content.getText().equals("")){
            comm_add.setEvent(event);
            comm_add.setID_User(Session.getIdThisUser());
            comm_add.setPost();
            comm_add.setContenu(comment_content.getText());
            comm_add.setEtat_Commentaire(Commentaire.Etat_Commentaire.OK);
           try {
              final int id_comm = sc.CommenterEvent(comm_add, Session.getIdThisUser());
              comm_add.setID_Commentaire(id_comm);
           } catch (SQLException ex) {
               Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
            comment_space_new.add(comment_inserted,0,0);
            comment_space_new.add(detail_new,1,0);
            comment_list.getItems().add(comment_space_new);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Commentaire Ajouté",ButtonType.CLOSE);
            alert.show();
            comment_content.clear();
           }
           else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Champs commentaire vide !",ButtonType.CLOSE);
                alert.show();
           }
           
           // Details commentaire ajouté
           
            TextInputDialog EditDialog_new = new TextInputDialog(comm_add.getContenu());
            EditDialog_new.setTitle("Modification de commentaire");
            EditDialog_new.setHeaderText("Voulez-vous modifier ce commentaire");
           
           detail_new.setOnAction((ActionEvent detail) -> {
            
            stackpane_details.setVisible(true);
            comment_stackpane.setVisible(false);
            comment_list.setVisible(false);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy HH:mm",Locale.FRANCE);
            String etat = comm_add.getEtat_Commentaire().name().equals("OK") ?  "Rien à signaler" : "Signalé";
            Text dialog_body = new Text("Contenu: "+comm_add.getContenu()+ "\n"+
                                        "Date      : "+ comm_add.getPost().format(formatter)+"\n"
                                        +"Etat       : "+ etat);
            
            details_dialog.setHeading(new Text("Utilisateur: "+Session.getUsernameThisUser()));            
            details_dialog.setBody(dialog_body);
            JFXButton Btn_retour = new JFXButton("Retour"); 
            btn_modifier_comment = new JFXButton("Modifier");
            btn_supprimer_comment = new JFXButton("Supprimer");
            
            if (Session.getIdThisUser() == comm_add.getID_User()){
                btn_modifier_comment.setDisable(false);
                btn_supprimer_comment.setDisable(false);
            }
            else {
                btn_modifier_comment.setDisable(true);;
                btn_supprimer_comment.setDisable(true);
            }
            

     
                    
            details_dialog.setActions(delete_image,btn_supprimer_comment,edit_image,btn_modifier_comment,retour_image,Btn_retour);            
            JFXDialog Dialog = new JFXDialog(stackpane_details,details_dialog, JFXDialog.DialogTransition.CENTER);
            Btn_retour.setOnAction((ActionEvent retour) -> {
                Dialog.close();
                comment_list.setVisible(true);
                    });
            
            
            btn_modifier_comment.setOnAction((ActionEvent modifier) ->{ 

                Optional<String> result = EditDialog_new.showAndWait();
                result.ifPresent(comment -> {
                    comment_space_new.getChildren().remove(comment_inserted);
                    comment_space_new.add(new Label(comment), 0, 0);
                    comm_add.setContenu(comment);
                    try {
                        sc.ModifierCommentaireEvent(comm_add,Session.getIdThisUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                Dialog.close();
                comment_list.setVisible(true);
                
            });
            
            btn_supprimer_comment.setOnAction((ActionEvent modifier) ->{ 
                
                Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText(event.getNom());
                            alert.setContentText("Voulez-vous supprimer ce commentaire?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                  try {
                            comment_space_new.getChildren().remove(comment_inserted);
                            comment_space_new.getChildren().remove(detail_new);
                            comment_list.getItems().remove(comment_space_new);
                            sc.SupprimerCommentaireEvent(comm_add, Session.getIdThisUser());
                                      } catch (SQLException ex) {
                                   Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                            }
                               else {
                                 alert.close();
                                    }
                            Dialog.close();
                            comment_list.setVisible(true);
                
            });
            
            Dialog.show();
            
            
            
            });
           
           
           
           
           
           
            });
       

       top_pane.prefWidthProperty().bind(comment_list.prefWidthProperty());
       
    }
       
       
    
    public void initialize_commentaires_static(){

       final List<JFXButton> report = new ArrayList<JFXButton>();

       for (int i = 0; i <= 10; i++) {
            GridPane comment_space = new GridPane();
            comment_space.add(new Label("Item" + i),i,i);  
            
            report.add(new JFXButton("Reporter"));
            String number = String.valueOf(i);
            report.get(i).setOnAction((ActionEvent e)-> {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Item"+number,ButtonType.CLOSE);
            alert.show();
            });
            comment_space.add(report.get(i), i+1, i);
            comment_list.getItems().add(comment_space);
            
        }
       


       btn_commenter.setOnAction((ActionEvent e)-> {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Commentaire Ajouté",ButtonType.CLOSE);
            alert.show();
            });

       
       
      // comment_list.prefHeightProperty().bind(drawer.prefHeightProperty());
       top_pane.prefWidthProperty().bind(comment_list.prefWidthProperty());
    }

   
       
       
    
       
       
       
      @FXML
    private void Eventbtn_action(ActionEvent event) {
    }
    
     @FXML
    private void colocbtn_action(ActionEvent event) {
        Accueil.changescenes("interfaceColocation.fxml");
    }

    @FXML
    private void covbtn_action(ActionEvent event) {
        Accueil.changescenes("Covoiturage.fxml");
    }

    @FXML
    private void docbtn_action(ActionEvent event) {
        Accueil.changescenes("AccueilDocuments.fxml");
    }

    @FXML
    private void combtn_action(ActionEvent event) {
        Accueil.changescenes("Interface.fxml");
    }
    
    
    
    @FXML
    private void next_action(ActionEvent event) {

    }
    

    @FXML
    private void back_exit(MouseEvent event) throws URISyntaxException {
      back_pane.setStyle("-fx-background-color:white");
      Image back = new Image(getClass().getResource("/Resources/back.png").toURI().toString());
      img_back.setImage(back);
      
    }

    @FXML
    private void back_hover(MouseEvent event) throws URISyntaxException {
      back_pane.setStyle("-fx-background-color:#F43955");
      Image back0 = new Image(getClass().getResource("/Resources/back0.png").toURI().toString());
      img_back.setImage(back0);
    }


    @FXML
    private void next_exit(MouseEvent event) throws URISyntaxException {
      next_pane.setStyle("-fx-background-color:white");
      Image next = new Image(getClass().getResource("/Resources/next.png").toURI().toString());
      img_next.setImage(next);
    }

    @FXML
    private void next_hover(MouseEvent event) throws URISyntaxException {
      next_pane.setStyle("-fx-background-color:#F43955");
      Image next0 = new Image(getClass().getResource("/Resources/next0.png").toURI().toString());
      img_next.setImage(next0);
    }
   
    

    @FXML
    private void coloc_hover(MouseEvent event) throws URISyntaxException {
      coloc_pane.setStyle("-fx-background-color:#F43955");
      Image colocation0 = new Image(getClass().getResource("/Resources/colocation0.png").toURI().toString());
      img_coloc.setImage(colocation0);
      coloc_text.setFill(Paint.valueOf("white"));
    }

    @FXML
    private void cov_hover(MouseEvent event) throws URISyntaxException {
      cov_pane.setStyle("-fx-background-color:#F43955");
      Image covoiturage0 = new Image(getClass().getResource("/Resources/covoiturage0.png").toURI().toString());
      img_cov.setImage(covoiturage0);
      cov_text.setFill(Paint.valueOf("white"));
    }

    @FXML
    private void doc_hover(MouseEvent event) throws URISyntaxException {
      doc_pane.setStyle("-fx-background-color:#F43955");
      Image document0 = new Image(getClass().getResource("/Resources/Document0.png").toURI().toString());
      img_doc.setImage(document0);
      doc_text.setFill(Paint.valueOf("white"));
    }

    @FXML
    private void com_hover(MouseEvent event) throws URISyntaxException {
      com_pane.setStyle("-fx-background-color:#F43955");
      Image communication0 = new Image(getClass().getResource("/Resources/communication0.png").toURI().toString());  
      img_com.setImage(communication0);
      com_text.setFill(Paint.valueOf("white"));
    }
    
          
          

    @FXML
    private void coloc_exit(MouseEvent event) throws URISyntaxException {
      coloc_pane.setStyle("-fx-background-color:white");
      coloc_pane.setStyle("-fx-border-color:#F43955");
      Image colocation = new Image(getClass().getResource("/Resources/colocation.png").toURI().toString());
      img_coloc.setImage(colocation);
      coloc_text.setFill(Paint.valueOf("#F43955"));
    }

    @FXML
    private void cov_exit(MouseEvent event) throws URISyntaxException {
      cov_pane.setStyle("-fx-background-color:white");
      cov_pane.setStyle("-fx-border-color:#F43955");
      Image covoiturage = new Image(getClass().getResource("/Resources/covoiturage.png").toURI().toString());
      img_cov.setImage(covoiturage);
      cov_text.setFill(Paint.valueOf("#F43955"));
    }

    @FXML
    private void doc_exit(MouseEvent event) throws URISyntaxException {
      doc_pane.prefHeight(243.0);
      doc_pane.prefWidth(187.0);
      doc_pane.setStyle("-fx-background-color:white");
      doc_pane.setStyle("-fx-border-color:#F43955");
      Image document = new Image(getClass().getResource("/Resources/Document.png").toURI().toString());
      img_doc.setImage(document);
      doc_text.setFill(Paint.valueOf("#F43955"));
    }

    @FXML
    private void com_exit(MouseEvent event) throws URISyntaxException {
      com_pane.setStyle("-fx-background-color:white");
      com_pane.setStyle("-fx-border-color:#F43955");
      Image communication = new Image(getClass().getResource("/Resources/communication.png").toURI().toString());
      img_com.setImage(communication);
      com_text.setFill(Paint.valueOf("#F43955"));
    }

@FXML
    private void user_hover(MouseEvent event) {
        ScaleTransition st=new ScaleTransition(Duration.millis(100), user_pane);        
        st.setToX(1.5);        
        st.setToY(1.5);
        st.play();
    }
    
    @FXML
    private void user_exit(MouseEvent event) {
        ScaleTransition st=new ScaleTransition(Duration.millis(100), user_pane);
        st.setToX(1);       
        st.setToY(1);
        st.play();
    }

    @FXML
    private void navigation_event(ActionEvent event) throws IOException {
        
       Accueil.changescenes("Mes_Evenements.fxml");
        
    }
    
    
}

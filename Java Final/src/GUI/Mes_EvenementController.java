/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Commentaire;
import Entities.Evenement;
import Entities.EventBeans;
import Entities.Reservation;
import Entities.ReservationBeans;
import Entities.Session;
import Services.ServicesCommentaire;
import Services.ServicesEvenement;
import Services.ServicesReservation;
import Services.Upload;
import Tech.InputValidation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.collections4.iterators.LoopingListIterator;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;



/**
 * FXML Controller class
 *
 * @author Mechlaoui
 */
public class Mes_EvenementController implements Initializable {
    

   @FXML
    private Pane top_pane;

    @FXML
    private JFXButton home;

    @FXML
    private JFXButton bnt_activite;

    @FXML
    private JFXButton btn_events_home;


    @FXML
    private JFXButton btn_mes_events;

    @FXML
    private JFXButton btn_mes_reservation;

    @FXML
    private Pane add_event_pane;

    @FXML
    private ImageView btn_ajout_event;

    @FXML
    private JFXButton Add_event;

    @FXML
    private VBox left_add;

    @FXML
    private Label duree_grid1;

    @FXML
    private Label localisation_grid1;

    @FXML
    private Label nbplace_grid1;

    @FXML
    private Label description_grid1;

    @FXML
    private JFXTextField titre_textfield_add;

    @FXML
    private DatePicker date_picker_grid_add;

    @FXML
    private JFXComboBox<String> type_event_add;

    @FXML
    private JFXComboBox<String> type_reservation_add;

    @FXML
    private Label nbplace_grid12;

    @FXML
    private JFXTextField heure_textfield_add;

    @FXML
    private VBox right_add;

    @FXML
    private Label duree_grid11;

    @FXML
    private Label localisation_grid11;

    @FXML
    private Label nbplace_grid11;

    @FXML
    private Label description_grid11;

    @FXML
    private Label affiche_grid11;

    @FXML
    private JFXTextField duree_textfield_add;

    @FXML
    private JFXTextField localisation_textfield_add;

    @FXML
    private JFXTextField nb_places_textfield_add;

    @FXML
    private JFXTextArea description_textarea_add;

    @FXML
    private JFXButton import_add;

    @FXML
    private Label prix_grid_add;

    @FXML
    private JFXTextField prix_textfield_add;

    @FXML
    private Label errors_heure_add;

    @FXML
    private Label errors_date_add;

    @FXML
    private Label errors_duree_add;

    @FXML
    private Label errors_localisation_add;

    @FXML
    private Label errors_quota_add;

    @FXML
    private Label errors_desc_add;

    @FXML
    private Label errors_prix_add;

    @FXML
    private Pane mes_event_pane;

    @FXML
    private Label editableTreeTableViewCount;

    @FXML
    private JFXTextField searchEventField;   

    @FXML
    private JFXTreeTableView<EventBeans> Mes_Events_TreeTable;

    @FXML
    private JFXTreeTableColumn<EventBeans, String> evenementColumn;
    
    @FXML
    private JFXTreeTableColumn<EventBeans, String> typeColumn;

    @FXML
    private JFXTreeTableColumn<EventBeans, String> type_reservationColumn;

    @FXML
    private JFXTreeTableColumn<EventBeans, String> etatColumn;

    @FXML
    private Pane seperator_pane;

    @FXML
    private Label date_grid;

    @FXML
    private Label duree_grid;

    @FXML
    private Label localisation_grid;

    @FXML
    private Label nbplace_grid;

    @FXML
    private Label description_grid;

    @FXML
    private Label affiche_grid;

    @FXML
    private DatePicker date_picker_grid;

    @FXML
    private JFXTextField duree_textfield;

    @FXML
    private JFXTextField localisation_textfield;

    @FXML
    private JFXTextField nb_places_textfield;

    @FXML
    private JFXTextArea description_textarea;

    @FXML
    private ImageView affiche_img;

    @FXML
    private JFXButton affiche_btn;

    @FXML
    private JFXButton importer_edit_supp;

    @FXML
    private JFXButton btn_supprimer;

    @FXML
    private JFXButton btn_modifier;

    @FXML
    private JFXButton modifier_dis;

    @FXML
    private JFXButton supprimer_dis;

    @FXML
    private Pane mes_reserations_pane;

    @FXML
    private Label editableTreeTableViewCount1;

    @FXML
    private JFXTextField searchResField;

    @FXML
    private JFXTreeTableView<ReservationBeans> mes_res_tree;

    @FXML
    private JFXTreeTableColumn<ReservationBeans, String> evenementResColumn;

    @FXML
    private JFXTreeTableColumn<ReservationBeans, String> typeResColumn;

    @FXML
    private JFXTreeTableColumn<ReservationBeans, String> type_reservationResColumn;

    @FXML
    private JFXTreeTableColumn<ReservationBeans, String> etatResColumn;

    @FXML
    private JFXButton btn_reconfirmer;

    @FXML
    private JFXButton btn_annulerRes;

    @FXML
    private JFXButton annulerRes_dis;

    @FXML
    private JFXButton reconfirmerRes_dis;

    private Pane gallerie_pane;

    @FXML
    private Pane main_pane;

    @FXML
    private Pane event_pane;

    @FXML
    private Pane back_pane;

    @FXML
    private ImageView img_back;

    @FXML
    private JFXButton back;

    @FXML
    private Pane next_pane;

    @FXML
    private ImageView img_next;

    @FXML
    private JFXButton next;

    @FXML
    private JFXButton affiche;

    @FXML
    private ImageView Affiches;

    @FXML
    private JFXButton details_event_home;

    @FXML
    private StackPane details_stackpane_home;

    @FXML
    private JFXDialogLayout details_dialog_home;

    @FXML
    private GridPane grid_comment;

    @FXML
    private StackPane stackpane_details;

    @FXML
    private JFXDialogLayout details_dialog;

    @FXML
    private StackPane comment_stackpane;

    @FXML
    private JFXDialogLayout report_dialog;

    @FXML
    private JFXListView<GridPane> comment_list;

    @FXML
    private JFXTextArea comment_content;

    @FXML
    private JFXButton btn_commenter;

    @FXML
    private JFXButton btn_add_event;
    
    @FXML
    private Pane activities_pane;
    
    @FXML
    private Label prix_grid;
    
    @FXML
    private JFXTextField prix_textfield;
    
    @FXML
    private JFXTextField heure_textfield;
    
    @FXML
    private JFXButton archiver_dis;
    
    @FXML
    private JFXButton btn_archiver;
    
    
    @FXML
    private StackPane details_stackpane_note;

    @FXML
    private JFXDialogLayout details_dialog_note;

    @FXML
    private Label editableTreeTableViewCount11;

    @FXML
    private JFXTextField searchNoteField;

    @FXML
    private JFXTreeTableView<EventBeans> notre_tree;

    @FXML
    private JFXTreeTableColumn<EventBeans, String> evenementNoteColumn;

    @FXML
    private JFXTreeTableColumn<EventBeans, String> typeNoteColumn;

    @FXML
    private JFXTreeTableColumn<EventBeans, String> type_reservationNomColumn1;

    @FXML
    private JFXButton btn_note;

    @FXML
    private JFXTextField note_textfield;

    
    
    //Not FXML
    
    private File UplodedAffiche;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public ObservableList<String> listTypeEvent = FXCollections.observableArrayList(Evenement.TypeEvent.Associatif.name(),
            Evenement.TypeEvent.Culturel.name(), Evenement.TypeEvent.Autres.name());
    
    public ObservableList<String> listTypeReservation = FXCollections.observableArrayList(
            Evenement.Type_Reservation.Gratuite.name(),Evenement.Type_Reservation.Payante.name());
            
    public StringProperty afficheUp = new SimpleStringProperty("");
    
    Evenement event_added = new Evenement();
        String titre=null;
        String type_ev=null;
        String type_res=null;
        LocalDateTime date_ev=null;
        String duree=null;
        String localisation=null;
        int Quota = -1;
        int prix;
        String description=null;
        
    List<Evenement> mes_evenements_list;
    List<Evenement> all_evenements_list;
    List<Reservation> mes_reservations_list;
    @FXML
    private Label date_grid1;
    @FXML
    private Label label_edit_error;
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
    
    private Evenement event;
    
    private List<Evenement> list = new ArrayList<Evenement>();

    Image images[];   
    
    private JFXButton btn_modifier_comment;

    private JFXButton btn_supprimer_comment;
    private JFXDialog DialogDetails;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Next and back
        
        try {
      list = getAffiches(); 
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
        
        // details All Events
        
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
        
      
        
        
        
        //Note TableView
        // MY Events TreeTableView
        ServicesEvenement sev1 = new ServicesEvenement();
        ObservableList<EventBeans> events_all = FXCollections.observableArrayList();
       try {
           all_evenements_list = sev1.SelectEvenementAll();
           System.out.println(all_evenements_list);
           all_evenements_list.forEach(e -> {   
            if (e.getType_Reservation().equals("Grauite")){
            events_all.add(new EventBeans(String.valueOf(e.getID()),String.valueOf(e.getID_Organisateur()),
                       String.valueOf(e.getNom()),String.valueOf(e.getType().name()),String.valueOf(e.getType_Reservation().name()),
                       String.valueOf(e.getDate_Event()),String.valueOf(e.getDuree()),String.valueOf(e.getLieu()),
                       String.valueOf(e.getNombre()),String.valueOf(e.getDescription()),String.valueOf(e.getAffiche()),      
                       String.valueOf(e.getEtat().name())));
            }
            else{
                events_all.add(new EventBeans(String.valueOf(e.getID()),String.valueOf(e.getID_Organisateur()),
                       String.valueOf(e.getNom()),String.valueOf(e.getType().name()),String.valueOf(e.getType_Reservation().name()),
                       String.valueOf(e.getDate_Event()),String.valueOf(e.getDuree()),String.valueOf(e.getLieu()),
                       String.valueOf(e.getNombre()),String.valueOf(e.getDescription()),String.valueOf(e.getAffiche()),      
                       String.valueOf(e.getEtat().name()),String.valueOf(e.getPrix())));
            }
                   

        });
       } catch (SQLException ex) {
           Logger.getLogger(Mes_EvenementController.class.getName()).log(Level.SEVERE, null, ex);
       }
        System.out.println(events_all);         
        
        
        
        //Valide Values Colummns
        evenementNoteColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<EventBeans, String> param) -> {
            if (evenementNoteColumn.validateValue(param)) {
                return param.getValue().getValue().Nom;
            } else {
                return evenementNoteColumn.getComputedValue(param);
            }
        });
        
        
        typeNoteColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<EventBeans, String> param) -> {
            if (typeNoteColumn.validateValue(param)) {
                return param.getValue().getValue().Type;
            } else {
                return typeNoteColumn.getComputedValue(param);
            }
        });
        
        type_reservationNomColumn1.setCellValueFactory((TreeTableColumn.CellDataFeatures<EventBeans, String> param) -> {
            if (type_reservationNomColumn1.validateValue(param)) {
                return param.getValue().getValue().Type_Reservation;
            } else {
                return type_reservationNomColumn1.getComputedValue(param);
            }
        });
        
        
        
        
        //build Tree
        final TreeItem<EventBeans> rootNote = new RecursiveTreeItem<>(events_all, RecursiveTreeObject::getChildren); 
        notre_tree.setRoot(rootNote);
        notre_tree.setShowRoot(false);
        //notre_tree.getColumns().setAll(evenementColumn, typeColumn, type_reservationColumn,etatColumn);
        //Mes_Events_TreeTable.setst;
        
        // Filter
        
        searchNoteField.textProperty().addListener((o, oldVal, newVal) -> {
            notre_tree.setPredicate(eventProp -> {
                final EventBeans ev = eventProp.getValue();
                return ev.Nom.get().toLowerCase().contains(newVal.toLowerCase())
                    || ev.Type.get().toLowerCase().contains(newVal.toLowerCase())
                    || ev.Type_Reservation.get().toLowerCase().contains(newVal.toLowerCase());
                   
            });
        });

        
        
        
        
        
        // My Reservations TreeTableView
        // MY Events TreeTableView
        ServicesReservation sReser = new ServicesReservation();
        ObservableList<ReservationBeans> res = FXCollections.observableArrayList();
       try {
           mes_reservations_list = sReser.SelectReservationstUser(Session.getIdThisUser());
           mes_reservations_list.forEach(e -> {   
            if (e.getType_Reservation().equals("Grauite")){
            EventBeans evbeans = new EventBeans(String.valueOf(e.getEvent().getID()),String.valueOf(e.getEvent().getID_Organisateur()),
                       String.valueOf(e.getEvent().getNom()),String.valueOf(e.getEvent().getType().name()),String.valueOf(e.getEvent().getType_Reservation().name()),
                       String.valueOf(e.getEvent().getDate_Event()),String.valueOf(e.getEvent().getDuree()),String.valueOf(e.getEvent().getLieu()),
                       String.valueOf(e.getEvent().getNombre()),String.valueOf(e.getEvent().getDescription()),String.valueOf(e.getEvent().getAffiche()),
                       String.valueOf(e.getEvent().getEtat().name()));
            res.add(new ReservationBeans(String.valueOf(e.getID_Reservation()),evbeans,
                                         String.valueOf(e.getID_Participant()),String.valueOf(e.getType_Reservation().name()),
                                         String.valueOf(e.getEtat().name()))
                                         );
            }
            else{
            EventBeans evbeans = new EventBeans(String.valueOf(e.getEvent().getID()),String.valueOf(e.getEvent().getID_Organisateur()),
                       String.valueOf(e.getEvent().getNom()),String.valueOf(e.getEvent().getType().name()),String.valueOf(e.getEvent().getType_Reservation().name()),
                       String.valueOf(e.getEvent().getDate_Event()),String.valueOf(e.getEvent().getDuree()),String.valueOf(e.getEvent().getLieu()),
                       String.valueOf(e.getEvent().getNombre()),String.valueOf(e.getEvent().getDescription()),String.valueOf(e.getEvent().getAffiche()),      
                       String.valueOf(e.getEvent().getEtat().name()),String.valueOf(e.getEvent().getPrix()));
            res.add(new ReservationBeans(String.valueOf(e.getID_Reservation()),evbeans,
                                         String.valueOf(e.getID_Participant()),String.valueOf(e.getType_Reservation().name()),
                                         String.valueOf(e.getNumero_Ticket()),String.valueOf(e.getTarif()),
                                         String.valueOf(e.getEtat().name()))
                                         );
            
            }
                   

        });
       } catch (SQLException ex) {
           Logger.getLogger(Mes_EvenementController.class.getName()).log(Level.SEVERE, null, ex);
       }
                
        
        
        
        //Valide Values Colummns
        evenementResColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReservationBeans, String> param) -> {
            if (evenementResColumn.validateValue(param)) {
                return param.getValue().getValue().Event.Nom;
            } else {
                return evenementResColumn.getComputedValue(param);
            }
        });
        
        
        typeResColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReservationBeans, String> param) -> {
            if (typeResColumn.validateValue(param)) {
                return param.getValue().getValue().Event.Type;
            } else {
                return typeResColumn.getComputedValue(param);
            }
        });
        
        type_reservationResColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReservationBeans, String> param) -> {
            if (typeResColumn.validateValue(param)) {
                return param.getValue().getValue().Type_Reservation;
            } else {
                return typeResColumn.getComputedValue(param);
            }
        });
        
        etatResColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReservationBeans, String> param) -> {
            if (typeResColumn.validateValue(param)) {
                return param.getValue().getValue().Etat;
            } else {
                return typeResColumn.getComputedValue(param);
            }
        });
        
        
        
        //build Tree
        final TreeItem<ReservationBeans> rootRes = new RecursiveTreeItem<>(res, RecursiveTreeObject::getChildren); 
        mes_res_tree.setRoot(rootRes);
        mes_res_tree.setShowRoot(false);
        mes_res_tree.getColumns().setAll(evenementResColumn, typeResColumn, type_reservationResColumn,etatResColumn);
        //Mes_Events_TreeTable.setst;
        
        // Filter
        
        searchResField.textProperty().addListener((o, oldVal, newVal) -> {
            mes_res_tree.setPredicate(resProp -> {
                final ReservationBeans ev = resProp.getValue();
                return ev.Event.Nom.get().toLowerCase().contains(newVal.toLowerCase())
                    || ev.Event.Type.get().toLowerCase().contains(newVal.toLowerCase())
                    || ev.Type_Reservation.get().toLowerCase().contains(newVal.toLowerCase())
                    || ev.Etat.get().toLowerCase().contains(newVal.toLowerCase())    ;
            });
        });
        
        
        
        
        
        
        
        // MY Events TreeTableView
        ServicesEvenement sev2 = new ServicesEvenement();
        ObservableList<EventBeans> events = FXCollections.observableArrayList();
       try {
           mes_evenements_list = sev2.SelectEvenementUser(Session.getIdThisUser());
           mes_evenements_list.forEach(e -> {   
            if (e.getType_Reservation().equals("Grauite")){
            events.add(new EventBeans(String.valueOf(e.getID()),String.valueOf(e.getID_Organisateur()),
                       String.valueOf(e.getNom()),String.valueOf(e.getType().name()),String.valueOf(e.getType_Reservation().name()),
                       String.valueOf(e.getDate_Event()),String.valueOf(e.getDuree()),String.valueOf(e.getLieu()),
                       String.valueOf(e.getNombre()),String.valueOf(e.getDescription()),String.valueOf(e.getAffiche()),      
                       String.valueOf(e.getEtat().name())));
            }
            else{
                events.add(new EventBeans(String.valueOf(e.getID()),String.valueOf(e.getID_Organisateur()),
                       String.valueOf(e.getNom()),String.valueOf(e.getType().name()),String.valueOf(e.getType_Reservation().name()),
                       String.valueOf(e.getDate_Event()),String.valueOf(e.getDuree()),String.valueOf(e.getLieu()),
                       String.valueOf(e.getNombre()),String.valueOf(e.getDescription()),String.valueOf(e.getAffiche()),      
                       String.valueOf(e.getEtat().name()),String.valueOf(e.getPrix())));
            }
                   

        });
       } catch (SQLException ex) {
           Logger.getLogger(Mes_EvenementController.class.getName()).log(Level.SEVERE, null, ex);
       }
                
        
        
        
        //Valide Values Colummns
        evenementColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<EventBeans, String> param) -> {
            if (evenementColumn.validateValue(param)) {
                return param.getValue().getValue().Nom;
            } else {
                return evenementColumn.getComputedValue(param);
            }
        });
        
        
        typeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<EventBeans, String> param) -> {
            if (typeColumn.validateValue(param)) {
                return param.getValue().getValue().Type;
            } else {
                return typeColumn.getComputedValue(param);
            }
        });
        
        type_reservationColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<EventBeans, String> param) -> {
            if (typeColumn.validateValue(param)) {
                return param.getValue().getValue().Type_Reservation;
            } else {
                return typeColumn.getComputedValue(param);
            }
        });
        
        etatColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<EventBeans, String> param) -> {
            if (typeColumn.validateValue(param)) {
                return param.getValue().getValue().Etat;
            } else {
                return typeColumn.getComputedValue(param);
            }
        });
        
        
        
        // Get Edited values from TreeTableView and Update Event row
        
        evenementColumn.setCellFactory((TreeTableColumn<EventBeans, String> param) -> new GenericEditableTreeTableCell<>(
            new TextFieldEditorBuilder()));
        evenementColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<EventBeans, String> t) ->{
                if (t.getNewValue().equals("")){
           t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().setNom(t.getOldValue());
           etatColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<EventBeans, String> param) ->
           { return param.getValue().getValue().Nom; });
                }
                else{
                    t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().setNom(t.getNewValue());
                }
                   });
        
        typeColumn.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(listTypeEvent));
        typeColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<EventBeans, String> t) -> t.getTreeTableView()
                                                                      .getTreeItem(t.getTreeTablePosition()
                                                                                    .getRow())
                                                                      .getValue().setType(t.getNewValue()));
        
        type_reservationColumn.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(listTypeReservation));
        type_reservationColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<EventBeans, String> t) -> t.getTreeTableView()
                                                                      .getTreeItem(t.getTreeTablePosition()
                                                                                    .getRow())
                                                                      .getValue().setType_Reservation(t.getNewValue()));
        etatColumn.setCellFactory((TreeTableColumn<EventBeans, String> param) -> new GenericEditableTreeTableCell<>(
            new TextFieldEditorBuilder()));
        etatColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<EventBeans, String> t) -> t.getTreeTableView()
                                                                      .getTreeItem(t.getTreeTablePosition()
                                                                                    .getRow())
                                                                      .getValue().setType_Reservation(t.getNewValue()));
        
        //build Tree
        final TreeItem<EventBeans> root = new RecursiveTreeItem<>(events, RecursiveTreeObject::getChildren); 
        Mes_Events_TreeTable.setEditable(true);
        Mes_Events_TreeTable.setRoot(root);
        Mes_Events_TreeTable.setShowRoot(false);
        Mes_Events_TreeTable.getColumns().setAll(evenementColumn, typeColumn, type_reservationColumn,etatColumn);
        //Mes_Events_TreeTable.setst;
        
        // Filter
        
        searchEventField.textProperty().addListener((o, oldVal, newVal) -> {
            Mes_Events_TreeTable.setPredicate(eventProp -> {
                final EventBeans ev = eventProp.getValue();
                return ev.Nom.get().toLowerCase().contains(newVal.toLowerCase())
                    || ev.Type.get().toLowerCase().contains(newVal.toLowerCase())
                    || ev.Type_Reservation.get().toLowerCase().contains(newVal.toLowerCase())
                    || ev.Etat.get().toLowerCase().contains(newVal.toLowerCase())    ;
            });
        });
        
        
        //Actions on Mes_Events TreeTableView (Listeners For Edit & Delete & Archive)
        
        
        heure_textfield.textProperty().addListener((observable,old, newv) -> {
           if (observable.getValue().equals("")){
               label_edit_error.setVisible(false);
            }
           
           else if (!InputValidation.Heure(observable.getValue())){
               label_edit_error.setVisible(true);
               
           }
           else {
              label_edit_error.setVisible(false);
           }
           
        });             
        
        
        date_picker_grid.valueProperty().addListener((observable, oldvalue,newvalue) -> {
            if (date_picker_grid.getValue()==null){
               label_edit_error.setVisible(false);
            }
            else if(observable.getValue().isBefore(LocalDate.now())){
              label_edit_error.setVisible(true);
            }
            else {
                label_edit_error.setVisible(false);
            }
        });
        
        
        duree_textfield.textProperty().addListener((obs, old, newv) -> {
            if (duree_textfield.getText().equals("")){
               label_edit_error.setVisible(false);
            }
            else if(!InputValidation.AlphaNumeric(obs.getValue())){
            label_edit_error.setVisible(true);
                }
            else{
                label_edit_error.setVisible(false);
            }
            
        });
        
         
        
        localisation_textfield.textProperty().addListener((obs,old, newv) -> {
            if (localisation_textfield.getText().equals("")){
               label_edit_error.setVisible(false);
            }
            else if (!InputValidation.AlphaNumeric(obs.getValue())){
            label_edit_error.setVisible(true);    
            }
                else{
                label_edit_error.setVisible(false);
            }
        });
         
        
         nb_places_textfield.textProperty().addListener((obs, old, newv)-> {
            if (nb_places_textfield.getText().equals("")){
               label_edit_error.setVisible(false);
            }
            else if(!InputValidation.Numeric(obs.getValue())){
            label_edit_error.setVisible(true);            
             }
             else {
                 label_edit_error.setVisible(false);
             }
         });
         
        
          description_textarea.textProperty().addListener((obs,old, newv)->
                  {
                      if (description_textarea.getText().equals("")){
                             label_edit_error.setVisible(false);
                       }
                      else if (obs.getValue().length() > 255){
                          label_edit_error.setVisible(true);      
                      }
                      else {
                          label_edit_error.setVisible(false);
                      }
                           
                      
                  });
    
        prix_textfield.textProperty().addListener((obs, old, newv)-> {
            if (prix_textfield.getText().equals("")){
               label_edit_error.setVisible(false);
            }
            else if (!InputValidation.Numeric(obs.getValue())){

                          label_edit_error.setVisible(true);  
            }
            else{
                label_edit_error.setVisible(false);
            }
        });

        
        
        
        // Add event listeners
        
        type_event_add.setItems(listTypeEvent);
        type_reservation_add.setItems(listTypeReservation);

                
        
        type_reservation_add.getSelectionModel().selectedItemProperty().addListener((observale,old,newv)-> {
           if(Evenement.Type_Reservation.Payante.name().equals(newv))
                {
                 prix_grid_add.setVisible(true);
                 prix_textfield_add.setVisible(true);
                }
           if(Evenement.Type_Reservation.Gratuite.name().equals(newv))
                {
                 prix_grid_add.setVisible(false);
                 prix_textfield_add.setVisible(false);
                }
         
        }
        );               
        

        heure_textfield_add.textProperty().addListener((observable,old, newv) -> {
           if (observable.getValue().equals("")){
               errors_heure_add.setVisible(false);
            }
           
           else if (!InputValidation.Heure(observable.getValue())){
               errors_heure_add.setVisible(true);
               final String s = "Erreur heure donnez une heure valide";
               errors_heure_add.setText(s);
           }
           else {
              errors_heure_add.setVisible(false);
              errors_date_add.setText("");
           }
           
        });             
        
        
        date_picker_grid_add.valueProperty().addListener((observable, oldvalue,newvalue) -> {
            if (date_picker_grid_add.getValue()==null){
               errors_date_add.setVisible(false);
            }
            else if(observable.getValue().isBefore(LocalDate.now())){
              errors_date_add.setVisible(true);
              final String s = "Erreur donnez une date valide !";
              errors_date_add.setText(s);
            }
            else {
                errors_date_add.setVisible(false);
                errors_date_add.setText("");
            }
        });
        
        
        duree_textfield_add.textProperty().addListener((obs, old, newv) -> {
            if (duree_textfield_add.getText().equals("")){
               errors_duree_add.setVisible(false);
            }
            else if(!InputValidation.AlphaNumeric(obs.getValue())){
            errors_duree_add.setVisible(true);
            final String s="Erreur donnez une durée valide !";
            errors_duree_add.setText(s);
                }
            else{
                errors_duree_add.setVisible(false);
                errors_duree_add.setText("");
            }
            
        });
        
         
        
        localisation_textfield_add.textProperty().addListener((obs,old, newv) -> {
            if (localisation_textfield_add.getText().equals("")){
               errors_localisation_add.setVisible(false);
            }
            else if (!InputValidation.AlphaNumeric(obs.getValue())){
            errors_localisation_add.setVisible(true);
            final String s="Erreur donnez une localisation valide !";
            errors_localisation_add.setText(s);    
            }
                else{
                errors_localisation_add.setVisible(false);
                errors_localisation_add.setText("");
            }
        });
         
        
         nb_places_textfield_add.textProperty().addListener((obs, old, newv)-> {
            if (nb_places_textfield_add.getText().equals("")){
               errors_quota_add.setVisible(false);
            }
            else if(!InputValidation.Numeric(obs.getValue())){
            errors_quota_add.setVisible(true);
            final String s = "Erreur donner un quota valide !";
            errors_quota_add.setText(s);            
             }
             else {
                 errors_quota_add.setVisible(false);
                 errors_quota_add.setText("");
             }
         });
         
        
          description_textarea_add.textProperty().addListener((obs,old, newv)->
                  {
                      if (description_textarea_add.getText().equals("")){
                             errors_desc_add.setVisible(false);
                       }
                      else if (obs.getValue().length() > 255){
                          errors_desc_add.setVisible(true);
                          final String s = "Veuillez ne pas dépasser 255 caractère pour votre description !";
                          errors_desc_add.setText(s);      
                      }
                      else {
                          errors_desc_add.setVisible(false);
                          errors_desc_add.setText("");
                      }
                           
                      
                  });
    
        prix_textfield_add.textProperty().addListener((obs, old, newv)-> {
            if (prix_grid_add.getText().equals("")){
               errors_prix_add.setVisible(false);
            }
            else if (!InputValidation.Numeric(obs.getValue())){

                          errors_prix_add.setVisible(true); 
                          final String s = "Erreur donnez un prix valide !";
                          errors_prix_add.setText(s);  
            }
            else{
                errors_prix_add.setVisible(false);
                errors_prix_add.setText("");
            }
        });



        
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
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
                    
                    
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
    
    public List<Evenement> getAffiches() throws URISyntaxException, SQLException{
        ServicesEvenement sev = new ServicesEvenement();
        List<Evenement> listaff = new ArrayList<Evenement>(); 
        HashMap<Evenement,List<Commentaire>> aff = sev.SelectEventsWeek();
        aff.forEach((Evenement k,List<Commentaire> v) -> {

                listaff.add(k);

            
        });
        return listaff;
    }
    

    @FXML
    private void bnt_acceuil(ActionEvent event) throws IOException {

        Accueil.changescenes("Accueil.fxml");
    }
    
     @FXML
    void Activite_Pane(ActionEvent event) {
          main_pane.setVisible(false);
          activities_pane.setVisible(true);
          mes_event_pane.setVisible(false);
          mes_reserations_pane.setVisible(false);
          add_event_pane.setVisible(false);
    }
    
    
        @FXML
    void Events_Pane(ActionEvent event) {
          main_pane.setVisible(true);
          activities_pane.setVisible(false);
          mes_event_pane.setVisible(false);
          mes_reserations_pane.setVisible(false);
          add_event_pane.setVisible(false);
    }
    
       @FXML
    void Eventbtn_action(ActionEvent event) {

    }
    
    
    @FXML
    void Add_Event_Pane(ActionEvent event) {
          main_pane.setVisible(false);
          activities_pane.setVisible(false);
          mes_event_pane.setVisible(false);
          mes_reserations_pane.setVisible(false);
          add_event_pane.setVisible(true);
    }

    void Galleries_Pane(ActionEvent event) {
          main_pane.setVisible(false);
          activities_pane.setVisible(false);
          mes_event_pane.setVisible(false);
          mes_reserations_pane.setVisible(false);
          add_event_pane.setVisible(false);

    }

    @FXML
    void Mes_Events_Pane(ActionEvent event) {
          main_pane.setVisible(false);
          activities_pane.setVisible(false);
          mes_event_pane.setVisible(true);
          mes_reserations_pane.setVisible(false);
          add_event_pane.setVisible(false);  
    }

    @FXML
    void Mes_Res_Pane(ActionEvent event) {
          main_pane.setVisible(false);
          activities_pane.setVisible(false);
          mes_event_pane.setVisible(false);
          mes_reserations_pane.setVisible(true);
          add_event_pane.setVisible(false);
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
    private void AjoutEvenement(ActionEvent event) throws IOException {
        
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("erreur");
        
        if (!errors_date_add.isVisible() && !errors_duree_add.isVisible() && !errors_desc_add.isVisible()
             && !errors_heure_add.isVisible() && !errors_prix_add.isVisible() && !errors_localisation_add.isVisible()
              && !errors_quota_add.isVisible()  ){
            if(heure_textfield_add.getText().equals("") && type_event_add.getSelectionModel().isEmpty()
               && heure_textfield_add.getText().equals("") && type_reservation_add.getSelectionModel().isEmpty()
               && date_picker_grid_add.getValue()==null && duree_textfield_add.getText().equals("")
               && localisation_textfield_add.getText().equals("") && nb_places_textfield_add.getText().equals("")
               && description_textarea_add.getText().equals(""))
            {
            alert.setHeaderText("Erreur Ajout");
            alert.setContentText("Un ou plusieurs champs sont vides !");
            alert.showAndWait();               
            }
            else{
               titre = titre_textfield_add.getText();
               type_ev = type_event_add.getSelectionModel().getSelectedItem();
               type_res = type_reservation_add.getSelectionModel().getSelectedItem();
               LocalTime dateTime = LocalTime.parse(heure_textfield_add.getText(), formatter);
               LocalDateTime t = LocalDateTime.of(date_picker_grid_add.getValue(), dateTime);
               date_ev = t;
               duree = duree_textfield_add.getText();
               localisation = localisation_textfield_add.getText();
               Quota = Integer.parseInt(nb_places_textfield_add.getText());
               description = description_textarea_add.getText();
               Evenement event_add;
               if (type_res.equals("Payante")){
               prix = Integer.parseInt(prix_textfield_add.getText());
               
               
                event_add = new Evenement(Session.getIdThisUser(), titre, Evenement.TypeEvent.valueOf(type_ev),
                             Evenement.Type_Reservation.valueOf(type_res),
                             date_ev,duree ,localisation, Quota, description,
                             afficheUp.get(), Evenement.EtatEvent.Non,prix);
               }
               else {
                   event_add = new Evenement(
                             Session.getIdThisUser(), titre, Evenement.TypeEvent.valueOf(type_ev),
                             Evenement.Type_Reservation.valueOf(type_res),
                             date_ev,duree ,localisation, Quota, description,
                             afficheUp.get(), Evenement.EtatEvent.Non);
                   
               }
               ServicesEvenement sev = new ServicesEvenement();
               try {
                         sev.AjouterEvenementUser(event_add, Session.getIdThisUser());
                         alert.setHeaderText("Succès");
                         alert.setContentText("Ajout réussi");
                         alert.showAndWait();
                    } catch (SQLException ex) {
                        Logger.getLogger(Mes_EvenementController.class.getName()).log(Level.SEVERE, null, ex);

               
            }
            }
        }   
               else {
                       alert.setHeaderText("Echec");
                         alert.setContentText("Merci de corriger les erreurs affichés");
                         alert.showAndWait();
                       }       
        
        
        
       
    }

    @FXML
    private void Upload_Affiche(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("erreur");
        
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        UplodedAffiche = fc.showOpenDialog(null);  
        if (UplodedAffiche != null) {
            afficheUp.setValue(UplodedAffiche.getName());
            Upload u = new Upload();

                    u.upload(UplodedAffiche);
                    StringProperty upload = new SimpleStringProperty(UplodedAffiche.getName());
                    afficheUp.bind(upload);

        }

        else {
            alert.setHeaderText("erreur upload");
            alert.setContentText("veuillez uploader un ficher de type image !");
            alert.showAndWait();
        }
        
        
    }

    @FXML
    private void DetailsEvents(MouseEvent eve) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        EventBeans event = Mes_Events_TreeTable.getSelectionModel().getSelectedItem().getValue();
                 LocalDateTime DateEv = LocalDateTime.parse(event.getDate_Event_String());
                 LocalTime time = DateEv.toLocalTime();
                 date_picker_grid.setValue(DateEv.toLocalDate());
                 heure_textfield.setText(time.format(formatter));
                 duree_textfield.setText(event.getDuree_String());
                 System.out.println(event.getDuree());
                 localisation_textfield.setText(event.getLieu_String());
                 nb_places_textfield.setText(event.getNombre_String());
                 description_textarea.setText(event.getDescription_String());
                 affiche_img.setImage(new Image("http://localhost/piweb-master/web/affiches/"+event.getAffiche()));
                 
                if (!event.getPrix_String().equals("") && event.getPrix_String() != null && !event.getPrix_String().equals("0")){
                    prix_grid.setVisible(true);
                    prix_textfield.setVisible(true);
                    prix_textfield.setText(event.getPrix_String());
                }
                if (event.getEtat_String().equals("Non")){
                    btn_modifier.setVisible(true);
                    btn_supprimer.setVisible(true);
                    btn_archiver.setVisible(false);
                    archiver_dis.setVisible(false);
                    modifier_dis.setVisible(false);
                    supprimer_dis.setVisible(false);
                }
                else if (event.getEtat_String().equals("Oui")){
                    btn_modifier.setVisible(false);
                    btn_supprimer.setVisible(false);
                    btn_archiver.setVisible(true);
                    archiver_dis.setVisible(false);
                    modifier_dis.setVisible(true);
                    supprimer_dis.setVisible(true); 
                }
                else {
                    btn_modifier.setVisible(false);
                    btn_supprimer.setVisible(false);
                    btn_archiver.setVisible(true);
                    archiver_dis.setVisible(false);
                    modifier_dis.setVisible(true);
                    supprimer_dis.setVisible(true);
                }
                 
                 
    }

    @FXML
    private void DetalsEventExited(MouseEvent eve) {
        //Mes_Events_TreeTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void Supprimer_Event(ActionEvent eve) throws SQLException {
        EventBeans event = Mes_Events_TreeTable.getSelectionModel().getSelectedItem().getValue();
        ServicesEvenement sev = new ServicesEvenement();
        if (event != null){
            Evenement e = new Evenement();
            e.setID(Integer.valueOf(event.getID_String()));
            if (btn_supprimer.isVisible()){
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppresion");
                            alert.setHeaderText("Suppression");
                            alert.setContentText("Voulez-vous supprimer l'évenement "+event.getNom_String()+" ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                  try {
                            sev.SupprimerEvenement(e);
                                      } catch (SQLException ex) {
                                   Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                            }
                               else {
                                 alert.close();
                                    }
            }
        }
    }

    @FXML
    private void Modifier_Event(ActionEvent eve) {
        EventBeans event = Mes_Events_TreeTable.getSelectionModel().getSelectedItem().getValue();
        ServicesEvenement sev = new ServicesEvenement();
        if (event != null && btn_supprimer.isVisible() && !label_edit_error.isVisible() && date_picker_grid != null
                && !heure_textfield.getText().equals("") && !duree_textfield.getText().equals("")
                && !localisation_textfield.getText().equals("") && !nb_places_textfield.getText().equals("")
                && !description_textarea.getText().equals(""))
            {
            Evenement e;    
            LocalTime dateTime = LocalTime.parse(heure_textfield.getText(), formatter);
            LocalDateTime t = LocalDateTime.of(date_picker_grid.getValue(), dateTime);
            String aff = afficheUp.get().equals("") ? event.getAffiche_String(): afficheUp.get();
                
                if (!event.getType_Reservation_String().equals("Payante")){
            
                    e = new Evenement(Integer.valueOf(event.getID_String()),Integer.valueOf(event.getID_Organisteur_String()),
                    event.getNom_String(), Evenement.TypeEvent.valueOf(event.getType_String()),
                    Evenement.Type_Reservation.valueOf(event.getType_Reservation_String()),t,
                    duree_textfield.getText(),localisation_textfield.getText(),Integer.valueOf(nb_places_textfield.getText()),
                    description_textarea.getText(), aff,Evenement.EtatEvent.valueOf(event.getEtat_String()) );
                }
                else {
                    System.out.println("oui");
                    e = new Evenement(Integer.valueOf(event.getID_String()),Integer.valueOf(event.getID_Organisteur_String()),
                    event.getNom_String(), Evenement.TypeEvent.valueOf(event.getType_String()),
                    Evenement.Type_Reservation.valueOf(event.getType_Reservation_String()),t,
                    duree_textfield.getText(),localisation_textfield.getText(),Integer.valueOf(nb_places_textfield.getText()),
                    description_textarea.getText(), aff,Evenement.EtatEvent.valueOf(event.getEtat_String()),Integer.valueOf(prix_textfield.getText()));
                }
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de modification");
                            alert.setHeaderText("Modification");
                            alert.setContentText("Voulez-vous modifier l'évenement "+event.getNom_String()+" ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                  try {
                            sev.ModifierEvenementUser(e);
                                      } catch (SQLException ex) {
                                   Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                      } 
                            }
                               else {
                                 alert.close();
                                    }
            }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setHeaderText("Echec");
            alert.setContentText("Merci de corriger les erreurs affichés");
            alert.showAndWait();
        }
        }
    

    @FXML
    private void Archiver_Event(ActionEvent eve) {
        EventBeans event = Mes_Events_TreeTable.getSelectionModel().getSelectedItem().getValue();
        ServicesEvenement sev = new ServicesEvenement();
        if (event != null && event.getEtat_String().equals("Oui")){
            Evenement e = new Evenement();
            e.setID(Integer.valueOf(event.getID_String()));
            e.setID_Organisateur(Session.getIdThisUser());
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppresion");
                            alert.setHeaderText("Suppression");
                            alert.setContentText("Voulez-vous supprimer l'évenement "+event.getNom_String()+" ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                  try {
                            sev.ArchiverEvenement(e, Session.getIdThisUser());
                                      } catch (SQLException ex) {
                                   Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                            }
                               else {
                                 alert.close();
                                    }
            }
        
        
    }

    @FXML
    private void Import_Edit_Supp(ActionEvent eve) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("erreur");
        
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        UplodedAffiche = fc.showOpenDialog(null);  
        if (UplodedAffiche != null) {
            afficheUp.setValue(UplodedAffiche.getName());
            Upload u = new Upload();

                    u.upload(UplodedAffiche);
                    StringProperty upload = new SimpleStringProperty(UplodedAffiche.getName());
                    afficheUp.bind(upload);

        }

        else {
            alert.setHeaderText("erreur upload");
            alert.setContentText("veuillez uploader un ficher de type image !");
            alert.showAndWait();
        }
    }

    @FXML
    private void Init_Buttons(MouseEvent eve) {
        ReservationBeans res = mes_res_tree.getSelectionModel().getSelectedItem().getValue();
        details_stackpane_home.setVisible(true);
        Evenement event = new Evenement();
        if (!res.getEvent().getType_Reservation_String().equals("Payante")){
            
                    event = new Evenement(Integer.valueOf(res.getEvent().getID_String()),Integer.valueOf(res.getEvent().getID_Organisteur_String()),
                    res.getEvent().getNom_String(), Evenement.TypeEvent.valueOf(res.getEvent().getType_String()),
                    Evenement.Type_Reservation.valueOf(res.getEvent().getType_Reservation_String()),
                    LocalDateTime.parse(res.getEvent().getDate_Event_String()),
                    res.getEvent().getDuree_String(),res.getEvent().getLieu_String(),Integer.valueOf(res.getEvent().getNombre_String()),
                    res.getEvent().getDescription_String(), res.getEvent().getAffiche_String(),Evenement.EtatEvent.valueOf(res.getEvent().getEtat_String()));
                }
                else {
                    System.out.println("oui");
                    event = new Evenement(Integer.valueOf(res.getEvent().getID_String()),Integer.valueOf(res.getEvent().getID_Organisteur_String()),
                    res.getEvent().getNom_String(), Evenement.TypeEvent.valueOf(res.getEvent().getType_String()),
                    Evenement.Type_Reservation.valueOf(res.getEvent().getType_Reservation_String()),LocalDateTime.parse(res.getEvent().getDate_Event_String()),
                    res.getEvent().getDuree_String(),res.getEvent().getLieu_String(),Integer.valueOf(res.getEvent().getNombre_String()),
                    res.getEvent().getDescription_String(), res.getEvent().getAffiche_String(),Evenement.EtatEvent.valueOf(res.getEvent().getEtat_String()),
                    Integer.valueOf(res.getEvent().getPrix_String()));
                }
        if (res.Etat.get().equals("Confirmé")){
            reconfirmerRes_dis.setVisible(true);
            btn_reconfirmer.setVisible(false);
            annulerRes_dis.setVisible(false);
            btn_annulerRes.setVisible(true);
        }
        if (res.Etat.get().equals("Annulé")){
            reconfirmerRes_dis.setVisible(false);
            btn_reconfirmer.setVisible(true);
            annulerRes_dis.setVisible(true);
            btn_annulerRes.setVisible(false);
        }
        
        // Show details
        
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
                
                JFXDialog DialogDetails = new JFXDialog(details_stackpane_home,details_dialog_home, JFXDialog.DialogTransition.CENTER);
                Btn_non.setOnAction((ActionEvent retour) ->{
                    DialogDetails.close();
                    details_event_home.setVisible(true);
                    details_stackpane_home.setVisible(false);
                        });
                
                DialogDetails.show();
                details_dialog_home.setActions(Btn_non);
           
        
    }

    @FXML
    private void Reconfirmer_Reservation(ActionEvent event) {
        ServicesReservation sres = new ServicesReservation();
        ReservationBeans res = mes_res_tree.getSelectionModel().getSelectedItem().getValue();
        Reservation r = new Reservation();
        r.setID_Participant(Integer.valueOf(res.getID_Participant().get()));
        int id = Integer.valueOf(res.getEvent().getID().get());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Reconfirmation de réservation");
                            alert.setHeaderText("Réserver");
                            alert.setContentText("Voulez-vous reconfimer votre réservation pour l'évenement "
                                    +res.getEvent().getNom_String()+" ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                  try {
                            sres.Reconfirmer(r, id);
                                      } catch (SQLException ex) {
                                   Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                            }
                               else {
                                 alert.close();
                                    }
        
        
    }

    @FXML
    private void Annuler_Reservation(ActionEvent event) {
        ServicesReservation sres = new ServicesReservation();
        ReservationBeans res = mes_res_tree.getSelectionModel().getSelectedItem().getValue();
        Reservation r = new Reservation();
        r.setID_Participant(Integer.valueOf(res.getID_Participant().get()));
        int id = Integer.valueOf(res.getEvent().getID().get());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Annulation de réservation");
                            alert.setHeaderText("Réserver");
                            alert.setContentText("Voulez-vous annuler votre réservation pour l'évenement "
                                    +res.getEvent().getNom_String()+" ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                  try {
                            sres.AnnulerReservation(r, id);
                                      } catch (SQLException ex) {
                                   Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                            }
                               else {
                                 alert.close();
                                    }
    }
    
        @FXML
    void Noter_Evenement(ActionEvent event) {

    }
    

}
          
         
         
         
        
        
        
        

    



  
    
    

 


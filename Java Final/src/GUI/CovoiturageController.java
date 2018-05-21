/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Adresse_cov;
import Entities.Covoiturage;
import Entities.Localisation;
import Entities.Session;
import Entities.User;
import Services.ServicesAdrCov;
import Services.ServicesCovoiturage;
import Services.ServicesUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class CovoiturageController implements Initializable {
     private final  ServicesCovoiturage SC= new ServicesCovoiturage();   
     private final ServicesAdrCov SA= new ServicesAdrCov();
     private final ServicesUser SU = new ServicesUser();
     private  List<Covoiturage> Covoiturage = SC.getCovoiturage();
    
     private  List<Adresse_cov> AdressesCov= SA.getAdresses();
     private List<String> NomsAdrCov=AdressesCov.stream().map(a->a.getNom()).collect(Collectors.toList());
     private Covoiturage selectedA=null;
     private Localisation selectedL_Dep;
     private Localisation selectedL_Dest;
     GoogleMap   gmap;
     private int controlFields=0;
    
     int loc_type=0;
    @FXML
     final ToggleGroup group = new ToggleGroup();
     /*-----------
               ----------------------
                                ------------------------------
     */
     
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXDrawer drawer1;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXTextField depart_text;
    @FXML
    private JFXTextField dest_text;
   
    @FXML
    private JFXComboBox<Label> type_text;

    @FXML
    private JFXDatePicker date_text;
    @FXML
    private JFXButton btn_update;
    @FXML
    private JFXButton btn_add;
    
    @FXML
    private Pane googlemap_pane;
    
   
    @FXML
    private JFXTextField search_text;
    @FXML
    private Tab offres_tab;
    @FXML
    private Tab demandes_tab;
    @FXML
    private Tab mesannonces_tab;
    @FXML
    private JFXTabPane typeannonce_TP;
    @FXML
    private JFXButton home;
     @FXML
    private ScrollPane Ann_Scroll;
    @FXML
    private ImageView dep_marker;
    @FXML
    private ImageView dest_marker;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Pane sortby_pane;
    @FXML
    private JFXRadioButton dateRadio;
    @FXML
    private JFXRadioButton NomRadio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedL_Dep= new Localisation();
        selectedL_Dest = new Localisation();
        search_text.toFront();
        gmap= new GoogleMap();
         googlemap_pane.getChildren().add(gmap.getwebview());
         TextFields.bindAutoCompletion(depart_text,NomsAdrCov);
         TextFields.bindAutoCompletion(dest_text,NomsAdrCov);
         type_text.getItems().add(new Label("Offre"));
         type_text.getItems().add(new Label("Demande"));
      
        drawer1.setSidePane(scrollPane);
        
             CovoituragePanel();    
     drawer1.open();    
     sortby_pane.toFront();
    }    

  public void controlTabPane(){
       List<Covoiturage> annonces= new ArrayList<>();
     if( typeannonce_TP.getSelectionModel().getSelectedItem().equals(offres_tab)){
        annonces=Covoiturage.stream().filter(a->a.getType()==0).collect(Collectors.toList());
        AnchorPane.getChildren().clear();
         DisplayCovoiturage(annonces);
     }else if( typeannonce_TP.getSelectionModel().getSelectedItem().equals(demandes_tab)) {
          annonces=Covoiturage.stream().filter(a->a.getType()==1).collect(Collectors.toList());
          AnchorPane.getChildren().clear();
         DisplayCovoiturage(annonces);
     } else if( typeannonce_TP.getSelectionModel().getSelectedItem().equals(mesannonces_tab)) {
          annonces=Covoiturage.stream().filter(a->a.getId_u()==Session.getIdThisUser()).collect(Collectors.toList());
          AnchorPane.getChildren().clear();
         DisplayCovoiturage(annonces);
     }      
  }
    
   
    private void CovoituragePanel()  {
        AnchorPane.setVisible(true);
        Covoiturage= SC.getCovoiturage();
        DisplayCovoiturage(Covoiturage);
        typeannonce_TP.toFront();
        
    }
    
    private void DisplayCovoiturage(List<Covoiturage> annonces){
       AnchorPane.getChildren().clear();
       int taille=annonces.size(); 
       Pane AddP= new Pane();
        for(int i=0;i<taille;i++){
            Pane currentAP= new Pane();
            User currentuser = SU.getUserById(annonces.get(i).getId_u());
       
                Label Depart =new Label();
                Label Destination = new Label();
                Label NomUser =new Label(currentuser.getUsername());
                System.out.println(NomUser.getText());
           JFXButton button = new JFXButton();
           JFXButton btn_del = new JFXButton();
           ImageView dep_icon=new ImageView();
           ImageView dest_icon=new ImageView();
           
           ImageView photo= new ImageView(
                   new Image("file:\\\\\\C:\\wamp64\\www\\Files\\"+currentuser.getPhoto()
                           , 100, 100, true, true));
           try {
            dep_icon=new ImageView(new Image(getClass().getResource("/Resources/dep_icon.png").toURI().toString(),20,20,true,true));
               dest_icon=new ImageView(new Image(getClass().getResource("/Resources/dest_icon.png").toURI().toString(),20,20,true,true));
           } catch (URISyntaxException ex) {
               Logger.getLogger(CovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
    EventHandler Hover = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        
                        ScaleTransition st=new ScaleTransition(Duration.millis(50), currentAP);
                        st.setToX(1.1);
        
        st.setToY(1.1);
        st.play();
                    }
                };
    EventHandler Revoh = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                       
                    ScaleTransition st=new ScaleTransition(Duration.millis(100), currentAP);
                        st.setToX(1);
        
        st.setToY(1);
        st.play();
                    }
                };
    EventHandler Clicked = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        selectedA=annonces.stream()
                                .filter(a->a.getId()==Integer.parseInt(currentAP.getId()))
                                .findFirst().get();
                        displayAnnonce();
                        if(Session.getIdThisUser()==currentuser.getId()){
       
        depart_text.setEditable(true);
        dest_text.setEditable(true);
        date_text.setEditable(true);
        btn_update.setVisible(true);
     }else {
         
        depart_text.setEditable(false);
        dest_text.setEditable(false);
        date_text.setEditable(false);
        btn_update.setVisible(false);
     }
                         
                    }
                };
    EventHandler Hover_del = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        
                        btn_del.setOpacity(1);
                    }
                };
    EventHandler Revoh_del = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        
                        btn_del.setOpacity(0.7);
                    }
                };
    EventHandler Clicked_del = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        Covoiturage A=annonces.stream()
                                .filter(a->a.getId()==Integer.parseInt(currentAP.getId()))
                                .findFirst().get();
                        SC.deleteAnnonce(A);
                      AnchorPane.getChildren().clear();
                      Covoiturage.remove(A);
                       annonces.remove(A);
                        DisplayCovoiturage(annonces);
     
                    }
                };
    photo.setLayoutX(70);
    photo.setLayoutY(10);
    //icons
    dep_icon.setLayoutX(39);
    dep_icon.setLayoutY(130);
    dest_icon.setLayoutX(39);
    dest_icon.setLayoutY(149);
    //labels
    Depart.setLayoutX(69);
    Depart.setLayoutY(130);
    Depart.setPrefSize(90, 18);
    Depart.setText("From "+((Covoiturage)annonces.get(i)).getDepart());
    Destination.setLayoutX(69);
    Destination.setLayoutY(149);
    Destination.setPrefSize(90, 18);  
    Destination.setText("To "+((Covoiturage)annonces.get(i)).getDestination()); 
    NomUser.setLayoutX(65);
    NomUser.setLayoutY(110);
    NomUser.setPrefSize(100, 18);
    NomUser.setFont(new Font(18));
   
    //button
    button.setOnAction(Clicked);
    button.setLayoutX(0);
    button.setLayoutY(0);
    button.setPrefSize(220, 175);
    button.setFocusTraversable(false);
    if(Session.getIdThisUser()==currentuser.getId()){
     button.setStyle("-fx-background-color:#CFFAD1;-fx-border-color:#F43955");
    }else {
        button.setStyle("-fx-background-color:white;-fx-border-color:#F43955");
    }
    //delete button
    btn_del.setPrefSize(20,20);
    btn_del.setLayoutX(180);
    btn_del.setLayoutY(0);
    btn_del.setText("X");
    btn_del.setOpacity(0.7);
    btn_del.setStyle("-fx-background-color:#0B0B0B");
    btn_del.setOnMouseEntered(Hover_del);
    btn_del.setOnMouseExited(Revoh_del);
    btn_del.setOnAction(Clicked_del);
    //pane
    currentAP.setLayoutX((i%2)*230+20); 
    currentAP.setLayoutY((i/2)*195+20);
    currentAP.setPrefSize(220, 175);
    currentAP.setStyle("-fx-background-color:white;-fx-border-color:#F43955"); 
   
    if(annonces.get(i).getEtat()==0){
        currentAP.setOpacity(0.7);
        currentAP.setStyle("-fx-background-color:white;-fx-border-color:#B9B4B4");
    }
    
    currentAP.setOnMouseEntered(Hover);
    currentAP.setOnMouseExited(Revoh);
    currentAP.setVisible(true);
    currentAP.setId(String.valueOf(((Covoiturage)annonces.get(i)).getId()));
    
     
    //add components to pane
    currentAP.getChildren().add(button);
    currentAP.getChildren().add(Depart);
    currentAP.getChildren().add(Destination);
    currentAP.getChildren().add(NomUser);
    currentAP.getChildren().add(btn_del);
    currentAP.getChildren().add(dep_icon);
    currentAP.getChildren().add(dest_icon);
    currentAP.getChildren().add(photo);
     //add pane to Anchorpane       
      AnchorPane.getChildren().add(currentAP); 
        }
        DisplayAddPane(AddP, annonces.size());
        
    }
    public void DisplayAddPane(Pane AddP,int taille){
         ImageView add_img=new ImageView();
        
        JFXButton button = new JFXButton();
       
            
         try {
             add_img = new ImageView(new Image(getClass().getResource("/Resources/add_red.png").toURI().toString(),100,100,true,true));
         } catch (URISyntaxException ex) {
             Logger.getLogger(CovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
         }
       add_img.setLayoutX(60);
       add_img.setLayoutY(40);
       add_img.setOpacity(0.5f);
           
         
        EventHandler Hover = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                       AddP.setOpacity(1);
                        ScaleTransition st=new ScaleTransition(Duration.millis(50),AddP);
                        st.setToX(1.1);
        
        st.setToY(1.1);
        st.play();
        
                    }
                };
    EventHandler Revoh = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        AddP.setOpacity(0.5f);
                    ScaleTransition st=new ScaleTransition(Duration.millis(100),AddP);
                        st.setToX(1);
        
        st.setToY(1);
        st.play();
                    }
                };
    EventHandler Clicked = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        selectedA=new Covoiturage();
                       displayVAnnonce();
                    }
                };
        button.setOnAction(Clicked);
    button.setLayoutX(0);
    button.setLayoutY(0);
    button.setPrefSize(220, 175);
    button.setStyle("-fx-border-color:#F43955");
    AddP.setLayoutX((taille%2)*230+20); 
    AddP.setLayoutY((taille/2)*195+20);
    AddP.setPrefSize(220, 175);
    AddP.setStyle("-fx-background-color:white");  
    AddP.setOpacity(0.5f);
    AddP.setOnMouseEntered(Hover);
    AddP.setOnMouseExited(Revoh);
    AddP.setVisible(true);
    AddP.getChildren().add(add_img);
    AddP.getChildren().add(button);
           
    AnchorPane.getChildren().add(AddP);
    }
    public FadeTransition FadeIn(Node N,int delay){
    FadeTransition ft = new FadeTransition(Duration.millis(500+delay), N);
ft.setFromValue(0);
ft.setToValue(1.0);

return ft;
}
    public TranslateTransition RollIn(Node N,double from,double to,int delay){
        TranslateTransition tt= new TranslateTransition(Duration.millis(500+delay), N);
        tt.setFromX(from);
tt.setToX(to);
return tt;
    }
    public ScaleTransition Popup(Node N, double d,double delay) {
        ScaleTransition st= new ScaleTransition(Duration.seconds(500+delay),N);
        st.setByX(d);
        
        return st;
    }

   
    public void inittextfield(String s,TextField TF){
        
    ChangeListener textchanged = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            
         if(!TF.getText().equals(s))
                btn_update.setDisable(false);
         else  
             btn_update.setDisable(true);
            
         controlfields();
        }
    };
    
    TF.textProperty().addListener(textchanged);
    
}
  public void initDatePicker(LocalDate date,DatePicker DP){
       
    ChangeListener datechanged = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
         if(!DP.getValue().equals(date) && !DP.getValue().isBefore(LocalDate.now()) )
                 btn_update.setDisable(false);
          else 
             btn_update.setDisable(true);
            
          controlfields();
        }
    };
    DP.valueProperty().addListener(datechanged);
}  
  public void initComboBox(int s, ComboBox CB){
   ChangeListener textchanged = new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
          if(CB.getSelectionModel().getSelectedIndex()==s)
                 btn_update.setDisable(false);
          else  btn_update.setDisable(true);
          
        }
    };   
  }
  public void controlfields(){
     if("".equals(depart_text.getText()) || "".equals(dest_text.getText()) 
             || depart_text.getText().equals(dest_text.getText()) 
             || date_text.getValue().isBefore(LocalDate.now()))
         btn_add.setDisable(true);
     else btn_add.setDisable(false);
     if(NomsAdrCov.contains(depart_text.getText())) {
         Double lat= AdressesCov.stream().filter(a->a.getNom().equals(depart_text.getText()))
            .mapToDouble(a->a.getLat()).findFirst().getAsDouble();
         Double lng= AdressesCov.stream().filter(a->a.getNom().equals(depart_text.getText()))
            .mapToDouble(a->a.getLng()).findFirst().getAsDouble();
     selectedL_Dep= new Localisation( lat, lng);
   loc_type=1;
     }
    if(NomsAdrCov.contains(dest_text.getText())) {
         Double lat= AdressesCov.stream().filter(a->a.getNom().equals(dest_text.getText()))
            .mapToDouble(a->a.getLat()).findFirst().getAsDouble();
         Double lng= AdressesCov.stream().filter(a->a.getNom().equals(dest_text.getText()))
            .mapToDouble(a->a.getLng()).findFirst().getAsDouble();
     selectedL_Dest= new Localisation( lat, lng);
   loc_type=0;
     }
     googlemap_action();
  }
 public void displayAnnonce(){
       Ann_Scroll.setVisible(true);
      ParallelTransition pt = new ParallelTransition(
                FadeIn(depart_text,0),RollIn(depart_text, 0, 100,0),
              FadeIn(dep_marker,150),RollIn(dep_marker, 0, 60,150),
                FadeIn(dest_text,300),RollIn(dest_text, 0, 100,300),
            FadeIn(dest_marker,450),RollIn(dest_marker, 0, 60,450),
             FadeIn(type_text, 600),RollIn(type_text, 0, 100,600),  
                FadeIn(date_text,900),RollIn(date_text, 142, 200,900),
             
            RollIn(btn_update, 150, 225,1500)
               
                 
        );
        pt.play();
     initFields(selectedA);
     btn_add.setVisible(false);
     btn_update.setVisible(true);
    
     btn_update.setDisable(true);
if(NomsAdrCov.contains(depart_text.getText())) {
         Double lat= AdressesCov.stream().filter(a->a.getNom().equals(depart_text.getText()))
            .mapToDouble(a->a.getLat()).findFirst().getAsDouble();
         Double lng= AdressesCov.stream().filter(a->a.getNom().equals(depart_text.getText()))
            .mapToDouble(a->a.getLng()).findFirst().getAsDouble();
     selectedL_Dep= new Localisation( lat, lng);
     
     }else selectedL_Dep=new Localisation();
    if(NomsAdrCov.contains(dest_text.getText())) {
         Double lat= AdressesCov.stream().filter(a->a.getNom().equals(dest_text.getText()))
            .mapToDouble(a->a.getLat()).findFirst().getAsDouble();
         Double lng= AdressesCov.stream().filter(a->a.getNom().equals(dest_text.getText()))
            .mapToDouble(a->a.getLng()).findFirst().getAsDouble();
     selectedL_Dest= new Localisation( lat, lng);
    
     }else selectedL_Dest=new Localisation();
     gmap.setdepMarkerPosition(selectedL_Dep.getlat(),selectedL_Dep.getlng());
     gmap.setdestMarkerPosition(selectedL_Dest.getlat(),selectedL_Dest.getlng());  
    
     
   
 }  
 public void displayVAnnonce(){
     Ann_Scroll.setVisible(true);
     btn_update.setVisible(false);
     btn_add.setVisible(true);
     btn_add.setDisable(true);
     initFields(selectedA);
selectedL_Dep= new Localisation();
selectedL_Dest= new Localisation();

      
        date_text.setValue(LocalDate.now());
     ParallelTransition pt = new ParallelTransition(
               FadeIn(depart_text,0),RollIn(depart_text, 0, 100,0),
              FadeIn(dep_marker,150),RollIn(dep_marker, 0, 60,150),
                FadeIn(dest_text,300),RollIn(dest_text, 0, 100,300),
            FadeIn(dest_marker,450),RollIn(dest_marker, 0, 60,450),
             FadeIn(type_text, 600),RollIn(type_text, 0, 100,600),  
                FadeIn(date_text,900),RollIn(date_text, 142, 200,900),
            
             RollIn(btn_add, 150, 225,1800)
                 
        );
        pt.play(); 
   gmap.initialize();
   googlemap_action();
        
 }
 public void initFields(Covoiturage A){
    
     depart_text.setText(A.getDepart());
      dest_text.setText(A.getDestination());
     
        date_text.setValue(A.getDate().toLocalDate());
        if(A.getType()==0){
         type_text.getSelectionModel().selectFirst();
         }else type_text.getSelectionModel().selectLast();
             inittextfield(A.getDepart(),depart_text);
      inittextfield(A.getDestination(),dest_text);
     
       initDatePicker(A.getDate().toLocalDate(),date_text);
        initComboBox(A.getType(), type_text);
 }

    @FXML
    private void Update_Action(ActionEvent event) {
    
      String depart=depart_text.getText();
      String dest=dest_text.getText();
      Date date = Date.valueOf(date_text.getValue());
      int type=(type_text.getValue().getText().equals("offre")) ? 0:1;
      
        Covoiturage A= new Covoiturage(selectedA.getId(),1,type,depart,dest,date,true);
        SC.modifyAnnonce(A);
        
    }

    @FXML
    private void Add_Action(ActionEvent event) {
       
      String depart=depart_text.getText();
      String dest=dest_text.getText();
      Date date = Date.valueOf(date_text.getValue());
     
        int type = type_text.getSelectionModel().getSelectedIndex();
        Covoiturage A= new Covoiturage(0,Session.getIdThisUser(),1,depart,dest,date,true);
        SC.insertAnnonce(A);
        AnchorPane.getChildren().clear();
        CovoituragePanel();
        selectedA= Covoiturage.get(Covoiturage.size()-1);
      
        if(selectedL_Dest!=null) {
            
            Adresse_cov Adrdep= new Adresse_cov(selectedA.getDepart(), selectedL_Dep.getlat(), selectedL_Dep.getlng());
           Adresse_cov Adrdest= new Adresse_cov(selectedA.getDestination(), selectedL_Dest.getlat(), selectedL_Dest.getlng());
           if(!NomsAdrCov.contains(depart_text.getText()))
           SA.AjouterAdresse(Adrdep);
           if(!NomsAdrCov.contains(dest_text.getText()))
           SA.AjouterAdresse(Adrdest);

        }
       
        AdressesCov=SA.getAdresses();
        NomsAdrCov=AdressesCov.stream().map(a->a.getNom()).collect(Collectors.toList());
        TextFields.bindAutoCompletion(depart_text,NomsAdrCov);
         TextFields.bindAutoCompletion(dest_text,NomsAdrCov);
        
    }


    private void googlemap_action() {   
        if("".equals(selectedA.getDepart())){
            
            gmap.activateListener();
            EventHandler value = new EventHandler() {
                @Override
                public void handle(Event event) {
                    if(loc_type==0 && !NomsAdrCov.contains(depart_text.getText())){
                        selectedL_Dep= new Localisation(gmap.currentlat,gmap.currentlng); 
                        gmap.setdepMarkerPosition(selectedL_Dep.getlat(),selectedL_Dep.getlng());
                        if(!NomsAdrCov.contains(dest_text.getText()))
                        loc_type=1;
                    }
                 
                   else if(loc_type==1 && !NomsAdrCov.contains(dest_text.getText())) {
                        selectedL_Dest= new Localisation(gmap.currentlat,gmap.currentlng);
                        gmap.setdestMarkerPosition(selectedL_Dest.getlat(),selectedL_Dest.getlng());  
                        if(!NomsAdrCov.contains(depart_text.getText()))
                        loc_type=0;
                    }
                }
            };
            googlemap_pane.setOnMouseClicked(value);
        }
        
        gmap.setdepMarkerPosition(selectedL_Dep.getlat(),selectedL_Dep.getlng());
        gmap.setdestMarkerPosition(selectedL_Dest.getlat(),selectedL_Dest.getlng());         
    }

   

    @FXML
    private void search_changed(KeyEvent event) {
     Set<Covoiturage> A= Covoiturage.stream()
                .filter(a->a.getDepart().toUpperCase().startsWith(search_text.getText().toUpperCase())
                || a.getDestination().toUpperCase().startsWith(search_text.getText().toUpperCase())
                )
                .collect(Collectors.toSet());
     List<Covoiturage> cov_user= SC.getCovoiturageByUser(search_text.getText());
     if(cov_user.size()>0)
         A.addAll(cov_user);
     
        if(!"".equals(search_text.getText())){
        AnchorPane.getChildren().clear();
         DisplayCovoiturage(A.stream().collect(Collectors.toList()));
       }else {
          AnchorPane.getChildren().clear();
         DisplayCovoiturage(Covoiturage);
     }
        
    }

    @FXML
    private void tabpane_click(MouseEvent event) {
        controlTabPane();
    }

    @FXML
    private void bnt_acceuil(ActionEvent event) {
        Accueil.changescenes("Accueil.fxml");
    }

    @FXML
    private void SortByDate(ActionEvent event) {
        List<Covoiturage> sortedlist= Covoiturage.stream()
                .sorted((c1,c2)->-c1.getDate().compareTo(c2.getDate()))
                .collect(Collectors.toList());
        DisplayCovoiturage(sortedlist);
    }

    @FXML
    private void SortByNom(ActionEvent event) {
         List<Covoiturage> sortedlist=SC.getCovoiturageSortedbyusername();
         DisplayCovoiturage(sortedlist);
    }
  
}
 


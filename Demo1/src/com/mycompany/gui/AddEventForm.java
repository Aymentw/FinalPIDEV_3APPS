package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompagny.Service.ServicesEvenement;
import com.mycompagny.Service.ServicesUser;
import com.mycompany.Entite.Evenement;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * @author Mechlaoui
 */
public class AddEventForm extends BaseForm {

    private static String i;

    public AddEventForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Esprit Entr'Aide");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        Form previous = Display.getInstance().getCurrent();
        tb.addCommandToRightBar(null, res.getImage("backmenu.png"), e -> previous.showBack());
        Image img = res.getImage("back-logo.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label pp = new Label(ServicesUser.UrlImage(SessionManager.getPhoto()), "PictureWhiteBackgrond");
        
       //Image pap = res.getImage("profile-pic.jpg");
       //pp.setIcon(pap);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        pp)
                        )
                )
        ));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton elements = RadioButton.createToggle("Proposez votre événement", barGroup);
        elements.setUIID("SelectBar");
        
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, elements)
        ));
        
        TextField nomEvent = new TextField("");
        nomEvent.setUIID("LabelIcon");
        addStringValue("Titre", nomEvent);
        nomEvent.addActionListener((eeee)->{
            System.out.println(nomEvent.getText());
        });
        
        Picker typeEvent = new Picker();
        typeEvent.setType(Display.PICKER_TYPE_STRINGS);
        typeEvent.setStrings("Associatif", "Culturel","Autres");
        addStringValue("Type", typeEvent);
        
        Picker typeResEvent = new Picker();
        typeResEvent.setType(Display.PICKER_TYPE_STRINGS);
        typeResEvent.setStrings("Payante", "Gratuite");
        addStringValue("Type Reservation", typeResEvent);
        
        TextField PrixEvent = new TextField("");
        PrixEvent.setUIID("LabelIcon");
        Container Prix = addStringValuePrix("Prix", PrixEvent);
        //add(Prix).setVisible(false);
        
        typeResEvent.addActionListener((pick) -> {
            if(typeResEvent.getSelectedString() !=null){
        if(typeResEvent.getSelectedString().equals("Payante")){
        
        addComponent(7,Prix);
        Prix.setVisible(true);
        refreshTheme();
        }
        else{
            Prix.setVisible(false);
            removeComponent(Prix);
            refreshTheme(); 
        }
        }});

        Picker dateEvent = new Picker();
        dateEvent.setDate(new Date());
        dateEvent.setType(Display.PICKER_TYPE_DATE);
        dateEvent.addActionListener((datecheck)-> {
            Date dd = new Date();
            if(dateEvent.getDate().getTime()*1000L < dd.getTime()*1000L){
                Dialog.show("Date invalide", "Veuillez choisir une date valide", "OK", null);
            }
        });
        addStringValue("Date", dateEvent);
        
        System.out.println(dateEvent.getValue());
        
        Picker TimeEvent = new Picker();
        TimeEvent.setType(Display.PICKER_TYPE_TIME);
        addStringValue("Heure", TimeEvent);
        
        TextField dureeEvent = new TextField("");
        dureeEvent.setUIID("LabelIcon");
        addStringValue("Durée", dureeEvent);
        
        TextField lieuEvent = new TextField("");
        lieuEvent.setUIID("LabelIcon");
        addStringValue("Localisation", lieuEvent);
        
        TextField quotaEvent = new TextField("");
        quotaEvent.setUIID("LabelIcon");
        addStringValue("Quota", quotaEvent);
        
        TextArea descEvent = new TextArea();
        descEvent.setUIID("LabelIcon");
        addStringValue("Description", descEvent);
        
        
        Button picture = new Button("Affiche");
        picture.setUIID("Update");
        addStringValue("", picture);
                
        Button ajout = new Button("Ajouter");
        ajout.setUIID("Edit");
        addStringValue("", ajout);
        
        Validator v = new Validator();
        v.addConstraint(nomEvent, new LengthConstraint(1)). 
            addConstraint(dureeEvent, new RegexConstraint("^[a-zA-Z0-9 ]+$", "Veuillez choisir une durée valide")).
            addConstraint(lieuEvent, new RegexConstraint("^[a-zA-Z0-9 ]+$", "Veuillez choisir une localisation valide")).
            addConstraint(quotaEvent, new RegexConstraint("^\\d{1,10}$","Veuillez choisir un quota valide")).
            addConstraint(descEvent, new LengthConstraint(1));
        v.addSubmitButtons(ajout);
        if(PrixEvent.isVisible()){
            v.addConstraint(PrixEvent, new RegexConstraint("^\\d{1,10}$","Veuillez choisir un prix valide"));
        }
            
        
        TextField path = new TextField("");
        
                picture.addActionListener(e -> {
            i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (i != null) {
                Image im;
                try {
                    im = Image.createImage(i);
                    im = im.scaled(res.getImage("photo-profile.jpg").getWidth(),
                            res.getImage("photo-profile.jpg").getHeight());
                    pp.setIcon(im);
                    System.out.println(i);
                    path.setText(i);

                    //System.out.println("The new image's name is : "+Session.getTmpImage());
                } catch (IOException ex) {
                    System.out.println("Could not load image!");
                }
            }
        });
                
        ajout.addActionListener((edit)-> {
            Date dateTime = dateEvent.getDate();
            String prix;
            int hours =    TimeEvent.getTime()/60;
            int minutes = TimeEvent.getTime()%60;
            String Time = minutes == 0 ? String.valueOf(hours)+":00:00" : String.valueOf(hours)+":"+String.valueOf(minutes)+":00";
            SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd");
            String java_date = jdf.format(dateTime)+" "+Time;
            System.out.println(java_date);
            if (path.getText().equals("")){
            Dialog.show("Echec", "Veuillez uploader une affiche", "Ok", null);
            }
            else{
                if(PrixEvent.getText().equals("")){
                    prix = String.valueOf(0);
                }
                else{
                    prix = PrixEvent.getText();
                }
            String quota = quotaEvent.getText();
            
            Evenement newEvent = new Evenement(SessionManager.getId(),
                                               "",
                                               Evenement.TypeEvent.valueOf(typeEvent.getSelectedString()),
                                               Evenement.Type_Reservation.valueOf(typeResEvent.getSelectedString()),
                                               new Date(),dureeEvent.getText(),lieuEvent.getText(),Integer.valueOf(quotaEvent.getText()),
                                               descEvent.getText(),path.getText(),Evenement.EtatEvent.Oui);

            ServicesEvenement.addEvent(newEvent, java_date,nomEvent.getText(),prix,quota);
            Dialog.show("Succès", "Evénement ajouté avec succès", "Ok", null);
            new EventsForm(res).show();
            refreshTheme();    
            }
            
        });
        
    } 

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
    private Container addStringValuePrix(String s, Component v) {
        Container cnt = BorderLayout.west(new Label(s, "PaddedLabel"));
                cnt.add(BorderLayout.CENTER, v);
                cnt.add(BorderLayout.SOUTH,createLineSeparator(0xeeeeee));
        return cnt;        
    }
    
}

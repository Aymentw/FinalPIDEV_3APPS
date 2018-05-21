/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServicesEvenement;
import com.mycompany.Entite.Evenement;
import java.util.ArrayList;

/**
 *
 * @author Mechlaoui
 */
public class EventsForm extends BaseForm {
    
    public EventsForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Esprit Entr'Aide");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        ArrayList<Evenement> list = ServicesEvenement.AllEvents();
                for(Evenement ev : list){
                     addTab(swipe,UrlImage(ev), ev.getDescription());
                }
        
        
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
         
        
        Component.setSameSize(radioContainer);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        Image events_img = res.getImage("calendar.png").scaledSmallerRatio(100, 100); 
        Button events_btn = new Button(events_img);
        Label events_label = new Label("Evénements");
        events_label.setUIID("TitleIcon");
        Container iconsTopLeft = GridLayout.encloseIn(2,
           events_btn,
           events_label     
                
        );
        events_btn.addActionListener((evs)-> {
            new AllEventsForm(res).show();
        });
        iconsTopLeft.setLeadComponent(events_btn);
        
        
        
        Image newevent_img = res.getImage("newevent.png").scaledSmallerRatio(100, 100); 
        Button newevent_btn = new Button(newevent_img);
        Label newevent_label = new Label("Ajout");
        newevent_label.setUIID("TitleIcon");
        Container iconsTopRight = GridLayout.encloseIn(2,
           newevent_btn,
           newevent_label     
                
        );
        newevent_btn.addActionListener((newevs)-> {
            new AddEventForm(res).show();
        });
        iconsTopRight.setLeadComponent(newevent_btn);
        
        Image Myevents_img = res.getImage("myevents.png").scaledSmallerRatio(100, 100); 
        Button Myevents_btn = new Button(Myevents_img);
        Label Myevents_label = new Label("Mes événements");
        Myevents_label.setUIID("TitleIcon");
        Container iconsBotLeft = GridLayout.encloseIn(2,
           Myevents_btn,
           Myevents_label     
                
        );
        Myevents_btn.addActionListener((myevs)-> {
            new MyEventsForm(res).show();
        });
        iconsBotLeft.setLeadComponent(Myevents_btn);
        
        Image Myres_img = res.getImage("myres.png").scaledSmallerRatio(100, 100); 
        Button Myres_btn = new Button(Myres_img);
        Label Myres_label = new Label("Mes réservations");
        Myres_label.setUIID("TitleIcon");
        Container iconsBotRight = GridLayout.encloseIn(2,
           Myres_btn,
           Myres_label     
                
        );
        Myres_btn.addActionListener((myresevs)-> {
            new MyResForm(res).show();
        });
        iconsBotRight.setLeadComponent(Myres_btn);
        
        
        add(BoxLayout.encloseY(
                createLineSeparator(),
                createLineSeparator(),
                BoxLayout.encloseY(
                iconsTopLeft,
                iconsTopRight,
                iconsBotLeft,
                iconsBotRight
        )));
        
        
//        
//        Container iconsTopRight = new Container(BoxLayout.y());
//        iconsTopLeft.add(res.getImage("calendar.png").scaledSmallerRatio(100, 100));
//        Label Myevents = new Label("Mes événements");
//        Myevents.setWidth(10);
//        Myevents.setUIID("LabelIcon");
//        iconsTopLeft.add(Myevents);
//        
////        Container icons = new Container(new GridLayout(2,2));
////        icons.add(iconsTopLeft);
////        icons.add(iconsTopRight);
//        add(GridLayout.encloseIn(1,
//           iconsTopLeft,
//           iconsTopRight
//        ));
//         
////        Container iconsBotLeft = new Container(BoxLayout.y());
////        iconsBotLeft.add(res.getImage("calendar.png").scaledSmallerRatio(200, 200));
////        Label Myres = new Label("Mes réservations");
////        Myevents.setUIID("LabelIcon");
////        iconsBotLeft.add(Myres);
////          
////        Container iconsBotRight = new Container(BoxLayout.y());
////        iconsBotRight.add(res.getImage("calendar.png").scaledSmallerRatio(200, 200));
////        Label addev = new Label("Ajouter un événements");
////        addev.setUIID("LabelIcon");
////        iconsBotRight.add(addev);
////        
////
////        icons.add(BorderLayout.SOUTH,BoxLayout.encloseX(
////                iconsTopLeft,
////                iconsTopRight
////        ));
//        
//        //add(icons);
//        //add(iconsBot);

        
        
    }
    
        private void addTab(Tabs swipe, Image img,String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText")                            
                        )
                )
                    
            );
        swipe.addTab("", page1);
    }
    
   private Container addButton(Image img, String title,int id) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       Button details = new Button("Détails");
       details.setUIID("Details");       
      
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       details
               ));
       
       image.addActionListener(e -> {
           Evenement evv = ServicesEvenement.EventByID(id);
           new DetailsForm(Resources.getGlobalResources(), evv).show();
               });
       return cnt;
   }
    
    public Image UrlImage(Evenement e){
        String url ="http://127.0.0.1:8000/api/images?img="+e.getAffiche();
                EncodedImage placeholder = EncodedImage.createFromImage(Resources.getGlobalResources().getImage("load.png"), false);
                Image urli = URLImage.createToStorage(placeholder,"Medium_"+url, url,URLImage.RESIZE_SCALE);
                
        return urli;        
    }

    
    
}

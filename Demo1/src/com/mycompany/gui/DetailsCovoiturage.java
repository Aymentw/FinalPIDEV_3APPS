/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.FacebookShare;
import com.mycompagny.Service.ServiceAdresseCov;
import com.mycompany.Entite.Evenement;
import com.mycompagny.Service.ServicesEvenement;
import com.mycompany.Entite.Adresse_cov;
import com.mycompany.Entite.Covoiturage;
import com.mycompany.Entite.Topic;
import java.io.IOException;

/**
 *
 * @author Mechlaoui
 */
public class DetailsCovoiturage extends BaseForm {

    public DetailsCovoiturage(Resources res, Covoiturage ev) {
        super(BoxLayout.y());
        System.out.println(ev);
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Esprit Entr'Aide");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);
        Form previous = Display.getInstance().getCurrent();
        tb.addCommandToRightBar(null, res.getImage("backmenu.png"), e -> previous.showBack());
        //tb.setBackCommand("", e -> previous.showBack());

        Tabs swipe = new Tabs();
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        addTab(swipe, UrlImage(ev), ev.getDepart(), ev);
        add(LayeredLayout.encloseIn(swipe));
        
        MapContainer cnt = new MapContainer();
         Coord C = new Coord(36.898392, 10.1875433);
         Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(1));
         Style s2 = new Style();
        s2.setFgColor(0x66b3ff);
        s2.setBgTransparency(0);
        FontImage markerImg2 = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s2, Display.getInstance().convertToPixels(1));
        ServiceAdresseCov SAC= new ServiceAdresseCov();
         Adresse_cov depart= SAC.getList2(ev.getDepart());
       Adresse_cov destination= SAC.getList2(ev.getDestination());
       cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        new Coord(depart.getLat(),depart.getLng()),
                        ""+cnt.getCameraPosition().toString(),
                        "",
                        e3->{
                                ToastBar.showMessage("You clicked "+cnt.getName(), FontImage.MATERIAL_PLACE);
                        }
                );
        cnt.addMarker(
                        EncodedImage.createFromImage(markerImg2, false),
                        new Coord(destination.getLat(),destination.getLng()),
                        ""+cnt.getCameraPosition().toString(),
                        "",
                        e3->{
                                ToastBar.showMessage("You clicked "+cnt.getName(), FontImage.MATERIAL_PLACE);
                        }
                );
         cnt.zoom(C, 6);
addComponent(cnt);
    }

    public Image UrlImage(Covoiturage e) {
        String url = "http://localhost/piweb-master/web/images/Ressources/covIcon.png";
        
        EncodedImage placeholder = EncodedImage.createFromImage(Resources.getGlobalResources().getImage("covIcon.png"), false);
        Image urli = URLImage.createToStorage(placeholder, "Medium_" + url, url, URLImage.RESIZE_SCALE);

        return urli;
    }

    private void addTab(Tabs swipe, Image img, String text, Covoiturage ev) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
        Label overlay = new Label(" ", "ImageOverlay");
        //Button reserver = new Button();
        int height = Display.getInstance().convertToPixels(13.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button reserver = new Button(Resources.getGlobalResources().getImage("reserver.png").fill(width, height));
        //reserver.setIcon(l.getIcon());
        reserver.setUIID("Label");
        reserver.addActionListener((booking) -> {
            SpanLabel sp = new SpanLabel();
            InteractionDialog dlg = new InteractionDialog("Reservation");
            dlg.setLayout(new BorderLayout());
            sp.setText("Depart: " + ev.getDepart() + "\n"
                    + "Destination: " + ev.getDestination() + "\n"
                    + "Date: " + ev.getDate() + "\n"
            );
            sp.setTextUIID("textDialog");
            dlg.add(BorderLayout.CENTER, sp);

            Button res = new Button("Modifier");
            res.addActionListener((bookin) -> {
                new ApercueCovoiturage(Resources.getGlobalResources(), ev).show();
            });
            Button close = new Button("Fermer");
            close.addActionListener((ee) -> dlg.dispose());
            Container cnt = new Container(BoxLayout.x());
            cnt.add(res);
            cnt.add(close);
            dlg.addComponent(BorderLayout.SOUTH, cnt);
            Dimension pre = dlg.getContentPane().getPreferredSize();
            dlg.show(0, 160, 50, 0);
            //dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);
        });

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        BoxLayout.encloseX(reserver, new SpanLabel(text, "LargeWhiteText"))
                                )
                        )
                );
      

        swipe.addTab("", page1);
    }

   

    

}

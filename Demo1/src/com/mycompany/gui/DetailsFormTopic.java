/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.FacebookShare;
import com.mycompagny.Service.ServiceTopic;
import com.mycompany.Entite.Topic;

/**
 *
 * @author Mechlaoui
 */
public class DetailsFormTopic extends BaseForm {

    public DetailsFormTopic(Resources res, Topic ev) {
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

        addTab(swipe, UrlImage(ev), ev.getDescription(), ev);
        add(LayeredLayout.encloseIn(swipe));

    }

    public Image UrlImage(Topic e) {
        String url = "http://localhost/piweb-master/web/app_dev.php/api/imagesTop?img=" + e.getImage_name();
        EncodedImage placeholder = EncodedImage.createFromImage(Resources.getGlobalResources().getImage("load.png"), false);
        Image urli = URLImage.createToStorage(placeholder, "Medium_" + url, url, URLImage.RESIZE_SCALE);

        return urli;
    }

    private void addTab(Tabs swipe, Image img, String text, Topic ev) {
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
        ServiceTopic st = new ServiceTopic();
        reserver.addActionListener((booking) -> {
            try {
                SpanLabel sp = new SpanLabel();
                InteractionDialog dlg = new InteractionDialog("");
                dlg.setLayout(new BorderLayout());
                sp.setText("Sujet: " + ev.getSujet() + "\n"
                        + "Description: " + ev.getDescription() + "\n"
                        + "Date: " + st.getDate(ev.getId())+ "\n"
                );
                sp.setTextUIID("textDialog");
                dlg.add(BorderLayout.CENTER, sp);

                Button res = new Button("Partager");
                res.addActionListener((bookin) -> {
                    Dialog.show("Partagé avec succès", "", "Annuler", "Ok");
                    FacebookShare fb = new FacebookShare("EAACEdEose0cBAOPKtstUvPZB1VcFadETQxSZAOuFULPwbBMoRF7UvYOsUStRHhPiYxPTjElMO00jVbThi5eRYBsn2IjiKDUOJgqxpCxXui65Ac8ZAtVvm8igSf3l0MZATgyTLZABWVjG8HI7uTbS5vVsiawudWM5f3GCy9ZC29AoUco55K2AdpZAoUZA0o8lesgZD");
                    try {
                        fb.share("J'aime bien cette publication qui a comme nom " + ev.getSujet() + " et comme description " + ev.getDescription());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                Database db;
                db = Database.openOrCreate("fav.db");
                db.execute("create table if not exists Fav (sujet TEXT,description TEXT,image_name TEXT);\"");
                Button fav = new Button("Favoris");
                fav.addActionListener((favv) -> {
                    try {
                        db.execute("insert into Fav (sujet,description,image_name) values ('" + ev.getSujet() + "', '" + ev.getDescription() + "', '" + ev.getImage_name() + "');");
                        System.out.println("element inser");
                        Cursor cur = db.executeQuery("select distinct * from Fav");
                        AfficherTopFavoris afc = new AfficherTopFavoris(Resources.getGlobalResources(), cur);
                        afc.show();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                Button close = new Button("X");
                close.addActionListener((ee) -> dlg.dispose());
                Container cnt = new Container(BoxLayout.x());
                cnt.add(res);
                cnt.add(fav);
                cnt.add(close);
                dlg.addComponent(BorderLayout.SOUTH, cnt);
                Dimension pre = dlg.getContentPane().getPreferredSize();
                dlg.show(0, 160, 50, 0);
                //dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
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

import com.mycompany.Entite.Document;

import java.io.IOException;

/**
 *
 * @author Mechlaoui
 */
public class DetailsDocForm extends BaseForm {

    public DetailsDocForm(Resources res, Document ev) {
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

    public Image UrlImage(Document e) {
        String url = "http://localhost/piweb/web/app_dev.php/images?img=" + e.getPath();
        EncodedImage placeholder = EncodedImage.createFromImage(Resources.getGlobalResources().getImage("load.png"), false);
        Image urli = URLImage.createToStorage(placeholder, "Medium_" + url, url, URLImage.RESIZE_SCALE);

        return urli;
    }

    private void addTab(Tabs swipe, Image img, String text, Document ev) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
        Label overlay = new Label(" ", "ImageOverlay");
        Slider star = createStarRankSlider();
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
            sp.setText("Matière: " + ev.getMatiere()+ "\n"
                    + "Niveau: " + ev.getNiveau()+ "\n"
                    + "Description: " + ev.getDescription()+ "\n"
            );
            sp.setTextUIID("textDialog");
            dlg.add(BorderLayout.CENTER, sp);

            Button res = new Button("Partager");
            res.addActionListener((bookin) -> {
                FacebookShare fb = new FacebookShare("EAACEdEose0cBAGF8gcTaR1nGqZC3WWgpZBibghblZCd6VNEVYiIyHc3DePYstCLuhNkiPBDEJlZBg6ZAvsAdn5ZC1gAuxxZBvHTZCb4mV4lI9vFDbg7Vo1XJGHpDZASaxGTeDO7zJWpp29ZCZApXh9uSy8tZBDDX2cxP5RBSk7c9tMnBqvIjiWVG9eU0I4CeHzHQjZBzpppHwhuuDZAgZDZD");
                try {
                    fb.share("Matiére :"+ev.getMatiere()+"\n"+ev.getNiveau());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            Button close = new Button("X");
            close.addActionListener((ee) -> dlg.dispose());
            Button consulter = new Button("Consulter");
            consulter.addActionListener(e -> {
                FileSystemStorage fs = FileSystemStorage.getInstance();
                String fileName = fs.getAppHomePath() + ev.getPath();
                 if(!fs.exists(fileName)) {
                     Util.downloadUrlToFile("http://localhost/piweb/web/uploads/"+ev.getPath(), fileName, true);
                 }
                  Display.getInstance().execute(fileName);
                });
            Container cnt = new Container(BoxLayout.x());
            cnt.add(consulter);
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
                                        star,
                                        BoxLayout.encloseX(reserver, new SpanLabel(text, "LargeWhiteText"))
                                )
                        )
                );
        star.addActionListener((e) -> {
            int rating = star.getProgress();
            System.out.println(rating);

        });

        swipe.addTab("", page1);
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;

    }

}

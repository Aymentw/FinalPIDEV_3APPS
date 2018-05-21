/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.gui;

import com.codename1.components.OnOffSwitch;
import com.codename1.uikit.cleanmodern.*;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
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
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceColocation;
import com.mycompagny.Service.ServiceDoc;
import com.mycompany.Entite.Colocation;
import com.mycompany.Entite.Document;
import java.util.ArrayList;
import java.util.Hashtable;
import javafx.scene.control.Alert;
import rest.file.uploader.tn.FileUploader;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class AfficherColocDetail extends BaseForm {

    public AfficherColocDetail(Resources res,Colocation cd) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Esprit Entr'Aide");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("esprit-logo.png"), spacer1, "", "", "");

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
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
       RadioButton mesColoc = RadioButton.createToggle("Mes colocation", barGroup);
        mesColoc.setUIID("SelectBar");
        
        RadioButton liste = RadioButton.createToggle("colocations", barGroup);
        liste.setUIID("SelectBar");
        RadioButton modif = RadioButton.createToggle("Modifier colocation", barGroup);
        modif.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        liste.addActionListener((e) -> {
            AfficherColoc a = new AfficherColoc(res);
            a.show();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesColoc, liste, modif),
                FlowLayout.encloseBottom(arrow)
        ));

        modif.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(modif, arrow);
        });
        bindButtonSelection(liste, arrow);
        bindButtonSelection(modif, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
             Label l =new Label(""+cd.getId());
        TextField typecoloc = new TextField(cd.getType(), "Type colocation");
        typecoloc.setUIID("TextFieldBlack");

        addStringValue("Type colocation", typecoloc);

        TextField Adresse = new TextField(cd.getAdresse(), "Adresse");
        Adresse.setUIID("TextFieldBlack");
        addStringValue("Adresse", Adresse);

//        TextField sexe = new TextField("", "Sexe");
//        sexe.setUIID("TextFieldBlack");
//        addStringValue("sexe", sexe);
     TextField sexe = new TextField(""+cd.getSexe(), "sexe");
        sexe.setUIID("TextFieldBlack");
        addStringValue("sexe", sexe);
      

        TextField prix = new TextField(""+cd.getPrix(), "prix");
        prix.setUIID("TextFieldBlack");
        addStringValue("prix", prix);

        TextField placeDispo = new TextField(cd.getPlace_dispo()+"", "Place Disponible");
        placeDispo.setUIID("TextFieldBlack");
        addStringValue("place dispo", placeDispo);

        TextField typeMaison = new TextField(cd.getType_maison(), "Type maison");
        typeMaison.setUIID("TextFieldBlack");
        addStringValue(" Type maison", typeMaison);

        TextField desc = new TextField(cd.getDescription(), "Description ");
        desc.setUIID("TextFieldBlack");
        addStringValue("Description ", desc);
        
         TextField path = new TextField(cd.getPath(), "path");
        path.setUIID("TextFieldBlack");
        addStringValue("Path", path);

        
        Button imgBtn = new Button("image");
         addStringValue("", imgBtn);
        imgBtn.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        
                     String  path1 = (String)ev.getSource();
                        Image img = null;
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path1));
//                         FileUploader fu = new FileUploader("localhost/piweb/piweb/web/");
//                //Upload
//                 String fileNameInServer = fu.upload(path.getText());
                                path.setText(path1.substring(7));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    
                    }
                }

               
            }, Display.GALLERY_IMAGE);
        });

        Button btnmodif = new Button("Modifier");
        addStringValue("", btnmodif);

        btnmodif.addActionListener((e) -> {

            try {
                
               FileUploader fu = new FileUploader("localhost/piweb-master/web/");
               //Upload
                String fileNameInServer = fu.upload(path.getText());

                  
                ServiceColocation ser = new ServiceColocation();
                Colocation c = new Colocation(cd.getId(),typecoloc.getText(), Adresse.getText(), sexe.getText(), (Float.valueOf(prix.getText())), Integer.valueOf(placeDispo.getText()), typeMaison.getText(), desc.getText(), fileNameInServer);
               
                
             {
                ser.modifierColocation(c);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
 public boolean isAnumber(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}

package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServicesUser;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Mechlaoui
 */
public class ProfileForm extends BaseForm {

    private static String i;

    public ProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Esprit Entr'Aide");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        Image img = res.getImage("back-logo.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Button modiff = new Button("Modifier");
        Button picture = new Button("Photo");
        

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

        String us = SessionManager.getUserName();
        System.out.println(us);

        TextField username = new TextField(us);
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);
        TextField password = new TextField(SessionManager.getPass(), "password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);

        TextField email = new TextField(SessionManager.getEmail(), "email", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("email", email);
        
        Vector<String> VectorNiveau = new Vector();
        VectorNiveau.add("3A");
        VectorNiveau.add("3B");
        VectorNiveau.add("4BI");
        VectorNiveau.add("4");
        VectorNiveau.add("5");
        
        ComboBox<String> niveau = new ComboBox<>(VectorNiveau);
        niveau.setSelectedItem(SessionManager.getNiveau());
        
        addStringValue("Niveau", niveau);
        
        
        
        picture.setUIID("Update");
        modiff.setUIID("Edit");
        addStringValue("", picture);
        addStringValue("", modiff);
        
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
        
        
        modiff.addActionListener((edit)-> {
            ServicesUser.EditUser(username.getText(), password.getText(), email.getText(), niveau.getSelectedItem(), path.getText());
            SessionManager.setUserName(username.getText());
            SessionManager.setPass(password.getText());
            SessionManager.setEmail(email.getText());
            SessionManager.setNiveau(niveau.getSelectedItem());
            SessionManager.setPhoto(username.getText()+".jpg");
            Dialog.show("Succès", "Modifications des coordonnées avec succès", "Ok", null);
            refreshTheme();
            
        });
        
    } 

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}

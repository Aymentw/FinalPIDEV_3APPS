package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Map;

/**
 * @author Mechlaoui
 */
public class SignInForm extends BaseForm {

    ConnectionRequest connectionRequest;
    Label lblogo;
    TextField username, password;

    public SignInForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("IMGLogin");

        add(BorderLayout.NORTH, new Label(res.getImage(""), "LogoLabel"));
        
        lblogo = new Label();
        lblogo.setUIID("LogoLabel");
        username = new TextField("", "Utilisateur",20, TextField.ANY);
        password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Se Connecter");
        Button signUp = new Button("Creer Compte");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Avez vous un compte?");
   
        
        Container content = BoxLayout.encloseY(
               
                new ImageViewer(res.getImage("masters.png")),
                createLineSeparator(),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        //signIn.addActionListener(e -> new NewsfeedForm(res).show());
        signIn.addActionListener((e) ->{
                String url = "http://127.0.0.1:8000/api/login";
                connectionRequest = new ConnectionRequest(url,false);
                connectionRequest.addArgument("username",username.getText());
                connectionRequest.addArgument("password",password.getText());
                connectionRequest.addResponseListener((action) -> {
                     try {

                            JSONParser j = new JSONParser();
                            String json = new String(connectionRequest.getResponseData()) + "";
                            if (json.equals("failed")){
                                Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
                            }
                            else{
                            System.out.println(json);

                            
                            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                            System.out.println(user);
                                float id = Float.parseFloat(user.get("id").toString());
                                System.out.println((int)id);
                                SessionManager.setId((int)id);
                                SessionManager.setPass(password.getText());
                                SessionManager.setUserName(user.get("username").toString());
                                SessionManager.setEmail(user.get("email").toString());
                                if (user.get("niveau") != null){
                                SessionManager.setNiveau(user.get("niveau").toString());
                                }
                                if (user.get("photo") !=null){
                                SessionManager.setPhoto(user.get("photo").toString());
                                }
                            
                            if (user.size() > 0) {
                                new NewsfeedForm(res).show();
                                

                            }
                            }
                                    

                   
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    
                });
                NetworkManager.getInstance().addToQueue(connectionRequest);
            
        });
        
        
    }
    
 

    
    

}

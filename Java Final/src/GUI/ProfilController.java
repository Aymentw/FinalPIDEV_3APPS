/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import static GUI.InscriptionController.PasswordStrong;
import Services.ServicesUser;
import Services.Uploadphoto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class ProfilController implements Initializable {
    
ServicesUser su;
    User currentUser;
    @FXML
    private JFXComboBox<Label> niveau_cb;
    @FXML
    private ImageView ImageError3;
    @FXML
    private ImageView ImageError6;
    @FXML
    private ImageView ImageError7;
    @FXML
    private JFXTextField TextPseudo;
    @FXML
    private JFXPasswordField TextPassword;
    @FXML
    private JFXPasswordField TextPasswordConf;
    @FXML
    private JFXButton update_btn;
    @FXML
    private ImageView photo_img;
    @FXML
    private JFXButton home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        su=new ServicesUser();
        
        
    try {
        currentUser=su.login(Session.getUsernameThisUser());
    } catch (SQLException ex) {
        Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
    }
      
       
        initfields();
        
       for (int i = 1; i < 6; i++) {
           String niv=i+"A";
            niveau_cb.getItems().add(new Label(niv));
            if(currentUser.getNiveau().equals(niv)) niveau_cb.setValue(niveau_cb.getItems().get(i-1));
        }
        
        ImageError3.setVisible(false);
        TextPseudo.setText(currentUser.getUsername());
        if(!currentUser.getPhoto().equals(null))
            photo_img.setImage(new Image("file:\\\\\\C:\\wamp64\\www\\Files\\"+currentUser.getPhoto()
                    , 200, 200, true, true));
    }    
public void initfields(){
    
    TextPseudo.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(TextPseudo.getText().equals("")||TextPseudo.getLength()<4
                        ||TextPseudo.getLength()>15
                        || (su.verif_username(TextPseudo.getText()) 
                        && !TextPseudo.getText().equals(Session.getUsernameThisUser()))){
                    
                    ImageError3.setVisible(true);
                }
                else
{
    
            ImageError3.setVisible(false);
}
                controlfields();
            }
        }); 
          
            TextPassword.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(TextPassword.getText().equals("")||TextPassword.getLength()<4||TextPassword.getLength()>15||!PasswordStrong(TextPassword.getText()))
                    {
                       
                    ImageError6.setVisible(true);
                TextPasswordConf.setVisible(false);
                ImageError7.setVisible(false);
                    }
                else
{
    
            ImageError6.setVisible(false);
            TextPasswordConf.setVisible(true);
            ImageError7.setVisible(true);
}
                controlfields();
            }
        });
             TextPasswordConf.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!TextPasswordConf.getText().equals(TextPassword.getText())){
                  update_btn.setDisable(true);
                    ImageError7.setVisible(true);
                }
                else
{           
            ImageError7.setVisible(false);
}
                controlfields();
            }
        });
}
public void controlfields(){
     if(!"".equals(TextPseudo.getText()) && !"".equals(TextPassword) && !ImageError3.isVisible() && !ImageError6.isVisible() && !ImageError7.isVisible())
            update_btn.setDisable(false);
     else update_btn.setDisable(true);
}
    @FXML
    private void update_action(ActionEvent event) {
       if(!"".equals(TextPseudo.getText()))
        currentUser.setUsername(TextPseudo.getText());
        if(!"".equals(TextPassword.getText()))
            currentUser.setPassword(TextPassword.getText());
         currentUser.setNiveau(niveau_cb.getSelectionModel().getSelectedItem().getText());
         su.update(currentUser);
         
 }

    @FXML
    private void modifierimage_action(ActionEvent event) {
        Uploadphoto u = new Uploadphoto();
        File selectedfile;
         FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("*.png", "*.jpg")
        );
        Stage stage=new Stage();
        selectedfile = fc.showOpenDialog(stage);
        if (selectedfile != null) {
           
            try {
                photo_img.setImage(new Image("file:\\\\\\C:\\Users\\ASUS-PC\\Desktop\\johnny.jpg", 200, 200, true, true));
                System.out.println(u.upload(selectedfile));
                currentUser.setPhoto(selectedfile.getName());
                su.updatephoto(currentUser);
            } catch (IOException ex) {
                Logger.getLogger(IntAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void bnt_acceuil(ActionEvent event) {
        Accueil.changescenes("Accueil.fxml");
    }

   
    
}

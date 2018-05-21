/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.ServicesUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Manai
 */
public class InscriptionController implements Initializable {

    private ServicesUser su;
    private static User currentuser;
    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private JFXTextField TextPseudo;
    @FXML
    private JFXTextField TextEmail;
    @FXML
    private JFXPasswordField TextPassword;
    @FXML
    private JFXPasswordField TextPasswordConf;
    @FXML
    private JFXButton btnContinue;
    @FXML
    private JFXButton btnResetAll;
    @FXML
    private ImageView ImageError3;
    @FXML
    private ImageView ImageError4;
    @FXML
    private ImageView ImageError6;
    @FXML
    private ImageView ImageError7;
    @FXML
    private AnchorPane AnchorErrorN3;
    @FXML
    private AnchorPane AnchorErrorN4;
    @FXML
    private AnchorPane AnchorErrorN7;
    @FXML
    private AnchorPane AnchorErrorN6;
    @FXML
    private JFXComboBox<Label> niveau_cb;

    /**
     * Initia    @FXML
    private TextField txtUserName;
lizes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        su=new ServicesUser();
          for (int i = 1; i < 6; i++) {
            niveau_cb.getItems().add(new Label(i+"A"));
        }
          niveau_cb.setValue(niveau_cb.getItems().get(0));
        TextPseudo.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(TextPseudo.getText().equals("")||TextPseudo.getLength()<4||TextPseudo.getLength()>15|| su.verif_username(TextPseudo.getText()))

                    ImageError3.setVisible(true);
                else
{
            ImageError3.setVisible(false);}
            }
        });
           TextEmail.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(TextEmail.getText().equals("")||!validate(TextEmail.getText()))

                    ImageError4.setVisible(true);
                else
{
            ImageError4.setVisible(false);}
            }
        });    
          
            TextPassword.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(TextPassword.getText().equals("")||TextPassword.getLength()<4||TextPassword.getLength()>15||!PasswordStrong(TextPassword.getText()))

                    ImageError6.setVisible(true);
                else
{
            ImageError6.setVisible(false);}
            }
        });
             TextPasswordConf.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!TextPasswordConf.getText().equals(TextPassword.getText()))

                    ImageError7.setVisible(true);
                else
{
            ImageError7.setVisible(false);}
            }
        });
    }    
  
public static  boolean validate( String email){
 final String EMAIL_VERIFICATION = "^([\\w-\\.]+)@([\\w\\.]+)\\.([a-z]){2,}$";
Pattern pattern = Pattern.compile(EMAIL_VERIFICATION);
        Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	  }
  public static boolean PasswordStrong(String pass) {
    String expresion = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    Pattern patron = Pattern.compile(expresion);
    Matcher m = patron.matcher(pass);
    if (m.find())
        return true;
    return false;
}
   


    @FXML
    private void ajout_user(ActionEvent event) {
         if(!ImageError3.isVisible() && !ImageError4.isVisible() 
                 && !ImageError6.isVisible() && !ImageError7.isVisible()  )  {
             User U= new User(TextEmail.getText(), TextPseudo.getText(),
                     TextPassword.getText(),
                     "étudiant",niveau_cb.getSelectionModel().getSelectedItem().getText());
             try {
                 su.AjouterUser(U);
             } catch (SQLException ex) {
                 Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
             }
             Accueil.changescenes("Login.fxml");
         }  
             
    }

    @FXML
    private void reset_All(ActionEvent event) {
    }

    @FXML
    private void HideError3(MouseEvent event) {
    }

     @FXML private void AffichageError3()
   {
      AnchorErrorN3.setVisible(true);
   }
    private void HideError3()
   {
      AnchorErrorN3.setVisible(false);
   }
     @FXML private void AffichageError4 ()
   {
      AnchorErrorN4.setVisible(true);
   }
    @FXML private void HideError4 ()
   {
      AnchorErrorN4.setVisible(false);
   }

     @FXML private void AffichageError6()
   {
      AnchorErrorN6.setVisible(true);
   }
    @FXML private void HideError6()
   {
      AnchorErrorN6.setVisible(false);
   }
     @FXML private void AffichageError7 ()
   {
      AnchorErrorN7.setVisible(true);
   }
    @FXML private void HideError7 ()
   {
      AnchorErrorN7.setVisible(false);
   }
     @FXML
    void go_Back(ActionEvent event) {
Accueil.changescenes("Login.fxml");
    }
}

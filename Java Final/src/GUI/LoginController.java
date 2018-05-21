/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import Services.ServicesUser;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class LoginController implements Initializable {
    
    ServicesUser su;
    @FXML
    private JFXTextField username_field;
    @FXML
    private JFXPasswordField password_field;
    @FXML
    private Text msgerreur;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }    

    @FXML
    private void seConnecter_action(ActionEvent event) throws SQLException {
         su = new ServicesUser();
        User U=null;
        U = su.login(username_field.getText());
        if (U!=null && (BCrypt.checkpw(password_field.getText(),U.getPassword()))){
           Session.setIdThisUser(U.getId());
            Session.setUsernameThisUser(U.getUsername());
             Session.setRoleThisUser(U.getRole());
             if(U.getRole().equals("Admin"))
                  Accueil.changescenes("IntAdmin.fxml"); 
             else
            Accueil.changescenes("Accueil.fxml");    
        }
        else {
            msgerreur.setVisible(true);
        }
    }

    @FXML
    private void Inscrir_action(ActionEvent event) {
        Accueil.changescenes("Inscription.fxml");
    }
    
}

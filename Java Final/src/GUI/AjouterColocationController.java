/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Colocation;
import Services.ServiceCol;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karim
 */
public class AjouterColocationController implements Initializable {

    @FXML
    private Label adresselab;
    @FXML
    private TextField adressetxt;
    @FXML
    private Label colocspresent;
    @FXML
    private ComboBox<Integer> colocspresenttxt;
    @FXML
    private Label typelog;
    @FXML
    private ComboBox<String> typelogtxt;
    @FXML
    private Button deposezannoncebtn;
    @FXML
    private TextArea desc;
    @FXML
    private ImageView imgannonce;
    @FXML
    private Button parcourir;
    @FXML
    private TextField prixtxt;
    @FXML
    private Label prixlab;
    @FXML
    private Hyperlink Acceuilajout;
    @FXML
    private Hyperlink deconnexionrech;
    @FXML
    private Hyperlink deconnexionrech1;

    /**
     * Initializes the controller class.
     */
    public ObservableList<Integer> listNiveau = FXCollections.observableArrayList(1, 2, 3);
    public ObservableList<String> listNiveau1 = FXCollections.observableArrayList("maison", "studio", "sta7");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colocspresenttxt.setValue(0);
        colocspresenttxt.setItems(listNiveau);
        
        typelogtxt.setValue("appartement");
       typelogtxt.setItems(listNiveau1);
    }    
    @FXML
    private void AjouterColocation(ActionEvent event) throws IOException {
      Colocation c=new Colocation(adressetxt.getText(),Float.valueOf(prixtxt.getText()),colocspresenttxt.getSelectionModel().getSelectedItem(),typelogtxt.getSelectionModel().getSelectedItem(), desc.getText());
       

                ServiceCol sv=new ServiceCol();
    }

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commentaire;
import Entities.Document;
import Entities.Session;
import API.SendMail;
import Entities.User;
import Services.ServicesCommentaire;
import Services.ServicesDocument;
import Services.ServicesUser;
import Services.UploadDoc;
import java.lang.Object;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.awt.Desktop;
import java.io.File;
import javafx.application.HostServices;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Manai
 */
public class AfficherDocumentController implements Initializable {
    
    //id reçu du form d'accueil
    public int id, id_user;
    File selectedfile;
    private ServicesUser su;
    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtMatiere;
    @FXML
    private TextArea txtDescription;
    @FXML
    private ComboBox<String> cboNiveau;
    @FXML
    private Button btnUploadDoc;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField txtpath;
    @FXML
    private Button btnModifier;
    @FXML
    private TableView<Commentaire> tabCommentaire;
    @FXML
    private TableColumn<Commentaire, String> cellContenu;
    @FXML
    private TableColumn<Commentaire, Integer> cellNote;
    public ObservableList<Commentaire> data;
    @FXML
    private AnchorPane btnCommenter;
    @FXML
    private TextArea txtCommentaire;
    @FXML
    private Button btnValider;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void afficher_commentaires() throws SQLException
    {
        ServicesCommentaire sc = new ServicesCommentaire();
        data = sc.ListComDoc(id);
        cellContenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        cellNote.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tabCommentaire.setItems(null);
        tabCommentaire.setItems(data);
        
    }
   
    public void setChamps(String titre, String description,  String matiere, String niveau, String path) throws SQLException
    {
        if (Session.getRoleThisUser().equals("etudiant") && Session.getIdThisUser()!=id_user)
        {
            btnModifier.setVisible(false);
            btnSupprimer.setVisible(false);
            btnUploadDoc.setVisible(false);
            txtpath.setVisible(false);
        }
        if(Session.getRoleThisUser().equals("etudiant"))
        {
            btnValider.setVisible(false);
        }
        txtTitre.setText(titre);
        txtDescription.setText(description);
        txtMatiere.setText(matiere);
        txtpath.setText(path);
        cboNiveau.setValue(niveau);
        afficher_commentaires();
    }
    
    public void initChamps()
    {
        txtTitre.setText("");
        txtpath.setText(""); 
        txtDescription.setText(""); 
        cboNiveau.setValue("-");
        txtMatiere.setText("");
                
    }
    
    @FXML
    private void btnUploadDoc(ActionEvent event) {
         FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("file", "*.pdf")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            txtpath.setText(selectedfile.getName());
            
        }
    }

    @FXML
    private void SupprimerDocument(ActionEvent event) throws SQLException {
        ServicesDocument sd = new ServicesDocument();
        sd.SupprimerDocument(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Document Supprimer...");
        alert.setHeaderText("Document Supprimer avec succées");
        alert.showAndWait();
        initChamps();
    }

    @FXML
    private void ModifierDocument(ActionEvent event) throws SQLException, IOException {
        Document d = new Document(txtTitre.getText(), txtpath.getText(), txtDescription.getText(), cboNiveau.getSelectionModel().getSelectedItem().toString(), txtMatiere.getText());
        ServicesDocument sd = new ServicesDocument();
        sd.MAJDocument(d, id);
        UploadDoc u = new UploadDoc();
        u.upload(selectedfile);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Document Modifier...");
        alert.setHeaderText("Document modifier avec succées");
        alert.showAndWait();
    }

    @FXML
    private void CommenterDocument(ActionEvent event) throws SQLException {
        Commentaire cm = new Commentaire(txtCommentaire.getText(), id, Session.getIdThisUser());
        ServicesCommentaire sc = new ServicesCommentaire();
        sc.AjouterCommentaireDoc(cm);
        txtCommentaire.setText("");
        afficher_commentaires();
    }

    @FXML
    private void ValiderDocument(ActionEvent event) throws SQLException {
        ServicesDocument sd = new ServicesDocument();
        sd.ValiderDocument(id);
        SendMail sm = new SendMail();
        sm.envoiMultiple(cboNiveau.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void ouvrirPdf(ActionEvent event) throws IOException {
//        File file = new File("C:/Users/YourUsername/Desktop/Test.pdf");
//        HostServices hostServices = getHostServices();
//        hostServices.showDocument(file.getAbsolutePath());
        Desktop.getDesktop().open(new File("C:\\wamp64\\www\\piweb-master\\web\\uploads\\"+txtpath.getText()));
        //Runtime.getRuntime().exec("rundll url.dll, FileProtocolHandler" + ("E:\\outils\\wamp64\\www\\Files\\"+txtpath.getText()));
        
    }

    @FXML
    private void PartagerDoc(ActionEvent event) {
        Accueil.changescenes("PartagerDocument.fxml");
    }

    @FXML
    private void goToAccueil(ActionEvent event) {
        Accueil.changescenes("Accueil.fxml");
    }

    @FXML
    private void goToListeDoc(ActionEvent event) {
        Accueil.changescenes("AccueilDocuments.fxml");
    }




    
    
}

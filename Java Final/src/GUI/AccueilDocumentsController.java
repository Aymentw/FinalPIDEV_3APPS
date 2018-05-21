/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Document;
import Entities.Session;
import Services.ServicesDocument;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Manai
 */
public class AccueilDocumentsController implements Initializable {

    @FXML
    private ComboBox<String> cboNiveau;
    @FXML
    private TextField txtMatiere;
    @FXML
    private TableView<Document> tabDocuments;
    @FXML
    private TableColumn<Document, String> cellTitre;
    @FXML
    private TableColumn<Document, String> cellDescription;

    public ObservableList<Document> data;
    public ObservableList<String> listNiveau = FXCollections.observableArrayList("3A", "3B", "4BI");
    @FXML
    private Button btnAffiche;
    @FXML
    private TableColumn<Document, Integer> cellId;
    @FXML
    private TableColumn<Document, String> cellPath;
    @FXML
    private TableColumn<Document, String> cellNiveau;
    @FXML
    private TableColumn<Document, String> cellMatiere;
    @FXML
    private TableColumn<?, ?> cellId_User;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboNiveau.setValue("-");
        cboNiveau.setItems(listNiveau);
        ServicesDocument sd = new ServicesDocument();
        
        try {
            if(Session.getRoleThisUser().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) 
            {
                data = sd.ListDocAdmin();
            }
            else
            {
            data = sd.ListDoc();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccueilDocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cellTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        cellNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        cellMatiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        tabDocuments.setItems(null);
        tabDocuments.setItems(data);
    }
    
    @FXML
    private void AfficherContenue(ActionEvent event) throws IOException, SQLException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDocument.fxml"));
        Parent root = (Parent) loader.load();
        AfficherDocumentController afc = loader.getController();
        afc.id=this.getId();
        afc.id_user=this.getId_user();
        afc.setChamps(this.getTitre(), this.getDescription(), this.getMatiere(), this.getNivrau(), this.getPath());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Accueil.stage.close();
    }

    public int getId()
    {
        return tabDocuments.getSelectionModel().getSelectedItem().getId();
    }
    public String getTitre()
    {
       return tabDocuments.getSelectionModel().getSelectedItem().getTitre();
    }
    
    public String getDescription()
    {
       return tabDocuments.getSelectionModel().getSelectedItem().getDescription();
    }
    
    public String getMatiere()
    {
        return tabDocuments.getSelectionModel().getSelectedItem().getMatiere();
    }
    
    public String getNivrau()
    {
        return tabDocuments.getSelectionModel().getSelectedItem().getNiveau();
    }
    
    public String getPath()
    {
        return tabDocuments.getSelectionModel().getSelectedItem().getPath();
    }
    
    public int getId_user()
    {
        return tabDocuments.getSelectionModel().getSelectedItem().getId_user();
    }

    @FXML
    private void RechercheMatiere(KeyEvent event) throws SQLException {
        ServicesDocument sd = new ServicesDocument();
        data = sd.SearchDocMatiere(txtMatiere.getText());
        cellTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        cellNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        cellMatiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        tabDocuments.setItems(null);
        tabDocuments.setItems(data);
    }

    @FXML
    private void RechercheNiveau(ActionEvent event) throws SQLException {
        ServicesDocument sd = new ServicesDocument();
        data = sd.SearchDocNiveau(cboNiveau.getSelectionModel().getSelectedItem().toString());
        cellTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        cellNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        cellMatiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        tabDocuments.setItems(null);
        tabDocuments.setItems(data);
    }

    @FXML
    private void PartagerDoc(ActionEvent event) {
        Accueil.changescenes("PartagerDocument.fxml");
    }

    @FXML
    private void goToAccueil(ActionEvent event) {
        Accueil.changescenes("Accueil.fxml");
    }
}
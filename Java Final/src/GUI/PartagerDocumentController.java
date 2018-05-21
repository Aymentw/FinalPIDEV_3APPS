/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Document;
import Entities.Session;
import API.SendMail;
import Services.ServicesDocument;
import Services.UploadDoc;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Manai
 */
public class PartagerDocumentController implements Initializable {

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
    
    public static File file;
    String path_img;   

    @FXML
    private Button btnPartager;
    
    File selectedfile;

    public ObservableList<String> listNiveau = FXCollections.observableArrayList("3A", "3B", "4BI");
    @FXML
    private TextField txtpath;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cboNiveau.setValue("-");
        cboNiveau.setItems(listNiveau);
        txtpath.setDisable(true);
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
    private void PartagerDocument(ActionEvent event) throws SQLException, IOException {
        Document d = new Document(txtTitre.getText(), txtpath.getText(), txtDescription.getText(), cboNiveau.getSelectionModel().getSelectedItem().toString(), txtMatiere.getText(), "en attente", Session.getIdThisUser());
        ServicesDocument sd = new ServicesDocument();
        sd.AjouterDocument(d);
        UploadDoc u = new UploadDoc();
        u.upload(selectedfile);
        alert();
    }
    
    public void alert()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Document Partager...");
        alert.setHeaderText("Document ajouter avec succées");
        alert.setContentText("veuillez attendre la verification pour que ça soit publique ");
        alert.showAndWait();
        initChamps();
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
            //path_img = selectedfile.getAbsolutePath();
        }
    }
    
}

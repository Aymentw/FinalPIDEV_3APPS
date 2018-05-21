/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Annonce;
import Entities.Colocation;
import Entities.Covoiturage;
import Entities.Document;
import Entities.Reclamation;
import Entities.Topic;
import Services.ServiceAnnonce;
import Services.ServiceCol;
import Services.ServiceReclamation;
import Services.ServiceTopic;
import Services.ServicesCovoiturage;
import Services.ServicesDocument;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class IntAdminController implements Initializable {
  private final ServiceCol scol= new ServiceCol();
  private final ServicesCovoiturage scov= new ServicesCovoiturage();
  private final ServiceAnnonce sa= new ServiceAnnonce();
  private final ServiceTopic st= new ServiceTopic();
  private final ServiceReclamation sr = new ServiceReclamation();
  private final ServicesDocument sd = new ServicesDocument();
    @FXML
    private Tab coloc_tab;
    @FXML
    private Tab ann_tab;
    @FXML
    private Tab rec_tab;
    @FXML
    private Tab topic_tab;
    @FXML
    private Tab doc_tab;
    @FXML
    private Tab ev_tab;
    @FXML
    private Tab cov_tab;
    @FXML
    private Pane col_pane;
    @FXML
    private TableView<Colocation> tableviewfav;
    @FXML
    private TableColumn<Colocation, String> adressefav;
    @FXML
    private TableColumn<Colocation, String> sexefav;
    @FXML
    private TableColumn<Colocation, String> prixfav;
    @FXML
    private TableColumn<Colocation, String> placedispofav;
    @FXML
    private TableColumn<Colocation, String> typemaisonfav;
    @FXML
    private TableColumn<Colocation, String> descfav;
    @FXML
    private Pane cov_pane;
    @FXML
    private TableView<Covoiturage> tableviewcov;
    @FXML
    private TableColumn<Covoiturage, String> DepartColumn;
    @FXML
    private TableColumn<Covoiturage, String> DestColumn;
    @FXML
    private TableColumn<Covoiturage, String> TypeColumn;
    @FXML
    private TableColumn<Covoiturage, Date> DateColumn;
    @FXML
    private Pane ann_pane;
    @FXML
    private TableView<Annonce> Annonces;
    @FXML
    private TableColumn<Annonce, String> ColumnLibelle;
    @FXML
    private TableColumn<Annonce, String> ColumnDesc;
    @FXML
    private TableColumn<Annonce, ImageView> ColumnPhoto;
    @FXML
    private Pane rec_pane;
    @FXML
    private TableView<Reclamation> Reclamations;
    @FXML
    private TableColumn<Reclamation, String> ColumnSujetTop;
    @FXML
    private TableColumn<Reclamation, String> ColumnDescTop;
    @FXML
    private Pane topic_pane;
    @FXML
    private TableView<Topic> Topics;
    @FXML
    private TableColumn<Topic, String> ColumnSujetTop1;
    @FXML
    private TableColumn<Topic, String> ColumnDescTop1;
    @FXML
    private Pane doc_pane;
    @FXML
    private TableView<Document> tabDocuments;
    @FXML
    private TableColumn<Document, String> cellTitre;
    @FXML
    private TableColumn<Document, String> cellDescription;
    @FXML
    private TableColumn<Document, String> cellPath;
    @FXML
    private TableColumn<Document, String> cellNiveau;
    @FXML
    private TableColumn<Document, String> cellMatiere;
    @FXML
    private Pane ev_pane;
    @FXML
    private JFXTabPane tabpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initcol();
        initcov();
        initann();
        initrec();
        inittopic();
        initdoc();
    }    
public void initcol(){
      try {
          tableviewfav.setItems(scol.affichercolocation_all());
          adressefav.setCellValueFactory(new PropertyValueFactory<>("adresse"));
          sexefav.setCellValueFactory(new PropertyValueFactory<>("sexe"));
          prixfav.setCellValueFactory(new PropertyValueFactory<>("prix"));
          placedispofav.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));
          typemaisonfav.setCellValueFactory(new PropertyValueFactory<>("type_maison"));
          descfav.setCellValueFactory(new PropertyValueFactory<>("description"));
      } catch (SQLException ex) {
          Logger.getLogger(IntAdminController.class.getName()).log(Level.SEVERE, null, ex);
      }
}
public void initcov(){
    try {
          tableviewcov.setItems(scov.affichercovoiturage());
          DepartColumn.setCellValueFactory(new PropertyValueFactory<>("depart"));
          DestColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
          TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type_ann"));
          DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
         
      } catch (SQLException ex) {
          Logger.getLogger(IntAdminController.class.getName()).log(Level.SEVERE, null, ex);
      }
}
public void initann(){
      try {
          ObservableList<Annonce> listeAnimaux = FXCollections.observableArrayList(Annonce.generateImageViews(sa.displayAnnonce()));
          Annonces.setItems(listeAnimaux);
          ColumnDesc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Annonce, String>, ObservableValue<String>>() {
              @Override
              public ObservableValue<String> call(TableColumn.CellDataFeatures<Annonce, String> param) {
                  return new ReadOnlyObjectWrapper(param.getValue().getDescription());
              }
          });
          ColumnLibelle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Annonce, String>, ObservableValue<String>>() {
              @Override
              public ObservableValue<String> call(TableColumn.CellDataFeatures<Annonce, String> param) {
                  return new ReadOnlyObjectWrapper(param.getValue().getLibelle());
              }
          });
          
          ColumnPhoto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Annonce, ImageView>, ObservableValue<ImageView>>() {
              @Override
              public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Annonce, ImageView> param) {
                  return new ReadOnlyObjectWrapper(param.getValue().getImg());
              }
          });
      } catch (SQLException ex) {
          Logger.getLogger(IntAdminController.class.getName()).log(Level.SEVERE, null, ex);
      }
}
public void initrec(){
     try {
            Reclamations.setItems(sr.displayReclamation());
        } catch (SQLException ex) {
            Logger.getLogger(IntReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ColumnSujetTop.setCellValueFactory(new PropertyValueFactory<>("objet"));
        ColumnDescTop.setCellValueFactory(new PropertyValueFactory<>("description"));
}
public void inittopic(){
     try {
            Topics.setItems(st.displayTopic());
        } catch (SQLException ex) {
            Logger.getLogger(IntTopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ColumnSujetTop1.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        ColumnDescTop1.setCellValueFactory(new PropertyValueFactory<>("description"));
}
public void initdoc(){
     try {
         tabDocuments.setItems(sd.ListDoc_all());
            
        } catch (SQLException ex) {
            Logger.getLogger(AccueilDocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cellTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        cellDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        cellPath.setCellValueFactory(new PropertyValueFactory<>("path"));
        cellNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        cellMatiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        
        
        
}
    @FXML
    private void suppcol_action(ActionEvent event) throws SQLException {
        scol.deleteColocation(tableviewfav.getSelectionModel().getSelectedItem().getId());
        tableviewfav.setItems(scol.affichercolocation_all());
        
    }

    @FXML
    private void suppcov_action(ActionEvent event) throws SQLException {
        scov.deleteAnnonce(tableviewcov.getSelectionModel().getSelectedItem());
        tableviewcov.setItems(scov.affichercovoiturage());
    }

    @FXML
    private void suppann_action(ActionEvent event) throws SQLException {
        sa.deleteAnnonce(Annonces.getSelectionModel().getSelectedItem().getId());
         ObservableList<Annonce> listeAnimaux = FXCollections.observableArrayList(Annonce.generateImageViews(sa.displayAnnonce()));
          Annonces.setItems(listeAnimaux);
    }

    @FXML
    private void tabview_clicked(MouseEvent event) {
    }

    @FXML
    private void supprec_action(ActionEvent event) throws SQLException {
        sr.deleteReclamation(Reclamations.getSelectionModel().getSelectedItem().getId());
        Reclamations.setItems(sr.displayReclamation());
    }

    @FXML
    private void supptopic_action(ActionEvent event) throws SQLException {
        st.deleteTopic(Topics.getSelectionModel().getSelectedItem().getId());
         Topics.setItems(st.displayTopic());
    }

    @FXML
    private void suppdoc_action(ActionEvent event) throws SQLException {
        sd.SupprimerDocument(tabDocuments.getSelectionModel().getSelectedItem().getId());
        tabDocuments.setItems(sd.ListDoc_all());
    }
public void controlPanes(Pane P){
    col_pane.setVisible(false);
    cov_pane.setVisible(false);
    ann_pane.setVisible(false);
    doc_pane.setVisible(false);
    rec_pane.setVisible(false);
    topic_pane.setVisible(false);
    P.setVisible(true);
}
    @FXML
    private void tabpane_clicked(MouseEvent event) {
      switch (tabpane.getSelectionModel().getSelectedIndex()) {
          case 0:
              controlPanes(col_pane);
              break;
          case 1:
              controlPanes(cov_pane);
              break;
          case 2:
              controlPanes(ann_pane);
              break;
          case 3:
              controlPanes(rec_pane);
              break;
          case 4:
              controlPanes(topic_pane);
              break;
          case 5:
              controlPanes(doc_pane);
              break;
          default:
              break;
      }
   
    }
    
}

package GUI;

import Entities.Commentaire;
import Entities.Session;
import Entities.Topic;
import Services.ServiceTopic;
import Services.ServicesCommentaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class CommentaireTopicController implements Initializable {
ServicesCommentaire sc = new ServicesCommentaire();
    FilteredList<Commentaire> filteredlist=null;
    
    @FXML
    private Pane afficher_pane;
    @FXML
    private Pane ajouter_pane;
    @FXML
    private Pane add_pane;
    @FXML
    private JFXTextField search_field;
    @FXML
    private TableColumn<Commentaire, String> ColmnContenu;
    @FXML
    private TableView<Commentaire> Commentaires;
    @FXML
    private JFXTextArea Contenu_modif_id;
    @FXML
    private JFXTextArea Contenu_id;
    @FXML
    private Pane ConsulterTopicId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ServicesCommentaire sc = new ServicesCommentaire();
        try {
            Commentaires.setItems(sc.CommentaireTopic(IntTopicController.id_top));
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireTopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ColmnContenu.setCellValueFactory(new PropertyValueFactory<>("Contenu"));
    try {
               filteredlist= new FilteredList<>(sc.CommentaireTopic(IntTopicController.id_top),p->true);
    } catch (SQLException ex) {
        Logger.getLogger(CommentaireTopicController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    search_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredlist.setPredicate(commentaire -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    try {
                        Commentaires.setItems(sc.CommentaireTopic(IntTopicController.id_top));
                    } catch (SQLException ex) {
                        Logger.getLogger(CommentaireTopicController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (commentaire.getContenu().toLowerCase().contains(lowerCaseFilter)) 
                    
                    return true; // Filter matches first name.
                else
                return false; // Does not match.
            });
            Commentaires.setItems(filteredlist);
        });
    }    
   

    @FXML
    private void modifier_action(ActionEvent event) {
        if(Contenu_modif_id.getText().equals("")){
 
            Contenu_modif_id.setStyle("-fx-background-color : #F19E9E");
     
      }
        else {
            Commentaire C=Commentaires.getSelectionModel().getSelectedItem();
                              System.out.println(C.getId());

            C.setContenu(Contenu_modif_id.getText());         
             try {
                  sc.MAJCommentaireTopic(C);
              filteredlist= new FilteredList<>(sc.CommentaireTopic(IntTopicController.id_top),p->true);
          } catch (SQLException ex) {
              Logger.getLogger(CommentaireTopicController.class.getName()).log(Level.SEVERE, null, ex);
          }
              try {
            Commentaires.setItems(sc.CommentaireTopic(IntTopicController.id_top));
             Contenu_modif_id.setText("");
             Contenu_modif_id.setStyle(null);
              add_pane.setVisible(true);
              afficher_pane.setVisible(false); 
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireTopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void Supprimer_action(ActionEvent event) {
          Commentaire C=Commentaires.getSelectionModel().getSelectedItem();
    try {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                alert.setTitle("Effacer Commentaire?");
                alert.setHeaderText(null);
                alert.setContentText("Effacer le commentaire sélectionneé?");
                
                 Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
        sc.SupprimerCommentaireTopic(C.getId());
            }
        Commentaires.setItems(sc.CommentaireTopic(IntTopicController.id_top));
        filteredlist= new FilteredList<>(sc.CommentaireTopic(IntTopicController.id_top),p->true);
    } catch (SQLException ex) {
        Logger.getLogger(CommentaireTopicController.class.getName()).log(Level.SEVERE, null, ex);
    }
      Contenu_modif_id.setStyle(null);
        Contenu_modif_id.setText("");
        afficher_pane.setVisible(false);
        add_pane.setVisible(true);
    }

    @FXML
    private void Ajouter_action(ActionEvent event) {
      if(Contenu_id.getText().equals("")){
        
            Contenu_id.setStyle("-fx-background-color : #F19E9E");
      }   
        
      else {
          
          Commentaire C= new Commentaire(Contenu_id.getText(),IntTopicController.id_top,"topic");
          try {
              sc.AjouterCommentaireTopic(C);
              filteredlist= new FilteredList<>(sc.CommentaireTopic(IntTopicController.id_top),p->true);
              try {
            Commentaires.setItems(sc.CommentaireTopic(IntTopicController.id_top));
            Contenu_id.setText("");
                    Contenu_id.setStyle(null);
              add_pane.setVisible(true);
        ajouter_pane.setVisible(false); 
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireTopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
          } catch (SQLException ex) {
              Logger.getLogger(CommentaireTopicController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
            
    }

    @FXML
    private void Annuler_action(ActionEvent event) {
        Contenu_id.setStyle(null);
        Contenu_modif_id.setStyle(null);
        Contenu_modif_id.setText("");
        Contenu_id.setText("");
        add_pane.setVisible(true);
        ajouter_pane.setVisible(false);
    }

    @FXML
    private void add_exited(MouseEvent event) {
        add_pane.setOpacity(0.1f);
    }

    @FXML
    private void add_entered(MouseEvent event) {
        add_pane.setOpacity(0.2f);
    }

    @FXML
    private void add_action(ActionEvent event) {
        add_pane.setVisible(false);
        ajouter_pane.setVisible(true);
        
        
    }

    @FXML
    private void tabview_clicked(MouseEvent event) {
      Commentaire C=  Commentaires.getSelectionModel().getSelectedItem();
      if(C!=null){
          afficher_pane.setVisible(true);
          add_pane.setVisible(false);
          ajouter_pane.setVisible(false);
          Contenu_modif_id.setText(C.getContenu());
          System.out.println(C.getId());
      }
         
    }

    @FXML
    private void close_action(ActionEvent event) {
         Contenu_id.setStyle(null);
        Contenu_id.setText("");
        add_pane.setVisible(true);
        afficher_pane.setVisible(false);
        ajouter_pane.setVisible(false);
    }

    @FXML
    private void AcceuilComm(ActionEvent event) {
        Accueil.changescenes("Interface.fxml");
    }
}
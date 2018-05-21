/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Entities.Session;
import Services.ServiceReclamation;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class IntReclamationController implements Initializable {
    ServiceReclamation sr;
    FilteredList<Reclamation> filteredlist=null;
    @FXML
    private Pane afficher_pane;
    @FXML
    private JFXTextField Sujet_field;
    @FXML
    private JFXTextArea Desc_field;
    @FXML
    private Pane ConsulterReclamationId;
    @FXML
    private TableView<Reclamation> Reclamations;
    @FXML
    private TableColumn<Reclamation,String> ColumnSujetTop;
    @FXML
    private TableColumn<Reclamation,String> ColumnDescTop;
    @FXML
    private JFXTextField search_field;
    @FXML
    private Pane ajouter_pane;
    @FXML
    private JFXTextField Sujet_field1;
    @FXML
    private JFXTextArea Desc_field1;
    @FXML
    private Pane add_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sr = new ServiceReclamation();
        try {
            Reclamations.setItems(sr.displayReclamation());
        } catch (SQLException ex) {
            Logger.getLogger(IntReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ColumnSujetTop.setCellValueFactory(new PropertyValueFactory<>("objet"));
        ColumnDescTop.setCellValueFactory(new PropertyValueFactory<>("description"));
    try {
       filteredlist= new FilteredList<>(sr.displayReclamation(),p->true);
    } catch (SQLException ex) {
        Logger.getLogger(IntReclamationController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    search_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredlist.setPredicate(reclamation -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    try {
                        Reclamations.setItems(sr.displayReclamation());
                    } catch (SQLException ex) {
                        Logger.getLogger(IntReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (reclamation.getObjet().toLowerCase().contains(lowerCaseFilter)) {
                    
                    return true; // Filter matches first name.
                } else if (reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                   
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
            Reclamations.setItems(filteredlist);
        });
    }    
   

    @FXML
    private void modifier_action(ActionEvent event) {
        if(Sujet_field.getText().equals("") || Desc_field.getText().equals("")){
        if(Sujet_field.getText().equals(""))
            Sujet_field.setStyle("-fx-background-color : #F19E9E");
        else Sujet_field.setStyle("-fx-background-color : #BAF3B7");
        if(Desc_field.getText().equals(""))
            Desc_field.setStyle("-fx-background-color : #F19E9E");
        else Desc_field.setStyle("-fx-background-color : #BAF3B7");
      }else {
            
            Reclamation T=Reclamations.getSelectionModel().getSelectedItem();
            T.setObjet(Sujet_field.getText());
            T.setDescription(Desc_field.getText());
         
             try {
              sr.updateReclamation(T);
               filteredlist= new FilteredList<>(sr.displayReclamation(),p->true);
          } catch (SQLException ex) {
              Logger.getLogger(IntReclamationController.class.getName()).log(Level.SEVERE, null, ex);
          }
              
                  Reclamations.refresh();
             Sujet_field.setText("");
             Desc_field.setText("");
             Sujet_field.setStyle(null);
             Desc_field.setStyle(null);
             
              add_pane.setVisible(true);
              afficher_pane.setVisible(false); 
       
        }
    }

    @FXML
    private void Supprimer_action(ActionEvent event) throws SQLException {
          Reclamation T=Reclamations.getSelectionModel().getSelectedItem();
   
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
                alert.setTitle("Effacer Annonce?");
                alert.setHeaderText(null);
                alert.setContentText("Effacer l'annonce sélectionneé?");
                
                 Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
        sr.deleteReclamation(T.getId());
            }
         Reclamations.setItems(sr.displayReclamation());
         filteredlist= new FilteredList<>(sr.displayReclamation(),p->true);
    
     
      Sujet_field.setStyle(null);
        Desc_field.setStyle(null);
        Sujet_field.setText("");
        Desc_field.setText("");
        afficher_pane.setVisible(false);
        add_pane.setVisible(true);
    }

    @FXML
    private void Ajouter_action(ActionEvent event) throws SQLException {
      if(Sujet_field1.getText().equals("") || Desc_field1.getText().equals("")){
        if(Sujet_field1.getText().equals(""))
            Sujet_field1.setStyle("-fx-background-color : #F19E9E");
        else Sujet_field1.setStyle("-fx-background-color : #BAF3B7");
        if(Desc_field1.getText().equals(""))
            Desc_field1.setStyle("-fx-background-color : #F19E9E");
        else Desc_field1.setStyle("-fx-background-color : #BAF3B7");
      }else {
          Reclamation T= new Reclamation(Sujet_field1.getText(), Desc_field1.getText());
          
              sr.AjouterReclamation(T);
               filteredlist= new FilteredList<>(sr.displayReclamation(),p->true);
              
            Reclamations.setItems(sr.displayReclamation());
            Sujet_field1.setText("");
                    Desc_field1.setText("");
                    Sujet_field1.setStyle(null);
                    Desc_field1.setStyle(null);
              add_pane.setVisible(true);
        ajouter_pane.setVisible(false); 
        
      }
      
            
    }

    @FXML
    private void Annuler_action(ActionEvent event) {
        Sujet_field1.setStyle(null);
        Desc_field1.setStyle(null);
        Sujet_field.setText("");
        Desc_field1.setText("");
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
      Reclamation T=  Reclamations.getSelectionModel().getSelectedItem();
      if(T!=null){
          afficher_pane.setVisible(true);
          add_pane.setVisible(false);
          ajouter_pane.setVisible(false);
          Sujet_field.setText(T.getObjet());
          Desc_field.setText(T.getDescription());
          System.out.println(T.getId());
      }
         
    }

    @FXML
    private void close_action(ActionEvent event) {
         Sujet_field.setStyle(null);
        Desc_field.setStyle(null);
        Sujet_field.setText("");
        Desc_field.setText("");
        add_pane.setVisible(true);
        afficher_pane.setVisible(false);
        ajouter_pane.setVisible(false);
    }

    @FXML
    private void AcceuilComm(ActionEvent event) {
        Accueil.changescenes("Interface.fxml");
    }
    
}

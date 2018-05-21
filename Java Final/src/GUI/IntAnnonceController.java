/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Annonce;
import Entities.Session;
import Service.UploadCommunication;
import Services.ServiceAnnonce;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class IntAnnonceController implements Initializable {
    ServiceAnnonce sa = new ServiceAnnonce();
    FilteredList<Annonce> filteredlist=null;
    File selectedfile;
    @FXML
    private Pane afficher_pane;
    @FXML
    private JFXTextField Libelle_field;
    @FXML
    private JFXTextArea Desc_field;
    @FXML
    private TableView<Annonce> Annonces;
    @FXML
    private TableColumn<Annonce, String> ColumnLibelle;
    @FXML
    private TableColumn<Annonce,String> ColumnDesc;
    @FXML
    private JFXTextField search_field;
    @FXML
    private Pane ajouter_pane;
    @FXML
    private JFXTextField Libelle_field1;
    @FXML
    private JFXTextArea Desc_field1;
    @FXML
    private JFXButton btnupid;
    @FXML
    private Label txtpath;
    @FXML
    private Pane add_pane;
    @FXML
    private Pane ConsulterAnnonceId;
    @FXML
    private ImageView imgpdf;
    @FXML
    private JFXButton pdfid;
    @FXML
    private TableColumn<Annonce, ImageView> ColumnPhoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     /*   sa=new ServiceAnnonce();
        try {
            Annonces.setItems(sa.displayAnnonce());
        } catch (SQLException ex) {
            Logger.getLogger(IntAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        ColumnLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        ColumnDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColumnDesc.setCellValueFactory(new PropertyValueFactory<>("photo"));


    try {
       filteredlist= new FilteredList<>(sa.displayAnnonce(),p->true);
    } catch (SQLException ex) {
        Logger.getLogger(IntAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    search_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredlist.setPredicate(topic -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    try {
                        Annonces.setItems(sa.displayAnnonce());
                    } catch (SQLException ex) {
                        Logger.getLogger(IntAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return true;
                }

                // Compare firsa name and lasa name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (topic.getLibelle().toLowerCase().contains(lowerCaseFilter)) {
                    
                    return true; // Filter matches firsa name.
                } else if (topic.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    
                    return true; // Filter matches lasa name.
                }
                return false; // Does not match.
            });
            Annonces.setItems(filteredlist);
        });*/
     afficher();
    } 
    public void afficher(){
         try {

            /////////////////////////
            ServiceAnnonce service = new ServiceAnnonce();


            /* add column to the tableview and set its items */
            ObservableList<Annonce> listeAnimaux = FXCollections.observableArrayList(Annonce.generateImageViews(service.displayAnnonce()));
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
            Logger.getLogger(IntAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@FXML
   private void modifier_action(ActionEvent event) {
        if(Libelle_field.getText().equals("") || Desc_field.getText().equals("")){
        if(Libelle_field.getText().equals(""))
            Libelle_field.setStyle("-fx-background-color : #F19E9E");
        else Libelle_field.setStyle("-fx-background-color : #BAF3B7");
        if(Desc_field.getText().equals(""))
            Desc_field.setStyle("-fx-background-color : #F19E9E");
        else Desc_field.setStyle("-fx-background-color : #BAF3B7");
      }else {
            
            Annonce T=Annonces.getSelectionModel().getSelectedItem();
            T.setLibelle(Libelle_field.getText());
            T.setDescription(Desc_field.getText());
         
             try {
              sa.updateAnnonce(T);
              filteredlist= new FilteredList<>(sa.displayAnnonce(),p->true);
          } catch (SQLException ex) {
              Logger.getLogger(IntAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
          }
              
            Annonces.refresh();
             Libelle_field.setText("");
             Desc_field.setText("");
             Libelle_field.setStyle(null);
             Desc_field.setStyle(null);
             
              add_pane.setVisible(true);
              afficher_pane.setVisible(false); 
      
        }
    }

    @FXML
    private void Supprimer_action(ActionEvent event) {
          Annonce T=Annonces.getSelectionModel().getSelectedItem();
         
    try {
         Alert alert = new Alert(AlertType.CONFIRMATION);
            
                alert.setTitle("Effacer Annonce?");
                alert.setHeaderText(null);
                alert.setContentText("Effacer l'annonce sélectionneé?");
                
                 Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
        sa.deleteAnnonce(T.getId());
            }
         filteredlist= new FilteredList<>(sa.displayAnnonce(),p->true);
         afficher();

    } catch (SQLException ex) {
        Logger.getLogger(IntAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
    }
      Libelle_field.setStyle(null);
        Desc_field.setStyle(null);
        Libelle_field.setText("");
        Desc_field.setText("");
        afficher_pane.setVisible(false);
        add_pane.setVisible(true);
    }

    @FXML
    private void Ajouter_action(ActionEvent event) throws SQLException {
      if(Libelle_field1.getText().equals("") || Desc_field1.getText().equals("")){
        if(Libelle_field1.getText().equals(""))
            Libelle_field1.setStyle("-fx-background-color : #F19E9E");
        else Libelle_field1.setStyle("-fx-background-color : #BAF3B7");
        if(Desc_field1.getText().equals(""))
            Desc_field1.setStyle("-fx-background-color : #F19E9E");
        else Desc_field1.setStyle("-fx-background-color : #BAF3B7");
      }else {
          Annonce T= new Annonce(Libelle_field1.getText(), Desc_field1.getText(),txtpath.getText());
          sa.AjouterAnnonce(T);
          afficher();
      
              
     
      }
      
            
    }

    @FXML
    private void Annuler_action(ActionEvent event) {
        Libelle_field1.setStyle(null);
        Desc_field1.setStyle(null);
        Libelle_field.setText("");
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
      Annonce T=  Annonces.getSelectionModel().getSelectedItem();
      if(T!=null){
          imgpdf.setVisible(true);
          pdfid.setVisible(true);
          afficher_pane.setVisible(true);
          add_pane.setVisible(false);
          ajouter_pane.setVisible(false);
          Libelle_field.setText(T.getLibelle());
          Desc_field.setText(T.getDescription());
          System.out.println(T.getId());
      }
         
    }

    @FXML
    private void close_action(ActionEvent event) {
         Libelle_field.setStyle(null);
        Desc_field.setStyle(null);
        Libelle_field.setText("");
        Desc_field.setText("");
        add_pane.setVisible(true);
        afficher_pane.setVisible(false);
        ajouter_pane.setVisible(false);
    }

    @FXML
    private void btnUploadDoc(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("file", "*.jpg", "*.pdf")
        );
        Stage stage=new Stage();
        selectedfile = fc.showOpenDialog(stage);
        if (selectedfile != null) {
            txtpath.setText(selectedfile.getName());
            UploadCommunication u = new UploadCommunication();
            try {
                u.upload(selectedfile);
                //path_img = selectedfile.getAbsolutePath();
            } catch (IOException ex) {
                Logger.getLogger(IntAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void annoncePdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {
          Annonce T=Annonces.getSelectionModel().getSelectedItem();

       Document pdfReport = new Document();
        PdfWriter.getInstance(pdfReport, new FileOutputStream("Annonces.pdf"));
        pdfReport.open();
        Text t = new Text();
        t.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        t.setX(30);
        pdfReport.add(new Phrase(t.getText()));
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(new Paragraph("Liste des annonces"));
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        PdfPTable my_report_table = new PdfPTable(2);

        PdfPCell tableCellColumn = new PdfPCell(new Phrase("libelle"));
        my_report_table.addCell(tableCellColumn);
        tableCellColumn = new PdfPCell(new Phrase("description"));
        my_report_table.addCell(tableCellColumn);
   

ServiceAnnonce sa = new ServiceAnnonce();
          sa.display1Annonce(T.getId()).forEach(e -> {
         // Evenement e = new Evenement();
            
           

            PdfPCell tableCell = new PdfPCell(new Phrase(e.getLibelle()));
            my_report_table.addCell(tableCell);

            tableCell = new PdfPCell(new Phrase(e.getDescription()));
            my_report_table.addCell(tableCell);

        });
        /* Attach report table to PDF */
        pdfReport.add(my_report_table);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);


        pdfReport.close();

        Alert alertReservation = new Alert(Alert.AlertType.INFORMATION);
        alertReservation.setTitle("Extraction en PDF");
        alertReservation.setHeaderText(null);
        alertReservation.setContentText("Un fichier PDF a été généré");
        alertReservation.showAndWait();
      
        
    }

    @FXML
    private void tabview_exited(MouseEvent event) {
        
        imgpdf.setVisible(false);
          pdfid.setVisible(false);
    }

    @FXML
    private void AcceuilComm(ActionEvent event) {
        
        Accueil.changescenes("Interface.fxml");
    }
    
    }
    
    


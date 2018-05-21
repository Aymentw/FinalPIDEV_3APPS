/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceCol;
import Services.Upload;
import com.jfoenix.controls.JFXButton;
import Entities.Colocation;
import Entities.FavorisCol;
import Services.ServiceFavoris;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import API.SendMailCol;
import API.TwilioSms;
import Entities.Session;
import java.net.URISyntaxException;

/**
 * FXML Controller class
 *
 * @author Karim
 */
public class InterfaceColocationController implements Initializable {

    @FXML
    private ImageView imgacceuil1;
    @FXML
    private ImageView imgacceuil2;
    @FXML
    private ImageView imgacceuil3;
    @FXML
    private JFXButton btn_acceuil;
    @FXML
    private JFXButton btn_mesannonces;
    @FXML
    private JFXButton btn_deconnexion;
    @FXML
    private JFXButton btn_mesfavoris;
    @FXML
    private Pane Ajout_pane;
    @FXML
    private Label adresselab;
    @FXML
    private TextField adressetxt;
    @FXML
    private Label colocspresent;
    @FXML
    private Label typelog;
    @FXML
    private Button deposezannoncebtn;
    @FXML
    private TextArea desc;
    @FXML
    private Button parcourir;
    @FXML
    private TextField prixtxt;
    @FXML
    private Label prixlab;
    @FXML
    private ComboBox<Integer> colocspresenttxt;
    @FXML
    private ComboBox<String> typelogtxt;
    @FXML
    private Pane acceuil_pane;

    /**
     * Initializes the controller class.
     */
    public ObservableList<Integer> listNiveau = FXCollections.observableArrayList(0, 1, 2, 3, 4);
    public ObservableList<String> listNiveau1 = FXCollections.observableArrayList("maison", "studio", "sta7");

    public ObservableList<Integer> listNiveau2 = FXCollections.observableArrayList(1, 2, 3, 4);
    public ObservableList<String> listNiveau3 = FXCollections.observableArrayList("maison", "studio", "sta7");

    public ObservableList<String> listNiveau4 = FXCollections.observableArrayList("F");
    public ObservableList<String> listNiveau5 = FXCollections.observableArrayList("F");

    public ObservableList<Integer> listNiveau6 = FXCollections.observableArrayList(1, 2, 3, 4);

    public ObservableList<String> listNiveau7 = FXCollections.observableArrayList("F");
    public ObservableList<String> listNiveau8 = FXCollections.observableArrayList("maison", "appartement", "studio", "sta7");
ServiceCol c = new ServiceCol();
    ServiceFavoris sf=new ServiceFavoris();
    public ObservableList<Colocation> listcol=null;
    @FXML
    private TextArea desc1;
    @FXML
    private Label nbcolocs;
    @FXML
    private ComboBox<Integer> listecolocs;
    @FXML
    private Label typelog1;
    @FXML
    private Label sexe_lab;
    private TextField sexe_txt;
    @FXML
    private Button chercher_colocation;
    @FXML
    private Pane chercher_pane;
    @FXML
    private TextField txtpath;
    File selectedfile;
    @FXML
    private ComboBox<String> type_log;
    private TextField sexe_ajt;
    @FXML
    private Label sexe_labajt;
    @FXML
    private JFXButton liste_annonce;
    @FXML
    private Pane panetableview;
    @FXML
    private TableColumn<Colocation, String> Tadresse;
    @FXML
    private TableColumn<Colocation, String> Tsexe;
    @FXML
    private TableColumn<Colocation, String> Tprix;
    @FXML
    private TableColumn<Colocation, String> Tplace_dispo;
    @FXML
    private TableColumn<Colocation, String> Ttype_maison;
    @FXML
    private TableColumn<Colocation, String> Tdescription;
    @FXML
    private TableView<Colocation> tableview;
    @FXML
    private JFXButton lstdem;
    @FXML
    private TableView<Colocation> tabviewdem;
    @FXML
    private TableColumn<Colocation, String> sexedem;
    @FXML
    private TableColumn<Colocation, String> nbcolocsdem;
    @FXML
    private TableColumn<Colocation, String> typelogdem;
    @FXML
    private TableColumn<Colocation, String> descdem;
    @FXML
    private Pane panetabviewdem;
    @FXML
    private ComboBox<String> sexeboxdem;
    @FXML
    private ComboBox<String> sexeboxann;
    @FXML
    private TextField txtrechdem;
    @FXML
    private TextField rechavancann;
    @FXML
    private Pane mespane;
    @FXML
    private TableView<Colocation> mestableview;
    @FXML
    private TableColumn<Colocation, String> mesadresse;
    @FXML
    private TableColumn<Colocation, String> messexe;
    @FXML
    private TableColumn<Colocation, String> mesprix;
    @FXML
    private TableColumn<Colocation, String> mesplacedispo;
    @FXML
    private TableColumn<Colocation, String> mestypemaison;
    @FXML
    private TableColumn<Colocation, String> mesdesc;
    @FXML
    private TextField mesanntxt;
    @FXML
    private Button btnchoix;
    @FXML
    private Pane modif_pane;
    @FXML
    private Label adresselab1;
    @FXML
    private TextField adressetxt1;
    @FXML
    private Label colocspresent1;
    @FXML
    private Label typelog2;
    @FXML
    private Button deposezannoncebtn1;
    @FXML
    private TextArea desc2;
    @FXML
    private Button parcourir1;
    @FXML
    private TextField prixtxt1;
    @FXML
    private Label prixlab1;
    @FXML
    private ComboBox<Integer> colocspresenttxt1;
    @FXML
    private ComboBox<String> typelogtxt1;
    @FXML
    private TextField txtpath1;
    @FXML
    private Label sexe_labajt1;
    @FXML
    private ComboBox<String> sexeboxann1;
    @FXML
    private Pane affiche_pane1;
    @FXML
    private Label adresselab11;
    @FXML
    private Label colocspresent11;
    @FXML
    private Label typelog21;
    @FXML
    private Label prixlab11;
    @FXML
    private Label sexe_labajt11;
    @FXML
    private Label addresslab2;
    @FXML
    private Label addresslab21;
    @FXML
    private Label addresslab22;
    @FXML
    private Label addresslab23;
    @FXML
    private Label addresslab24;
    @FXML
    private Label addresslab25;
    @FXML
    private Label adresselab111;
    @FXML
    private Pane panefavoris;
    @FXML
    private TableView<Colocation> tableviewfav;
    @FXML
    private TableColumn<Colocation ,String> adressefav;
    @FXML
    private TableColumn<Colocation ,String> sexefav;
    @FXML
    private TableColumn<Colocation ,String> prixfav;
    @FXML
    private TableColumn<Colocation ,String> placedispofav;
    @FXML
    private TableColumn<Colocation ,String> typemaisonfav;
    @FXML
    private TableColumn<Colocation ,String> descfav;
    @FXML
    private Button btnchoix1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mestableview.refresh();
                tableview.refresh();
        try {
             listcol=c.affichercolocation();
            for (int i = 0; i < listcol.size(); i++) {
                
                listcol.get(i).setStar1(new ImageView(new Image(sf.afficherFavoris(listcol.get(i),Session.getIdThisUser()), 30, 30, true, true)));
                                    }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceColocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
            FilteredList<Colocation> filteredData = new FilteredList<>(c.affichercolocdem(), p -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            txtrechdem.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(colocation -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (colocation.getSexe().toLowerCase().contains(lowerCaseFilter)) {

                        return true; // Filter matches first name.
                    } else if (String.valueOf(colocation.getPlace_dispo()).toLowerCase().contains(lowerCaseFilter)) {

                        return true; // Filter matches last name.
                    } else if (colocation.getType_maison().toLowerCase().contains(lowerCaseFilter)) {

                        return true;
                    }
                    return false; // Does not match.
                });
                tabviewdem.setItems(filteredData);
            });

            FilteredList<Colocation> filteredData1 = new FilteredList<>(listcol, p -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            rechavancann.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData1.setPredicate(colocation -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (colocation.getAdresse().toLowerCase().contains(lowerCaseFilter)) {

                        return true;
                    }
                    if (colocation.getSexe().toLowerCase().contains(lowerCaseFilter)) {

                        return true; // Filter matches first name.
                    } else if (String.valueOf(colocation.getPrix()).toLowerCase().contains(lowerCaseFilter)) {

                        return true; // Filter matches last name.
                    } else if (String.valueOf(colocation.getPlace_dispo()).toLowerCase().contains(lowerCaseFilter)) {

                        return true; // Filter matches last name.
                    } else if (colocation.getType_maison().toLowerCase().contains(lowerCaseFilter)) {

                        return true;
                    }
                    return false; // Does not match.
                });
                tableview.setItems(filteredData1);
            });

            FilteredList<Colocation> filteredData2 = new FilteredList<>(c.affichercolocation1(Session.getIdThisUser()), p -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            mesanntxt.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData2.setPredicate(colocation -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (colocation.getAdresse().toLowerCase().contains(lowerCaseFilter)) {

                        return true;
                    }
                    if (colocation.getSexe().toLowerCase().contains(lowerCaseFilter)) {

                        return true; // Filter matches first name.

                    } else if (String.valueOf(colocation.getPrix()).toLowerCase().contains(lowerCaseFilter)) {

                        return true; // Filter matches last name.
                    } else if (String.valueOf(colocation.getPlace_dispo()).toLowerCase().contains(lowerCaseFilter)) {

                        return true; // Filter matches last name.
                    } else if (colocation.getType_maison().toLowerCase().contains(lowerCaseFilter)) {

                        return true;
                    }
                    return false; // Does not match.
                });
                mestableview.setItems(filteredData2);
            });

            colocspresenttxt.setValue(0);
            colocspresenttxt.setItems(listNiveau);

            typelogtxt.setValue("appartement");
            typelogtxt.setItems(listNiveau1);

            listecolocs.setValue(1);
            listecolocs.setItems(listNiveau2);

            type_log.setValue("appartement");
            type_log.setItems(listNiveau3);
            sexeboxdem.setValue("H");
            sexeboxdem.setItems(listNiveau4);
            sexeboxann.setValue("H");
            sexeboxann.setItems(listNiveau5);
            colocspresenttxt1.setValue(1);
            colocspresenttxt1.setItems(listNiveau6);

            sexeboxann1.setValue("H");
            sexeboxann1.setItems(listNiveau7);
            typelogtxt1.setValue("");
            typelogtxt1.setItems(listNiveau8);
            
                try {
                    imgacceuil1.setImage(new Image(getClass().getResource("/Resources/imgacceuil1.jpg").toURI().toString()));
               
               imgacceuil2.setImage(new Image(getClass().getResource("/Resources/imgacceuil2.jpg").toURI().toString()));
                imgacceuil3.setImage(new Image(getClass().getResource("/Resources/imgacceuil3.jpg").toURI().toString()));
 } catch (URISyntaxException ex) {
                    Logger.getLogger(InterfaceColocationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            Ajout_pane.setVisible(false);
            acceuil_pane.setVisible(true);
            chercher_pane.setVisible(false);
            panetableview.setVisible(false);
            panetabviewdem.setVisible(false);
            mespane.setVisible(false);
            modif_pane.setVisible(false);
            affiche_pane1.setVisible(false);
            panefavoris.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceColocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(listcol);
            Tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            Tsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            Tprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            Tplace_dispo.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));
            Ttype_maison.setCellValueFactory(new PropertyValueFactory<>("type_maison"));
            Tdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableColumn<Colocation, ImageView> firstColumn = new TableColumn<Colocation, ImageView>("Favoris");
            firstColumn.setCellValueFactory(new PropertyValueFactory<Colocation, ImageView>("star1"));
            
            

            firstColumn.setPrefWidth(60);

            /* add column to the tableview and set its items */
            tableview.getColumns().add(firstColumn);
    }

    @FXML
    void Chercher_action(ActionEvent event) {
        acceuil_pane.setVisible(false);
        Ajout_pane.setVisible(false);
        chercher_pane.setVisible(true);
        panetableview.setVisible(false);
        panetabviewdem.setVisible(false);
        mespane.setVisible(false);
        modif_pane.setVisible(false);
        affiche_pane1.setVisible(false);
        panefavoris.setVisible(false);

    }

    @FXML
    private void proposer_action(ActionEvent event) {
        acceuil_pane.setVisible(false);
        Ajout_pane.setVisible(true);
        chercher_pane.setVisible(false);
        panetableview.setVisible(false);
        panetabviewdem.setVisible(false);
        mespane.setVisible(false);
        modif_pane.setVisible(false);
        affiche_pane1.setVisible(false);
        panefavoris.setVisible(false);

    }

    @FXML
    private void AjouterColocation(ActionEvent event) throws SQLException, IOException {
        if (!isAnumber(prixtxt.getText())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier votre prix");
            alert.show();
        } else {
            System.out.println("Number : D ");
            if (adressetxt.getText().isEmpty() || prixtxt.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("veuillez saisir des données valide");
                alert.show();
            }
            Colocation c = new Colocation("annonce", adressetxt.getText(), sexeboxann.getSelectionModel().getSelectedItem(), Float.valueOf(prixtxt.getText()), colocspresenttxt.getSelectionModel().getSelectedItem(), typelogtxt.getSelectionModel().getSelectedItem(), desc.getText(), txtpath.getText());
            ServiceCol sc = new ServiceCol();
            try {
                sc.AjouterColocation(c,Session.getIdThisUser());
                mestableview.refresh();
                tableview.refresh();
                Upload u = new Upload();
                u.upload(selectedfile);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceColocation.fxml"));
                Parent root = loader.load();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceColocationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean isAnumber(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    @FXML
    private void Cherchercolocation(ActionEvent event) throws IOException {
        Colocation c = new Colocation("demande", sexeboxdem.getSelectionModel().getSelectedItem(), listecolocs.getSelectionModel().getSelectedItem(), type_log.getSelectionModel().getSelectedItem(), desc1.getText());

        ServiceCol sc = new ServiceCol();
        try {
            sc.ChercherColocation(c);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceColocation.fxml"));
            Parent root = loader.load();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceColocationController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void parouriraction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("file", "*.jpg", "*.pdf")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            txtpath.setText(selectedfile.getName());

        }
    }

//    @FXML
//    private void listerannonce(ActionEvent event) {
//        ServiceCol col = new ServiceCol();
//        ObservableList<Colocation> colocations = (ObservableList<Colocation>) col.getColocations();
//        panelistview.setVisible(true);
//        
//        listviewann.setEditable(true);
//        listviewann.accessibleTextProperty().addListener((observable) -> {ObservableList<Colocation> colocations1 = colocations;
//        });
//    }
    @FXML
    private void listerannonce(ActionEvent event) {

        panetableview.setVisible(true);
        acceuil_pane.setVisible(false);
        Ajout_pane.setVisible(false);
        chercher_pane.setVisible(false);
        panetabviewdem.setVisible(false);
        mespane.setVisible(false);
        modif_pane.setVisible(false);
        affiche_pane1.setVisible(false);
        panefavoris.setVisible(false);
        //   Colocation star1 = new Colocation(new ImageView(new Image("file:\\\\\\C:\\Users\\Karim\\Documents\\NetBeansProjects\\Essaie\\src\\star1.png")));
        //  Colocation item_2 = new Colocation(new ImageView(new Image("file:\\\\\\C:\\Users\\Karim\\Documents\\NetBeansProjects\\Essaie\\src\\star0.png")));

        
            ServiceCol c = new ServiceCol();
            
            
                                    
        
            

        
    }

    @FXML
    private void listerdemande(ActionEvent event) {
        panetabviewdem.setVisible(true);
        panetableview.setVisible(false);
        acceuil_pane.setVisible(false);
        Ajout_pane.setVisible(false);
        chercher_pane.setVisible(false);
        mespane.setVisible(false);
        modif_pane.setVisible(false);
        affiche_pane1.setVisible(false);
        panefavoris.setVisible(false);

        try {

            ServiceCol c = new ServiceCol();
            tabviewdem.setItems(c.affichercolocdem());

            sexedem.setCellValueFactory(new PropertyValueFactory<>("sexe"));

            nbcolocsdem.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));
            typelogdem.setCellValueFactory(new PropertyValueFactory<>("type_maison"));
            descdem.setCellValueFactory(new PropertyValueFactory<>("description"));
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceColocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mesannonce(ActionEvent event) {
        mespane.setVisible(true);
        panetabviewdem.setVisible(false);
        panetableview.setVisible(false);
        acceuil_pane.setVisible(false);
        Ajout_pane.setVisible(false);
        chercher_pane.setVisible(false);
        modif_pane.setVisible(false);
        affiche_pane1.setVisible(false);
        panefavoris.setVisible(false);

        try {

            ServiceCol c = new ServiceCol();
            mestableview.setItems(c.affichercolocation1(Session.getIdThisUser()));
            mesadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            messexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            mesprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            mesplacedispo.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));
            mestypemaison.setCellValueFactory(new PropertyValueFactory<>("type_maison"));
            mesdesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceColocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnchoisir(ActionEvent event) {
        TwilioSms sms = new TwilioSms();

        Colocation colocation = tableview.getSelectionModel().getSelectedItem();
        /*ServiceCol col = new ServiceCol();
        col.deleteColocation(colocation.getId());*/

        sms.sendSms("l'etudiant "+Session.getUsernameThisUser()+" a choisi l'annonce dont l'addresse est " + colocation.getAdresse());
        SendMailCol sm = new SendMailCol();
        sm.envoyer("mohamedkarimfenni@gmail.com", "l'etudiant "+Session.getUsernameThisUser()+" a choisi l'annonce dont l'addresse est " + colocation.getAdresse());
    }

    @FXML
    private void messup(ActionEvent event) {
        Colocation colocation = mestableview.getSelectionModel().getSelectedItem();
        // mestableview.getItems().remove(colocation.getId());
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Effacer Annonce?");
        alert.setHeaderText(null);
        alert.setContentText("Effacer l'annonce sélectionneé?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // mestableview.getItems().remove(colocation.getId());
            ServiceCol col = new ServiceCol();
            col.deleteColocation(colocation.getId());
        }

//     ;
    }

    @FXML
    private void acceuilbtn(ActionEvent event) {
        acceuil_pane.setVisible(true);
        mespane.setVisible(false);
        panetabviewdem.setVisible(false);
        panetableview.setVisible(false);

        Ajout_pane.setVisible(false);
        chercher_pane.setVisible(false);
        modif_pane.setVisible(false);
        affiche_pane1.setVisible(false);
        panefavoris.setVisible(false);

    }

    @FXML
    private void affichercontenu(ActionEvent event) {
        panefavoris.setVisible(false);
        modif_pane.setVisible(false);
        acceuil_pane.setVisible(false);
        mespane.setVisible(false);
        panetabviewdem.setVisible(false);
        panetableview.setVisible(false);
        affiche_pane1.setVisible(true);

        Ajout_pane.setVisible(false);
        chercher_pane.setVisible(false);
        Colocation c = tableview.getSelectionModel().getSelectedItem();
        addresslab2.setText(c.getAdresse());
        addresslab21.setText(String.valueOf(c.getPrix()));
        addresslab22.setText(c.getSexe());
        addresslab23.setText(String.valueOf(c.getPlace_dispo()));
        addresslab24.setText(c.getType_maison());
        addresslab25.setText(c.getDescription());

    }

    @FXML
    private void modifierannonce(ActionEvent event) throws IOException {
        modif_pane.setVisible(true);
        acceuil_pane.setVisible(false);
        mespane.setVisible(false);
        panetabviewdem.setVisible(false);
        panetableview.setVisible(false);
        Ajout_pane.setVisible(false);
        chercher_pane.setVisible(false);
         panefavoris.setVisible(false);
        
        
        Colocation c = mestableview.getSelectionModel().getSelectedItem();

        adressetxt1.setText(c.getAdresse());
        prixtxt1.setText(String.valueOf(c.getPrix()));
        sexeboxann1.setValue(c.getSexe());
        colocspresenttxt1.setValue(c.getPlace_dispo());
        typelogtxt1.setValue(c.getType_maison());
        txtpath1.setText(c.getPath());
        desc2.setText(c.getDescription());
    }

    @FXML
    private void parcouriraction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("file", "*.jpg", "*.pdf")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            txtpath1.setText(selectedfile.getName());

        }
    }

    @FXML
    private void validermodif(ActionEvent event) throws IOException, SQLException {
        Colocation oc = mestableview.getSelectionModel().getSelectedItem();
        Colocation nc = new Colocation(oc.getId(), "annonce", adressetxt1.getText(), sexeboxann1.getSelectionModel().getSelectedItem(), Float.valueOf(prixtxt1.getText()), colocspresenttxt1.getSelectionModel().getSelectedItem(), typelogtxt1.getSelectionModel().getSelectedItem(), desc2.getText(), txtpath1.getText());

        ServiceCol sc = new ServiceCol();
        sc.updateCol(nc);
        Upload u = new Upload();
        u.upload(selectedfile);
        mestableview.refresh();
    }

    @FXML
    private void favorisbtn(ActionEvent event) throws SQLException {
        panefavoris.setVisible(true);
         panetableview.setVisible(false);
        acceuil_pane.setVisible(false);
        Ajout_pane.setVisible(false);
        chercher_pane.setVisible(false);
        panetabviewdem.setVisible(false);
        mespane.setVisible(false);
        modif_pane.setVisible(false);
        affiche_pane1.setVisible(false);
        
        tableviewfav.setItems(c.afficherfavoris());
            adressefav.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            sexefav.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            prixfav.setCellValueFactory(new PropertyValueFactory<>("prix"));
            placedispofav.setCellValueFactory(new PropertyValueFactory<>("place_dispo"));
            typemaisonfav.setCellValueFactory(new PropertyValueFactory<>("type_maison"));
            descfav.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    private void btnajoutfav(ActionEvent event) throws SQLException, URISyntaxException {
        if(tableview.getSelectionModel().getSelectedItem()!=null){
            if(sf.afficherFavoris( tableview.getSelectionModel().getSelectedItem(),Session.getIdThisUser()).equals("file:\\\\\\C:\\Users\\ASUS-PC\\Desktop\\Esprit\\3eme annee\\pidev\\Java\\src\\Resources\\star0.png")){
            tableview.getSelectionModel().getSelectedItem().getStar1()
                    .setImage(new Image(getClass().getResource("/Resources/star1.png").toURI().toString(), 30, 30, true, true));
            sf.ajouterfavoris( tableview.getSelectionModel().getSelectedItem());
        }else  {
             tableview.getSelectionModel().getSelectedItem().getStar1()
                    .setImage(new Image(getClass().getResource("/Resources/star0.png").toURI().toString(), 30, 30, true, true));
            sf.supprimerfavoris( tableview.getSelectionModel().getSelectedItem());
        }
        
    }
    }
}

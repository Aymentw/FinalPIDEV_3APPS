/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class InterfaceController implements Initializable {

    @FXML
    private Pane topic_pane;
    @FXML
    private JFXButton topic_btn;
    @FXML
    private Pane ann_pane;
    @FXML
    private JFXButton ann_btn;
    @FXML
    private Pane rec_pane;
    @FXML
    private JFXButton rec_btn;
    @FXML
    private ImageView topic_img;
    @FXML
    private ImageView ann_img;
    @FXML
    private ImageView rec_img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            topic_img.setImage(new Image(getClass().getResource("/Resources/topic.png").toURI().toString(), 180,180, true, true));
           ann_img.setImage(new Image(getClass().getResource("/Resources/annonce.png").toURI().toString(), 180,180, true, true));
           rec_img.setImage(new Image(getClass().getResource("/Resources/reclamation.png").toURI().toString(), 180,180, true, true));
           
        
        } catch (URISyntaxException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void topic_exited(MouseEvent event) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), topic_pane);
         tt.setFromY(0);
         tt.setToY(10);
         tt.play();
         topic_pane.setStyle("-fx-background-color :  #E89595;-fx-border-color : white");
      
        
    }

    @FXML
    private void topic_entered(MouseEvent event) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), topic_pane);
         tt.setFromY(0);
         tt.setToY(-10);
         tt.play();
         topic_pane.setStyle("-fx-background-color : white;-fx-border-color : #EB7E7E");  
    }

    @FXML
    private void ann_exited(MouseEvent event) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), ann_pane);
         tt.setFromY(0);
         tt.setToY(10);
         tt.play();
         ann_pane.setStyle("-fx-background-color :  #E89595;-fx-border-color : white");
        
    }

    @FXML
    private void ann_entered(MouseEvent event) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), ann_pane);
         tt.setFromY(0);
         tt.setToY(-10);
         tt.play();
         ann_pane.setStyle("-fx-background-color : white;-fx-border-color : #EB7E7E");
         
    }

    @FXML
    private void rec_exited(MouseEvent event) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), rec_pane);
         tt.setFromY(0);
         tt.setToY(10);
         tt.play();
         rec_pane.setStyle("-fx-background-color :  #E89595;-fx-border-color : white");
         
    }

    @FXML
    private void rec_entered(MouseEvent event) {
         TranslateTransition tt = new TranslateTransition(Duration.millis(100), rec_pane);
         tt.setFromY(0);
         tt.setToY(-10);
         tt.play();
         rec_pane.setStyle("-fx-background-color : white;-fx-border-color : #EB7E7E");
        
    }
     public FadeTransition FadeOut(Node N){
    FadeTransition ft = new FadeTransition(Duration.millis(500), N);
ft.setFromValue(1.0);
ft.setToValue(0);

return ft;
}
      public ScaleTransition ScaleUp(Node N){
   ScaleTransition st = new ScaleTransition(Duration.millis(700), N);
   st.setByX(2.5f);
   st.setByY(0.05f);
  return st;
}
     

    @FXML
    private void topic_action(ActionEvent event) {
         ParallelTransition pt = new ParallelTransition(FadeOut(ann_pane),FadeOut(rec_pane));
       EventHandler pt1finish= new EventHandler() {
             @Override
             public void handle(Event event) {
                 Accueil.changescenes("IntTopic.fxml");
             }
         };
         EventHandler ttfinish= new EventHandler() {
             @Override
             public void handle(Event event) {
                 ParallelTransition pt1 = new ParallelTransition(FadeOut(topic_pane),ScaleUp(topic_pane));
             pt1.setOnFinished(pt1finish);
                 pt1.play();
             }
         };
         EventHandler ptfinish= new EventHandler() {
             @Override
             public void handle(Event event) {
             TranslateTransition tt = new TranslateTransition(Duration.millis(500), topic_pane);
         tt.setFromX(0);
         tt.setToX(300);
         tt.setOnFinished(ttfinish);
         tt.play();
             }
         };
         pt.setOnFinished(ptfinish);
        pt.play();
 }

    @FXML
    private void ann_action(ActionEvent event) {
        ParallelTransition pt = new ParallelTransition(FadeOut(topic_pane),FadeOut(rec_pane));
       EventHandler pt1finish= new EventHandler() {
             @Override
             public void handle(Event event) {
               Accueil.changescenes("IntAnnonce.fxml");
             }
         };
        
         EventHandler ptfinish= new EventHandler() {
             @Override
             public void handle(Event event) {
            ParallelTransition pt1 = new ParallelTransition(FadeOut(ann_pane),ScaleUp(ann_pane));
            pt1.setOnFinished(pt1finish);
            pt1.play();
             }
         };
         pt.setOnFinished(ptfinish);
        pt.play();
    }

    @FXML
    private void rec_action(ActionEvent event) {
        ParallelTransition pt = new ParallelTransition(FadeOut(ann_pane),FadeOut(topic_pane));
       EventHandler pt1finish= new EventHandler() {
             @Override
             public void handle(Event event) {
                 Accueil.changescenes("IntReclamation.fxml");
             }
         };
         EventHandler ttfinish= new EventHandler() {
             @Override
             public void handle(Event event) {
                 ParallelTransition pt1 = new ParallelTransition(FadeOut(rec_pane),ScaleUp(rec_pane));
            pt1.setOnFinished(pt1finish);
                 pt1.play();
             }
         };
         EventHandler ptfinish= new EventHandler() {
             @Override
             public void handle(Event event) {
             TranslateTransition tt = new TranslateTransition(Duration.millis(500), rec_pane);
         tt.setFromX(0);
         tt.setToX(-300);
         tt.setOnFinished(ttfinish);
         tt.play();
             }
         };
         pt.setOnFinished(ptfinish);
        pt.play();
    }
    
}

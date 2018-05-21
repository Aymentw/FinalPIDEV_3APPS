/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mechlaoui
 */
public class Accueil extends Application {
    static Stage stage;
    @Override
    public void start(Stage primarystage) throws IOException {

        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        stage=primarystage;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public static  void changescenes(String sceneName){
       
    try {
        Parent root = FXMLLoader.load(Accueil.class.getResource(sceneName));
       
        stage.setScene(new Scene(root));
    } catch (IOException ex) {
        Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }
    
}

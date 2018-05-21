/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Tech.InputValidation;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Mechlaoui
 */
public class Testregex {
    public static void main(String[] args) {
        String s="10:30";
        String str = InputValidation.Heure(s) ? "ok" : "no";
        System.out.println(str);
        
        
        IntegerProperty intProperty = new SimpleIntegerProperty(1024);
    final ChangeListener changeListener = new ChangeListener() {
      @Override
      public void changed(ObservableValue observableValue, Object oldValue,
          Object newValue) {
        System.out.println("oldValue:"+ oldValue + ", newValue = " + newValue);
      }

    };

    intProperty.addListener(changeListener);
    intProperty.set(5120);
    }
    
}

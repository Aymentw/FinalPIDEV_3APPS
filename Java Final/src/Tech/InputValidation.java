/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tech;

/**
 *
 * @author Mechlaoui
 */
public class InputValidation {
    private String chaine;
    private int entier;
    
    public InputValidation(){
        
    }
    
    public static boolean Alphabet (String chaine){
      String regex="[A-Za-z\\s]+";      
      return chaine.matches(regex);
    }
    
    public static boolean Numeric(String entier){
          String regex="^\\d{1,10}$";      
          return entier.matches(regex);
        
    }
    
    public static boolean AlphaNumeric(String chaine){
        String regex="^[a-zA-Z0-9 ]+$";
        
        return chaine.matches(regex);
    }
    
    public static boolean Heure(String heure){        
        String regex="([01]?[0-9]|2[0-3]):[0-5][0-9]";
        return heure.matches(regex);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Mechlaoui
 */
public class bcrypTest {
    public static void main(String[] args) {
        String s = "Azerty@12";
        System.out.println(BCrypt.hashpw(s, BCrypt.gensalt(13)));
        if (BCrypt.checkpw(s,"$2a$13$soJIjGzkBUKBCe2Gu3BpEeTW0Rq/JD8E9bhRr1kBHVqc81031NM/2")){
            System.out.println("OK");
        }
        else {
            System.out.println("Non");
        }
    }
    
}

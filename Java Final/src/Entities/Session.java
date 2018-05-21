/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Houbal
 */
public class Session {
     private static int  IdThisUser=0;
     private static String UsernameThisUser="NoUser";
     private static Date DateThisDay;    
     private static String RoleThisUser="NoRole";

   
    public static Date getDateThisDay() {
        return DateThisDay;
    }

    public static void setDateThisDay(Date DateThisDay) {
        Session.DateThisDay = DateThisDay;
    }

    public static int getIdThisUser() {
        return IdThisUser;
    }

    public static void setIdThisUser(int IdThisUser) {
        Session.IdThisUser = IdThisUser;
    }
    public static String getUsernameThisUser() {
        return UsernameThisUser;
    }

    public static void setUsernameThisUser(String UsernameThisUser) {
        Session.UsernameThisUser = UsernameThisUser;
    }
    
    public static String getRoleThisUser() {
        return RoleThisUser;
    }

    public static void setRoleThisUser(String UsernameThisUser) {
        Session.RoleThisUser = UsernameThisUser;
    }
    
     
}

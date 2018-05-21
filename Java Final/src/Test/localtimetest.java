/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 *
 * @author Mechlaoui
 */
public class localtimetest {
    public static void main(String[] args) {
        String s ="16:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime dateTime = LocalTime.parse(s, formatter);
        LocalDateTime t = LocalDateTime.of(LocalDate.now(), dateTime);
        System.out.println(t);
    }
    
}

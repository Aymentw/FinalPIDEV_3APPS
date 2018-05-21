/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;


import Services.ServiceCol;
import Tech.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Manai
 */
public class SendMailCol {
    
    public Connection cnx= DataSource.GetInstance().GetConnection() ;
    public Statement st;
    
    public SendMailCol()
    {
        try 
        {
            st=cnx.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServiceCol.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    private String username = "karimfenni3@gmail.com";
    private String password = "24273476";
    
    public void envoyer(String destinataire, String msg)
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() 
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(username, password);
            }
        });
    
        try 
        {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("karimfenni3@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(destinataire));
            message.setSubject("Test email");
            
            message.setText(msg);
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
        } 
        catch (MessagingException e) 
        {
            throw new RuntimeException(e);
        } 
    }
}


package Mailer;
/*
 * Clase que envia y recibe los correos
 */

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;  
import javax.mail.Session;
import javax.mail.Transport;
 

public class Mailer {
	
	public Mailer() {}
	
	public static boolean send(String from,String password,String to,String sub,String msg){  
        //Crear propiedades    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //Crear Session   
        Session session = getAuthentication(props, from, password);
        //Creacion MimeMessage (message object)
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //Enviar mensage
         Transport.send(message);  
         return true;
        } catch (MessagingException e) {
        	System.out.println("Error in send message to Gmail Server, " + e.getMessage());
        	return false;
        }    
	}
	
	//Comprobar datos de usuario
	private static Session getAuthentication(Properties properties, String user, String password) {
		return Session.getDefaultInstance(properties,    
		         new Authenticator() {
   	    protected PasswordAuthentication getPasswordAuthentication() {
   	        return new PasswordAuthentication(user, password);
   	    }
   });  
	}
}

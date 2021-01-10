package Mailer;
/*
 * Clase que envia y recibe los correos
 */

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;  
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;

import java.util.ArrayList;
import java.util.Arrays;


import javax.mail.Address;
 

public class Mailer {
	
	public Mailer() {}
	
	/*
	 * Metodo que envie el correo
	 */
	public static boolean send(String from,String password,String to,String sub,String msg)
			throws MessagingException {  
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
        
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //Enviar mensage
         Transport.send(message);  
         return true;   
	}	
	
	/*
	 * Metodo que devuelve los correos no leidos
	 */
	public static ArrayList<Models.Message> readInboundEmails(String user,
		      String password) throws MessagingException{
		ArrayList<Models.Message> recievedMessages = new ArrayList<Models.Message>();
		Session session = Session.getDefaultInstance(new Properties( ));
	    Store store = session.getStore("imaps");
	    store.connect("imap.googlemail.com", 993, user, password);
	    Folder inbox = store.getFolder( "INBOX" );
	    inbox.open( Folder.READ_ONLY );

	    // Recoger los mensajes no leidos
	    Message[] messages = inbox.search(
	        new FlagTerm(new Flags(Flags.Flag.SEEN), false));

	    // Ordenar los mensajes (el primero sera más reciente)
	    Arrays.sort( messages, ( m1, m2 ) -> {
	      try {
	        return m2.getSentDate().compareTo( m1.getSentDate() );
	      } catch ( MessagingException e ) {
	        throw new RuntimeException( e );
	      }
	    } );

	    for ( Message message : messages ) {
	      try {
			try {
				String messageBody = getTextFromMessage(message);
				String from = ((InternetAddress)((Address)(message.getFrom()[0]))).getAddress();
				recievedMessages.add(new Models.Message(message.getSubject(), messageBody, message.getReceivedDate(), from));
			} catch (IOException e) {
				System.out.println("Error, in readInboundEmails (IOException) " + e.getMessage());
			}
		} catch (MessagingException e) {
			System.out.println("Error, in readInboundEmails (MessagingException) " + e.getMessage());
		}
	    }
	    
	    return recievedMessages;
	  }
	
	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private static String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break;
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
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

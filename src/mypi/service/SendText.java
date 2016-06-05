/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypi.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * http://www.emailtextmessages.com/
 *
 *
 */
public class SendText {

    private String to, eventThatIsToday;
    private final String EMAIL = "";//enter your email here
    private final String EMAIL_PASSWORD = "";//enter your email password here

    public SendText(String to, String eventThatIsToday) {
        this.to = to;
        this.eventThatIsToday = eventThatIsToday;
    }


    public void sendText() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, EMAIL_PASSWORD);//change accordingly  
            }
        });
        try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(EMAIL));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Now set the actual message
                message.setText("REMINDER FROM TRAVIS'S INVENTION! " + eventThatIsToday);

                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
            
            
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}

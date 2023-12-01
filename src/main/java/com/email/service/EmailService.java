package com.email.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String subject, String message, String to) {

        boolean f = false;

        //From Email
        String from = "mca09jitendra@gmail.com";


        //Variable for gmail
        String host = "smtp.gmail.com";

        // get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties: " + properties);

        // setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");


        // Step 1: get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("*****@gmail.com", "yhey pvbd rqqy axwo");
            }
        });
        session.setDebug(true);
        //Set 2: compose the message [text, multimedia]
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            //from email
            mimeMessage.setFrom(from);
            //adding recipient to message
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //adding subject to message
            mimeMessage.setSubject(subject);

            //adding to text
            mimeMessage.setText(message);

            // Send
            //Setp 3: send the message using Trsanport class
            Transport.send(mimeMessage);
            System.out.println("Sent Success ...............");
        f=true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

}

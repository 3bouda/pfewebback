package com.rh.backend.service;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.MessagingException;
import javax.mail.Transport;


public class JavaMailSender {

    public void sendEmail(String email,String objet,String emailText) {

        
        final String username = "skytek461@gmail.com";
        final String password = "sdywconvwioflqmc";

        // SimpleMailMessage msg = new SimpleMailMessage();
        // msg.setFrom("skytek461@gmail.com");
        // msg.setTo(email);

        // msg.setSubject("Bienvenue dans notre équipe");
        // msg.setText("Nous sommes très heureux de vous avoir dans notre groupe !\n Nous croyons que vous pouvez utiliser vos compétences et vos talents pour aider notre entreprise à atteindre de nouveaux sommets. \n votre mot de passe est:"+motDePasse+"\n Vous pouvez connecté à notre app.\n Bienvenue à bord ! ");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");

        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.port","587");

        prop.put("mail.smtp.connectiontimeout","5000");
        prop.put("mail.smtp.timeou","5000");
        prop.put("mail.smtp.writetimeout","5000");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("skytek461@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    message.setSubject(objet);
                    message.setText(emailText);
        
                    Transport.send(message);
        
                    System.out.println("Done");
        
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
    }
    
}

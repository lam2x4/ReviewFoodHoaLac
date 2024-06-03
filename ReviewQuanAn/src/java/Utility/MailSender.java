/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.util.Random;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailSender {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final String MAIL = "anhtthe182190@fpt.edu.vn";      // Sender's email
    private final String PASSWORD = "phdi fbmt nptp zhjx";  // Sender's email password
    private final String SUBJECT = "Verification code for Restaurant Review Website";

    public void sentEmail(String toEmail, String text) {
        
        // Config
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        // Authenticator
        Session session = Session.getInstance(props,
            new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(MAIL, PASSWORD);
                }
            });

        // Mail info
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(SUBJECT);
            message.setText(text);

            Transport.send(message);           
            System.out.println("Message sent successfully...");
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String getVifificationCode(){
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(ALPHABET.length());
            code += ALPHABET.charAt(index);
        }
        return code;
    }

    public static void main(String[] args) {
    String toEmail = "llstylish0810@gmail.com";
    String subject = "[JavaMail] - Demo sent email";
    String text = "Thank you for using JavaMail";
    new MailSender().sentEmail(toEmail, text);
}
}

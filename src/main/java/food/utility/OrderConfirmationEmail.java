package food.utility;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OrderConfirmationEmail {

    public static void sendOrderConfirmationEmail(String recipientEmail, String subject, String body) {
        String smtpHostServer = "smtp.gmail.com";
        String senderEmail = "prajwaldp16@gmail.com"; // Replace with your Gmail email

        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHostServer);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, "dmgg ezig gmvo xutb"); // Replace with your Gmail email and password
            }
        });

        sendEmail(session, senderEmail, recipientEmail, subject, body);
    }

    private static void sendEmail(Session session, String fromEmail, String toEmail, String subject, String body) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent successfully to " + toEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

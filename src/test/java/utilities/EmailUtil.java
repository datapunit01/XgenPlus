package utilities;

import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.Authenticator;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailUtil {

    public static void sendLogsByEmail(String logFilePath) throws IOException {
    	
    	Properties p = new Properties();
    	FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
        final String fromEmail = p.getProperty("emailForLog");
        final String password = p.getProperty("passwordForLog"); // NOT normal Gmail password
        final String toEmail = p.getProperty("reciverMail");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("Automation Execution Logs");

            // Email body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(
                    "Hi Team,\n\n" +
                    "Automation execution completed successfully.\n" +
                    "Please find the attached log file.\n\n" +
                    "Regards,\nAutomation Bot"
            );

            // Attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(logFilePath));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("📧 Logs emailed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

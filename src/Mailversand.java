/*
 * Informatikprojekt aus 2019. Erstellt von Simon, Max, Nico am 24.03.19 22:23.
 * Zuletzt bearbeitet 23.03.19 18:07.
 * Keiner klaut das hier! (c) 2019.
 */

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Mailversand {
    public static void main(String[] args) { //args: 0: Empfänger, 1: Veranstaltungsname
        //Auslesen von MailserverSettings.txt
        String txtfrom, txtusername, txtpassword, txthost, txtport;
        String[] arr = new String[0];
        for (int i = 0; i < 4; i++) {
            arr = Mailversand.txtLesen();
            //System.out.println(arr[i]);
        }
        txtfrom = arr[0].replaceAll("\\bfrom: \\b", "");
        txtusername = arr[1].replaceAll("\\busername: \\b", "");
        txtpassword = arr[2].replaceAll("\\bpassword: \\b", "");
        txthost = arr[3].replaceAll("\\bhost: \\b", "");
        txtport = arr[4].replaceAll("\\bport: \\b", "");


        // Recipient's email ID needs to be mentioned.
        String to = args[0];

        // Sender's email ID needs to be mentioned
        String from = txtfrom;

        final String username = txtusername;//change accordingly
        final String password = txtpassword;//change accordingly


        String host = txthost;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", txtport);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("[TIKKET.] " + args[1] + ": Vielen Dank für ihre Bestellung!");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("Ihr digitales Ticket finden Sie im Anhang.");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "./MyBarcode.png";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("E-Ticket by tikket. (working title)");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] txtLesen(){
        String[] serverdaten = new String[5];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("./MailserverSettings.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                serverdaten[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverdaten;
    }
}
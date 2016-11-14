/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.internal.util.ConfigLoader;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;

/**
 *
 * @author emi
 */
public class Mail {

    public Mail() {
    }

    public void enviarMail() {
//        Email email = new EmailBuilder()
//                .from("lollypop", "lolly.pop@pretzelfun.com")
//                .to("C. Cane", "candycane@candyshop.org")
//                .to("C. Bo", "chocobo@candyshop.org")
//                .subject("hey")
//                .text("We should meet up! ;)")
//                .build();
//
//        Mailer mailer = new Mailer(new ServerConfig("localhost", 2525));
//        mailer.setDebug(false);
//        mailer.sendMail(email);
//        
//        
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");  //mail.smtp.host = localhost
        props.put("mail.smtp.port", "2525");
        Session session = Session.getInstance(props, null);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO,
                    "you@example.com");
            msg.setSubject("JavaMail hello world example");
            msg.setSentDate(new Date());
            msg.setText("Hello, world!\n");
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }

    }

}

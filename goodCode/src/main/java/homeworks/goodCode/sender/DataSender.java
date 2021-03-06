package homeworks.goodCode.sender;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Alexander on 27.10.2016.
 */
public class DataSender implements Sender {
    @Override
    public void send(String recipients, StringBuilder report) {
        // now when the report is built we need to send it to the recipients list
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // we're going to use google mail to send this message
        mailSender.setHost("mail.google.com");
        // construct the message
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            // setting message text, last parameter 'true' says that it is HTML format
            helper.setText(report.toString(), true);
            helper.setSubject("Monthly department salary report");
            // send the message
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}

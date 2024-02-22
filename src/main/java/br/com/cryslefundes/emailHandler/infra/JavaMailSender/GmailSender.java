package br.com.cryslefundes.emailHandler.infra.JavaMailSender;

import br.com.cryslefundes.emailHandler.adapters.EmailSenderGateway;
import br.com.cryslefundes.emailHandler.core.entity.Email;
import br.com.cryslefundes.emailHandler.core.enums.EmailStatus;
import br.com.cryslefundes.emailHandler.core.exception.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class GmailSender implements EmailSenderGateway {
    private final JavaMailSender mailSender;

    @Autowired
    public GmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        try {
            mailSender.send(message);
            email.setEmailStatus(EmailStatus.SENT);
        } catch (MailException e) {
            email.setEmailStatus(EmailStatus.ERROR);
            throw new EmailServiceException(format("Error sending e-mail to: %s - cause: %s", email.getEmailTo(), e.getCause()));
        }
    }
}

package br.com.cryslefundes.emailHandler.infra.JavaMailSender;

import br.com.cryslefundes.emailHandler.adapters.EmailSenderGateway;
import br.com.cryslefundes.emailHandler.core.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class GmailSender implements EmailSenderGateway {
    private final JavaMailSender mailSender;

    @Autowired
    public GmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(EmailDTO dto) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.emailTo());
        message.setSubject(dto.subject());
        message.setText(dto.body());

        mailSender.send(message);
    }
}

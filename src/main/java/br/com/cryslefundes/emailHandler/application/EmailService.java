package br.com.cryslefundes.emailHandler.application;

import br.com.cryslefundes.emailHandler.adapters.EmailSenderGateway;
import br.com.cryslefundes.emailHandler.core.entity.Email;
import br.com.cryslefundes.emailHandler.core.enums.EmailStatus;
import br.com.cryslefundes.emailHandler.core.exception.EmailServiceException;
import br.com.cryslefundes.emailHandler.core.useCase.EmailUseCase;
import br.com.cryslefundes.emailHandler.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
public class EmailService implements EmailUseCase {

    private final EmailSenderGateway emailSenderGateway;
    private final EmailRepository repository;

    @Value("${GMAIL_SENDER_USER}")
    String emailFrom;

    @Autowired
    public EmailService(EmailSenderGateway emailSenderGateway, EmailRepository repository) {
        this.emailSenderGateway = emailSenderGateway;
        this.repository = repository;
    }

    @Override
    public void sendEmail(Email email) {
        email.setSendDate(LocalDateTime.now());
        email.setEmailFrom(emailFrom);
        try {
            this.emailSenderGateway.sendEmail(email);
        } finally {
            this.repository.save(email);
        }
    }
}

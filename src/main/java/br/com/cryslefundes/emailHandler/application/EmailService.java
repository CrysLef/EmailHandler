package br.com.cryslefundes.emailHandler.application;

import br.com.cryslefundes.emailHandler.adapters.EmailSenderGateway;
import br.com.cryslefundes.emailHandler.core.dto.EmailDTO;
import br.com.cryslefundes.emailHandler.core.entity.Email;
import br.com.cryslefundes.emailHandler.core.exception.EmailServiceException;
import br.com.cryslefundes.emailHandler.core.useCase.EmailUseCase;
import br.com.cryslefundes.emailHandler.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class EmailService implements EmailUseCase {

    private final EmailSenderGateway emailSenderGateway;
    private final EmailRepository repository;

    @Autowired
    public EmailService(EmailSenderGateway emailSenderGateway, EmailRepository repository) {
        this.emailSenderGateway = emailSenderGateway;
        this.repository = repository;
    }

    @Override
    public void sendEmail(EmailDTO dto) {
        var email = new Email(dto);
        try {
            emailSenderGateway.sendEmail(dto);
            email.markEmailStatusAsSent();
        } catch (MailException e) {
            email.markEmailStatusAsError();
            throw new EmailServiceException(format("Error sending e-mail to: %s - cause: %s", dto.emailTo(), e.getMessage()));
        } finally {
            repository.save(email);
        }
    }
}

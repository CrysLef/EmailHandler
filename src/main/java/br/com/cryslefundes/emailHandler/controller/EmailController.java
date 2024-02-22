package br.com.cryslefundes.emailHandler.controller;

import br.com.cryslefundes.emailHandler.application.EmailService;
import br.com.cryslefundes.emailHandler.core.entity.Email;
import br.com.cryslefundes.emailHandler.core.exception.EmailServiceException;
import br.com.cryslefundes.emailHandler.core.dto.EmailDTO;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/v1/email-service")
public class EmailController {
    private final EmailService service;

    @Autowired
    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
        ResponseEntity<String> response;
        try {
            Email email = new Email();
            BeanUtils.copyProperties(emailDTO, email);
            this.service.sendEmail(email);
             response = new ResponseEntity<>("e-mail sent successufully!", HttpStatus.OK);
        } catch (EmailServiceException e) {
            response = new ResponseEntity<>(format("Error sending e-mail - cause: %s", e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}

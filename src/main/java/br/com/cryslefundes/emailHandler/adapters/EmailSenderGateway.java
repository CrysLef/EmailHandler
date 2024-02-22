package br.com.cryslefundes.emailHandler.adapters;

import br.com.cryslefundes.emailHandler.core.entity.Email;

public interface EmailSenderGateway {
    void sendEmail(Email email);
}

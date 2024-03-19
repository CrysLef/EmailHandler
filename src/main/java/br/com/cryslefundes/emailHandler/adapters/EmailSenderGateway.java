package br.com.cryslefundes.emailHandler.adapters;

import br.com.cryslefundes.emailHandler.core.dto.EmailDTO;

public interface EmailSenderGateway {
    void sendEmail(EmailDTO dto);
}

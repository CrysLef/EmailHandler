package br.com.cryslefundes.emailHandler.core.useCase;

import br.com.cryslefundes.emailHandler.core.dto.EmailDTO;

public interface EmailUseCase {
    void sendEmail(EmailDTO email);
}

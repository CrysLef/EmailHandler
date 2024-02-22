package br.com.cryslefundes.emailHandler.core.useCase;

import br.com.cryslefundes.emailHandler.core.entity.Email;

public interface EmailUseCase {
    void sendEmail(Email email);
}

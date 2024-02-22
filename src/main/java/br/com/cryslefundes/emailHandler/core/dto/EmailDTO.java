package br.com.cryslefundes.emailHandler.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @NotBlank
        String ownerRef,
        @NotBlank
        @Email
        String emailTo,
        @NotBlank
        String subject,
        @NotBlank
        String body
) { }

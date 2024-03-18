package br.com.cryslefundes.emailHandler.core.entity;

import br.com.cryslefundes.emailHandler.core.dto.EmailDTO;
import br.com.cryslefundes.emailHandler.core.enums.EmailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "emails")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String body;
    @Enumerated(EnumType.STRING)
    private EmailStatus status;
    private LocalDateTime sendDate;

    public Email(EmailDTO dto) {
        this.ownerRef = dto.ownerRef();
        this.emailFrom = System.getenv("GMAIL_SENDER_USER");
        this.emailTo = dto.emailTo();
        this.subject = dto.subject();
        this.body = dto.body();
        this.sendDate = LocalDateTime.now();
    }

    public void markEmailStatusAsSent() {
        this.status = EmailStatus.SENT;
    }

    public void markEmailStatusAsError() {
        this.status = EmailStatus.ERROR;
    }
}

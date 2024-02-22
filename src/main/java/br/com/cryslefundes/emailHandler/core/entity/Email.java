package br.com.cryslefundes.emailHandler.core.entity;

import br.com.cryslefundes.emailHandler.core.enums.EmailStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "TB_EMAIL")
@Data
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String body;
    @Enumerated(EnumType.STRING)
    private EmailStatus emailStatus;
    private LocalDateTime sendDate;
}

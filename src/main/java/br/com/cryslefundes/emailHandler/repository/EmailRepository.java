package br.com.cryslefundes.emailHandler.repository;

import br.com.cryslefundes.emailHandler.core.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}

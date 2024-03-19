package br.com.cryslefundes.emailHandler.infra.rabbitmq;

import br.com.cryslefundes.emailHandler.application.EmailService;
import br.com.cryslefundes.emailHandler.core.dto.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {
    private final EmailService service;

    @Autowired
    public RabbitConsumer(EmailService service) {
        this.service = service;
    }

    @RabbitListener(queues = "${queue.name}")
    public void listenEmailQueue(@Payload EmailDTO dto) {
        service.sendEmail(dto);
    }
}

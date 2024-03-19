package br.com.cryslefundes.emailHandler;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class EmailHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailHandlerApplication.class, args);
	}

}

package br.com.mbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProdutorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutorApplication.class, args);
	}

	// Executar o RabbitMq via container:
	// docker run -d  --name rabbit13 -p 8080:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management
 
}

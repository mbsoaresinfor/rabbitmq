package br.com.mbs;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoListener {

	@RabbitListener(queues = {"${queue.produto}"})
    public void receive(@Payload String fileBody) {
		System.out.println("Order: " + fileBody);
    }
}

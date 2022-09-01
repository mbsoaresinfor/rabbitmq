package br.com.mbs.controler;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controler {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${queue_produto}")
	String queue_produto;

	@Value("${exchange}")
	String exchange;

	@Value("${routingkey_produto}")
	private String routingkey_produto;
	
	@GetMapping(value = "/produtor")
	public String producer(@RequestParam("mensagem") String mensagem) {
	
		rabbitTemplate.convertAndSend(exchange, routingkey_produto, mensagem);

		return "Mensagem enviada com sucesso ao RabbitMQ :)";
	}
}

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
	
	@Value("${exchange}")
	String exchange;

	@Value("${routingkey.marcelo.produto}")
	private String routingkeyMarceloProduto;
	
	@Value("${routingkey.marcelo.produto}")
	private String routingkeyMarceloNotificacao;
	
	@GetMapping(value = "/produtor")
	public String producer(@RequestParam("fila") String fila,@RequestParam("mensagem") String mensagem) {
	
		String routingKey = "";
		if(fila.endsWith("produto")) {
			routingKey = routingkeyMarceloProduto;
		}else if(fila.endsWith("produto")) {
			routingKey = routingkeyMarceloNotificacao;
		}
		
		rabbitTemplate.convertAndSend(exchange, routingKey, mensagem);

		return "Mensagem enviada com sucesso ao RabbitMQ\n"
				+ "exchange=" + exchange + ", Fila=" + fila;
	}
}

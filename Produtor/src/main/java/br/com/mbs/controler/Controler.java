package br.com.mbs.controler;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Value("${routingkey.marcelo.email}")
	private String routingkeyMarceloEmail;
	
	@Value("${routingkey.marcelo}")
	private String routingkeyMarcelo;
	
	
	
	@GetMapping(value = "/produtor/exchange/{fila}/{mensagem}")
	public String enviaMensagemParaFila(@PathVariable("fila") String fila, 
			@PathVariable ("mensagem") String mensagem) {
	
		String routingKey = "";
		if(fila.endsWith("produto")) {
			routingKey = routingkeyMarceloProduto;
		}else if(fila.endsWith("email")) {
			routingKey = routingkeyMarceloEmail;
		}
		
		rabbitTemplate.convertAndSend(exchange, routingKey, mensagem);

		return "Mensagem enviada com sucesso ao RabbitMQ\n"
				+ "exchange=" + exchange + ", Fila=" + fila + ", mensagem=" + mensagem;
	}
	
	@GetMapping(value = "/produtor/exchange/{mensagem}")
	public String enviaMensagemParaExchange(@PathVariable("mensagem") String mensagem) {
		
		rabbitTemplate.convertAndSend(exchange, routingkeyMarcelo, mensagem);

		return "Mensagem enviada com sucesso ao RabbitMQ\n"
				+ "exchange=" + exchange +", mensagem= " + mensagem;
	}
}

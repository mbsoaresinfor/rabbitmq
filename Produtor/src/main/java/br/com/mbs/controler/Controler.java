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
	
	@Value("${exchange.compra.direct}")
	String exchangeCompraDirect;
	
	@Value("${exchange.compra.fanout}")
	String exchangeCompraFanout;

	@Value("${routingkey.compra.produto}")
	private String routingkeyCompraProduto;
	
	@Value("${routingkey.email.produto}")
	private String routingkeyEmailProduto;
	
	
	
	
	
	@GetMapping(value = "/produtor/exchange/{fila}/{mensagem}")
	public String enviaMensagemParaFila(@PathVariable("fila") String fila, 
			@PathVariable ("mensagem") String mensagem) {
	
		String routingKey = "";
		if(fila.endsWith("produto")) {
			routingKey = routingkeyCompraProduto;
		}else if(fila.endsWith("email")) {
			routingKey = routingkeyEmailProduto;
		}
		
		rabbitTemplate.convertAndSend(exchangeCompraDirect, routingKey, mensagem);

		return "Mensagem enviada com sucesso ao RabbitMQ\n"
				+ "exchange=" + exchangeCompraDirect + ", Fila=" + fila + ", mensagem=" + mensagem;
	}
	
	@GetMapping(value = "/produtor/exchange/{mensagem}")
	public String enviaMensagemParaExchange(@PathVariable("mensagem") String mensagem) {
		
		rabbitTemplate.convertAndSend(exchangeCompraFanout, "", mensagem);

		return "Mensagem enviada com sucesso ao RabbitMQ\n"
				+ "exchange=" + exchangeCompraFanout +", mensagem= " + mensagem;
	}
}

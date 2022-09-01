package br.com.mbs.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controler {

	
	@GetMapping(value = "/produtor")
	public String producer(@RequestParam("mensagem") String mensagem) {
	
	//	rabbitMQSender.send(emp);

		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}
}

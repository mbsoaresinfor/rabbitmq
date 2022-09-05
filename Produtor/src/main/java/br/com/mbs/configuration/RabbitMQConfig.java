package br.com.mbs.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${queue.produto}")
	String queueProduto;
	
	@Value("${queue.notificacao}")
	String queueNotificacao;

	@Value("${exchange}")
	String exchange;

	@Value("${routingkey.marcelo.produto}")
	private String routingkeyMarceloProduto;
	
	@Value("${routingkey.marcelo.notificacao}")
	private String routingkeyMarceloNotificacao;

	
	@Bean
	Queue queueProduto() {
		return new Queue(queueProduto, true);
	}
	
	@Bean
	Queue queueNotificacao() {
		return new Queue(queueNotificacao, true);
	}

	
	@Bean
	DirectExchange exchangeMarcelo() {
		return new DirectExchange(exchange);
	}

	
	@Bean
	Binding bindingMarceloProduto() {
		return BindingBuilder.bind(queueProduto()).to(exchangeMarcelo()).with(routingkeyMarceloProduto);
	}
	
	@Bean
	Binding bindingMarceloNotificacao() {
		return BindingBuilder.bind(queueNotificacao()).to(exchangeMarcelo()).with(routingkeyMarceloNotificacao);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	@Bean
	public AmqpTemplate rabbitTemplate2(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}

package br.com.mbs.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
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
	
	@Value("${queue.email}")
	String queueEmail;

	@Value("${exchange.compra.direct}")
	String exchangeCompraDirect;
	
	@Value("${exchange.compra.fanout}")
	String exchangeCompraFanout;
	
	@Value("${routingkey.compra.produto}")
	private String routingkeyCompraProduto;
	
	@Value("${routingkey.email.produto}")
	private String routingkeyEmailProduto;

	
	@Bean
	Queue queueProduto() {
		return new Queue(queueProduto, true);
	}
	
	@Bean
	Queue queueEmail() {
		return new Queue(queueEmail, true);
	}

	
	@Bean
	DirectExchange exchangeCompraDirect() {
		return new DirectExchange(exchangeCompraDirect);
	}
	
	@Bean
	FanoutExchange exchangeCompraFanout() {
		return new FanoutExchange(exchangeCompraFanout);
	}

	
	@Bean
	Binding bindingCompraDirectProduto() {
		return BindingBuilder.bind(queueProduto()).
				to(exchangeCompraDirect()).with(routingkeyCompraProduto);
	}
	
	@Bean
	Binding bindingCompraDirectEmail() {
		return BindingBuilder.bind(queueEmail()).
				to(exchangeCompraDirect()).with(routingkeyEmailProduto);
	}


	@Bean
	Binding bindingCompraFanoutProduto() {
		return BindingBuilder.bind(queueProduto()).
				to(exchangeCompraFanout());
	}
	
	@Bean
	Binding bindingCompraFanoutEmail() {
		return BindingBuilder.bind(queueEmail()).
				to(exchangeCompraFanout());
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

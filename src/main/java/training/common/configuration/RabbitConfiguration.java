package training.common.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public ConnectionFactory connectionFactory() {

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
				environment.getProperty("rabbitmq.host"));
		connectionFactory.setUsername(environment.getProperty("rabbitmq.user"));
		connectionFactory.setPassword(environment.getProperty("rabbitmq.password"));
		connectionFactory.setPort(Integer.valueOf(environment.getProperty("rabbitmq.port")));
		connectionFactory.setVirtualHost("/");
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}

	@Bean
	public Queue myQueue() {
		return new Queue("myqueue");
	}

	/*
	 * @Bean public AmqpAdmin amqpAdmin() { return new
	 * RabbitAdmin(connectionFactory()); }
	 *
	 * @Bean public RabbitTemplate rabbitTemplate() { RabbitTemplate
	 * rabbitTemplate = new RabbitTemplate(connectionFactory());
	 * rabbitTemplate.setMessageConverter(messageConverter()); return
	 * rabbitTemplate; }
	 *
	 * @Bean public MessageConverter messageConverter() { return new
	 * Jackson2JsonMessageConverter(); }
	 *
	 * @Bean public Queue myQueue() { return new Queue("myqueue"); }
	 */
}

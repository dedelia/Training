package training.common.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

	@Bean
	public ConnectionFactory connectionFactory() {
		return (ConnectionFactory) new CachingConnectionFactory("localhost");
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory());
	}

	@Bean
	public Queue myQueue() {
		return new Queue("myqueue");
	}

}


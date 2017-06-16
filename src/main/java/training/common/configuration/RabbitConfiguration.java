package training.common.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import training.company.service.ApplicationContextWrapper;

@Configuration
public class RabbitConfiguration {

	public static final String QUEUE = ".queue.test";

	public static String SETTINGS_FANOUT_EXCHANGE = ".settings.fanout.exchange";

	@Autowired
	private Environment environment;

	@Bean
	public Queue erpQueue() {
		return new Queue(getQueuesPrefix() + QUEUE);
	}

	public String getQueuesPrefix() {
		return environment.getProperty("rabbitmq.queues.prefix");
	}

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

	/**
	 * Fanout exchange used to route messages to all of the queues that are
	 * bound to it
	 *
	 * @return
	 */

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(getQueuesPrefix() + SETTINGS_FANOUT_EXCHANGE);
	}

	@Bean(name = "genericQueue")
	public Queue genericQueue() {
		return new Queue(getQueuesPrefix() + "settings.generic");

	}
	@Bean
	public List<Binding> bs() {
		return Arrays.asList(BindingBuilder.bind(genericQueue()).to(fanoutExchange()));
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public static String getQueueName() {
		RabbitConfiguration rabbitErpConfiguration = ApplicationContextWrapper
				.getBeanByClass(RabbitConfiguration.class);
		return rabbitErpConfiguration.getQueuesPrefix() + ".ceva";
	}

	/*
	 * Examle of a simple message listener that prints out any received message
	 * and is implemented her From what I see though it should has it's own
	 * service -> Companyconsumer
	 */

	/*
	 * @Bean public SimpleMessageListenerContainer messageListenerContainer() {
	 * SimpleMessageListenerContainer container = new
	 * SimpleMessageListenerContainer();
	 * container.setConnectionFactory(connectionFactory());
	 * container.setQueueNames("deliatestqueue");
	 * container.setMessageListener(messageListener()); return container; }
	 */

	/*
	 * @Bean public MessageListener messageListener() { return new
	 * MessageListener() { public void onMessage(Message message) {
	 * System.out.println("received: " + message); } }; }
	 */

}

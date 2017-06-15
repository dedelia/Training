package training.app;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import training.common.configuration.RabbitConfiguration;

@SpringBootApplication(scanBasePackages = { "training" })
@PropertySource("classpath:application.properties")
public class Application {

	public static void main(String[] args) throws Exception {

/*		ApplicationContext context =
			    new AnnotationConfigApplicationContext(RabbitConfiguration.class);
			AmqpTemplate template = context.getBean(AmqpTemplate.class);
			template.convertAndSend("myqueue", "foo");
			String foo = (String) template.receiveAndConvert("myqueue");*/

		SpringApplication.run(Application.class, args);
	}

}


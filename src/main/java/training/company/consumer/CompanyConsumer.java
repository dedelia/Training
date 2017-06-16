package training.company.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CompanyConsumer {

/*	@RabbitListener(queues = "deliatestqueue")
	public void processCompany(Message message) {
		System.out.println(message);
	}*/
}

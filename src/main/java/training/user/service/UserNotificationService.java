package training.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import training.common.annotation.TransactionalService;
import training.user.UserEntity;

@TransactionalService
public class UserNotificationService {


	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * Sends a message to SuPo queue in order to refresh their data
	 */
	public void sendSuPoNotification(UserEntity userEntity) {
		Map<String, String> map = new HashMap<>();
		map.put("userUUID", userEntity.getExternalUUIDWithoutDashes());
		rabbitTemplate.convertAndSend("myqueue", "foo");
		String foo = (String) rabbitTemplate.receiveAndConvert("myqueue");
	}
}

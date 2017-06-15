package training.company.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import training.common.annotation.TransactionalService;
import training.company.dto.CompanyDto;

@TransactionalService
public class CompanyNotificationService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	/**
	 * Sends a message to SuPo queue in order to refresh their data
	 */
	public void sendSuPoNotification(List<CompanyDto> companyEntities) {
		for (CompanyDto c : companyEntities) {
			Map<String, String> map = new HashMap<>();
			map.put("companyUUID", c.getUUID().toString());
			rabbitTemplate.convertAndSend(map);
		}

	}
}
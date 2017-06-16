package training.company.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import training.common.annotation.TransactionalService;
import training.common.configuration.RabbitConfiguration;
import training.company.CompanyEntity;
import training.company.dto.CompanyDto;

@TransactionalService
public class CompanyNotificationService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * Sends a message to SuPo queue in order to refresh their data
	 */
	public void sendNotification(List<CompanyDto> companies) {

		Map<String, String> map = new HashMap<>();
		for (CompanyDto c : companies) {
			map.put("companyUUID", c.getUUID().toString());
		}

		rabbitTemplate.convertAndSend(RabbitConfiguration.getQueueName(), map);
	}

	public void sendCompanyNotification(CompanyEntity companyEntity) {
		/*
		 * Best practice here is to wrap you objects before sending them to queue
		 */
		Map<String, String> map = new HashMap<>();
		map.put("entityUUID", companyEntity.getExternalUUIDWithoutDashes());
		rabbitTemplate.convertAndSend("deliatestqueue", map);
	}

}
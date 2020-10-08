package com.notification.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.notification.configs.queue.QueueConstant;
import com.notification.exception.ApplicationException;
import com.notification.model.Email;
import com.notification.model.NotificationRequest;
import com.notification.model.SMS;

@Service
public class ServiceImplementation implements com.notification.service.Service{
	
	private static final Logger log = LoggerFactory.getLogger(ServiceImplementation.class);
	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public String notificationRequestProcess(NotificationRequest notificationRequest) {
		String result="Queue submited successfully";
		log.info("inside notificationRequestProcess  {} ",notificationRequest);
		try {
			for(Email email:notificationRequest.getEmail())
				jmsTemplate.convertAndSend(QueueConstant.EMAIL,email);
			
			for(SMS sms:notificationRequest.getSms())
				jmsTemplate.convertAndSend(QueueConstant.SMS,sms);
		}catch (Exception e) {
			log.error("Error while notificationRequestProcess {} ",e);
			throw new ApplicationException(e);
		}
		
		return result;
	}

	
}

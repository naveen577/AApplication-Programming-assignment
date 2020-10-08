package com.notification.configs.queue;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.notification.model.Email;
import com.notification.model.SMS;

@Configuration
@EnableJms
public class NotificationQueueConfig {
	private static final Logger log = LoggerFactory.getLogger(NotificationQueueConfig.class);
	
	
	@Bean("notificationFactory")
	public JmsListenerContainerFactory<?> notificationFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@JmsListener(destination = QueueConstant.EMAIL, containerFactory = "notificationFactory")
	public void emailQueueReciver(Email email) {
		log.info("recived queue for email data {} ",email);
	}

	@JmsListener(destination = QueueConstant.SMS, containerFactory = "notificationFactory")
	public void smsQueueReciver(SMS sms) {
		log.info("recived queue for sms data {} ",sms);
	}
	

}

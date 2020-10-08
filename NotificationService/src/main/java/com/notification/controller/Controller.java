package com.notification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notification.exception.ApplicationException;
import com.notification.model.NotificationRequest;
import com.notification.service.Service;

@RestController
public class Controller {

	Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private Service service;

	@RequestMapping(value = "/notificationQueue", method = RequestMethod.POST)
	public ResponseEntity<String> requestEndpointReciver(@RequestBody NotificationRequest notificationRequest) {
		logger.info("Inside the requestEndpointReciver {} ", notificationRequest);
		String result = service.notificationRequestProcess(notificationRequest);
		logger.info("Inside the requestEndpointReciver %s", notificationRequest);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ExceptionHandler(value = ApplicationException.class)
	public ResponseEntity<String> handleApplicationException(ApplicationException exception){
		logger.error("Inside the handleApplicationException {} ", exception);
		return new ResponseEntity<>("Internal Error "+exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

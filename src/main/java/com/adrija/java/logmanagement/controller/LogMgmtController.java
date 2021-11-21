package com.adrija.java.logmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adrija.java.logmanagement.beans.LogRequest;
import com.adrija.java.logmanagement.beans.LogResponse;
import com.adrija.java.logmanagement.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class LogMgmtController {

	@Autowired
	private LogService service;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(LogMgmtController.class);
	
	@ResponseBody
	@PostMapping("/log")
	public LogResponse logController(@RequestBody LogRequest req) {
		LOGGER.info("Entering Controller");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json=null;
		try {
			json = ow.writeValueAsString(req);
		} catch (JsonProcessingException e) {
			LOGGER.error(" error while parsing request");
		}
		
		LOGGER.debug("request object::",json);
		LOGGER.info("Exiting Controller");
		return service.checkLogEvents(req);
	}
}

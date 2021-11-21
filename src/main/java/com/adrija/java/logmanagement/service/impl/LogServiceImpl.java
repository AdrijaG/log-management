package com.adrija.java.logmanagement.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.adrija.java.logmanagement.beans.LogEvents;
import com.adrija.java.logmanagement.beans.LogRequest;
import com.adrija.java.logmanagement.beans.LogResponse;
import com.adrija.java.logmanagement.beans.LogTime;
import com.adrija.java.logmanagement.service.LogService;
@Service
public class LogServiceImpl implements LogService {

	private static final Logger LOGGER=LoggerFactory.getLogger(LogServiceImpl.class);
	@Override
	public LogResponse checkLogEvents(LogRequest req) {
		LOGGER.info("Entering Service impl");
		LogResponse flagEvents=new LogResponse();
		List<String> eventResp=new ArrayList<>();
		Map<String,LogTime> eventDuration = new HashMap<>();
		for(LogEvents event:req.getLogEvents()) {
			if(eventDuration.containsKey(event.getId())) {
				LogTime logTime=new LogTime();
				if(event.getState().equalsIgnoreCase("FINISHED")) {
					LogTime logTimeStart=eventDuration.get(event.getId());
					long start=logTimeStart.getStart();
					
					logTime.setEnd(event.getTimestamp());
					logTime.setDuration(event.getTimestamp()-start);
					eventDuration.put(event.getId(), logTime);
				}
			}
			else if(!eventDuration.containsKey(event.getId())) {
				LogTime logTime=new LogTime();
				if(event.getState().equalsIgnoreCase("STARTED")) {
					logTime.setStart(event.getTimestamp());
					eventDuration.put(event.getId(), logTime);
				}
			}
		}
		for(Map.Entry<String,LogTime> eventDur:eventDuration.entrySet()) {
			if(eventDur.getValue().getDuration()>=4) {
				eventResp.add(eventDur.getKey());
			}
		}
		flagEvents.setEvent(eventResp);
		LOGGER.info("Exciting Service impl");
		return flagEvents;
	}

}

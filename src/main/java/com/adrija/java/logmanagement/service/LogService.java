package com.adrija.java.logmanagement.service;

import org.springframework.stereotype.Service;

import com.adrija.java.logmanagement.beans.LogRequest;
import com.adrija.java.logmanagement.beans.LogResponse;
@Service
public interface LogService {

	LogResponse checkLogEvents(LogRequest req);

}

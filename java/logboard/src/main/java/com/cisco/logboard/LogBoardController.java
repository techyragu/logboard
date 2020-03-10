package com.cisco.logboard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.logboard.exceptions.NotFoundException;
import com.cisco.logboard.utils.DateValidate;
import com.cisco.logboard.utils.DateValidator;

@RestController
@CrossOrigin
public class LogBoardController {
	private static final Logger logger = LoggerFactory.getLogger(LogBoardController.class);

	@Autowired
	private LogRepository repo;

	
	@RequestMapping(path = "/log/{id}", method = RequestMethod.GET)
	public ResponseEntity<Log> find(@PathVariable("id") Integer id) {
		//Log log = repo.findById(id).orElseThrow(() -> new NotFoundException(String.format("Log with Id %d not found", id)));
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<Log> logs = repo.findAllByTimestampBetween(LocalDateTime.parse("2019-01-09 15:48:22", format), LocalDateTime.parse("2019-01-09 15:48:25", format));
		logger.info("#####");
		logger.info(logs.get(0).toString());
		logger.info("##xxx##");
		Log log = logs.get(0);
		return new ResponseEntity<Log>(log, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/logs", method = RequestMethod.POST)
	public ResponseEntity<List<Log>> search(@RequestParam Map<String,String> allParams) {
		logger.info("Parameter are" + allParams.entrySet());
		String from = allParams.get("from");
		String to = allParams.get("to");
		//DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		DateValidator validator = new DateValidate(format);
		if (!validator.isValid(from)){
			return new ResponseEntity<List<Log>>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
		}
		//List<Log> logs = repo.findAllByTimestampBetween(LocalDateTime.parse("2019-01-09 15:48:24", format), LocalDateTime.parse("2019-01-09 15:48:25", format));
		List<Log> logs = repo.findAllByTimestampBetween(LocalDateTime.parse(from, format), LocalDateTime.parse(to, format));
		return new ResponseEntity<List<Log>>(logs, HttpStatus.OK);
	}
	
	
}

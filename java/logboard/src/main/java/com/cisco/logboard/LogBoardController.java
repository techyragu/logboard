package com.cisco.logboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.logboard.exceptions.NotFoundException;

@RestController
@CrossOrigin
public class LogBoardController {

	@Autowired
	private LogRepository repo;

	@RequestMapping(path = "/log/{id}", method = RequestMethod.GET)
	public ResponseEntity<Log> find(@PathVariable("id") Integer id) {
		Log log = repo.findById(id).orElseThrow(() -> new NotFoundException(String.format("Log with Id %d not found", id)));	
		return new ResponseEntity<Log>(log, HttpStatus.OK);
	}

}

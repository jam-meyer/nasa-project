package com.nasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasa.bean.RoverSet;
import com.nasa.service.RoverService;

@RestController

public class RoverController {
	
	@Autowired
	RoverService roverService;
	
	@CrossOrigin
	@GetMapping(value = "api/v1/rover", produces = {"application/json"})
	public RoverSet getRovers() {
		return roverService.getRoverNames();
	}

}

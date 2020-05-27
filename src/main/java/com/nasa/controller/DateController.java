package com.nasa.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasa.service.DateService;

@RestController
public class DateController {

@Autowired
DateService dateService;

@CrossOrigin
@GetMapping(value = "api/v1/date", produces = {"application/json"})
public Set<String> getDateSet() {
	return dateService.getAndFormatDates();
}
}

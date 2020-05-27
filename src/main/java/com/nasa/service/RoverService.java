package com.nasa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasa.bean.RoverSet;
import com.nasa.client.NasaRestClient;

@Service
public class RoverService {

	@Autowired 
	NasaRestClient nasaRestClient;
	
	public RoverSet getRoverNames() {
    	return nasaRestClient.getRoverSet();

	}
}

package com.nasa.client;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.nasa.bean.PhotoSet;
import com.nasa.bean.RoverSet;

import java.io.InputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Component
public class NasaRestClient {
    private static final String REST_URI = "https://api.nasa.gov/mars-photos/api/v1";
    private static final String API_KEY = "b8VvvcYIzDKI2k3wCeZJxfQNTHpan0giaRRlrOZL";
    private static final String API_KEY_PARAM = "api_key";
    private static final String EARTH_DATE_PARAM = "earth_date";

    final JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    final Client client = ClientBuilder.newClient(new ClientConfig(jacksonJsonProvider));

    public RoverSet getRoverSet(){
    	return client
    		.target(REST_URI)
    		.path("rovers")
    		.queryParam(API_KEY_PARAM, API_KEY)
    		.request(MediaType.APPLICATION_JSON)
    		.get(RoverSet.class);
    		
    }
    
    public PhotoSet getPhotoSet(String name, String date) {
    	return client
    		.target(REST_URI)
    		.path("rovers")
    		.path(name)
    		.path("photos")
    		.queryParam(EARTH_DATE_PARAM, date)
        	.queryParam(API_KEY_PARAM, API_KEY)
        	.request(MediaType.APPLICATION_JSON)
        	.get(PhotoSet.class);
    }
    public InputStream getPhoto(String url) {
		return client.target(url)
				.request()
				.get(InputStream.class);
	}

}

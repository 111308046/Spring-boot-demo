package com.geocoder.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.geocoder.demo.entities.JokeResponse;

@Service
public class JokeService {
	private Logger logger = LoggerFactory.getLogger(JokeService.class);
	
	private RestTemplate restTemplate;
	private final static String BASE = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
	
	public JokeService(RestTemplateBuilder restTemplateBuilder){
		restTemplate = restTemplateBuilder.build();
	}
	
	public String getJoke(String first, String last) {
		String url = String.format("%s&firstName=%s&lastName=%s", BASE, first, last);
		//JokeResponse j = restTemplate.getForObject(url, JokeResponse.class);
		ResponseEntity<JokeResponse> res = restTemplate.exchange(url, HttpMethod.GET, null, JokeResponse.class);
		JokeResponse j = res.getBody();
		logger.info(j.getValue().getJoke());
		return j.getValue().getJoke();
	}
}

package com.geocoder.demo.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.geocoder.demo.entities.Response;
import com.geocoder.demo.entities.Site;

@Service
public class GeocoderService {
	
	private Logger logger = LoggerFactory.getLogger(GeocoderService.class);
	
	private RestTemplate restTemplate;
	private static final String BASE = "https://maps.googleapis.com/maps/api/geocode/json";
	private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";
	public GeocoderService(RestTemplateBuilder restTemplateBuilder){
		restTemplate = restTemplateBuilder.build();
	}
	
	private String encodeString(String s) {
	    try {
	        return URLEncoder.encode(s,"UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    return s;
	}
	
	/*public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>();
		for(int i=1; i<11; i++){
			l.add(i);
		}
		
		System.out.println(sumStream(l));
		System.out.println(l.toString());
		
		Stream<String> names = Stream.of("aBc", "d", "ef");
		System.out.println(names.map(s -> {
				return s.toUpperCase();
			}).collect(Collectors.toList()));
		for(int i: l){
			System.out.println(i);
		}
		l.forEach(i -> {
			System.out.println(i);
		});
	}
	

	private static int sumStream(List<Integer> list) {
		return list.stream().filter(i -> i > 0).mapToInt(i -> i+1).sum();
	}*/


	public Site getLatLng(String... address) {
	    String encodedAddress = Stream.of(address)
	            .map(this::encodeString)
	            .collect(Collectors.joining(","));
	    String url = String.format("%s?address=%s&key=%s", BASE, encodedAddress, KEY);
	    Response response = restTemplate.getForObject(url, Response.class);
	    logger.info("address : " + response.getFormattedAddress());
	    return new Site(response.getFormattedAddress(),
	            response.getLocation().getLat(),
	            response.getLocation().getLng());
	}
}

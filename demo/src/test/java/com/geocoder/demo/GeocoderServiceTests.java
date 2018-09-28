package com.geocoder.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.geocoder.demo.entities.Site;
import com.geocoder.demo.services.GeocoderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeocoderServiceTests {
	
	@Autowired
	private GeocoderService geoService;
	
	@Test
	public void testGetLatLng(){
		  Site site = geoService.getLatLng("Boston", "MA");
		  assertEquals(42.36, site.getLatitude(), 0.01);
		  assertEquals(-71.06, site.getLongitude(), 0.01);
	}
	
	@Test
	public void testGetLatLng_guj(){
		Site site = geoService.getLatLng("Ellora Park", "Vadodara");
	}
}

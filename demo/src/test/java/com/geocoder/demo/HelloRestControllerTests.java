/*package com.geocoder.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.geocoder.demo.entities.Greeting;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestControllerTests {

	@Autowired TestRestTemplate template;
	
	@Test 
	public void testGreetWithoutName(){
		ResponseEntity<Greeting> response = template.getForEntity("/rest", Greeting.class);
		assertEquals(response.getStatusCode(), response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
		Greeting g = response.getBody();
		assertEquals("Hello, World!", g.getGreeting());
	}
	
	@Test
	public void testGreetWithName(){
		Greeting g = template.getForObject("/rest?name=Mayur", Greeting.class);
		assertEquals(g.getGreeting(), "Hello, Mayur!");
	}
}
*/
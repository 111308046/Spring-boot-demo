package com.geocoder.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.geocoder.demo.services.JokeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeServiceTest {
	
	@Autowired
	private JokeService jokeService;
	
	@Test
	public void testGetJoke(){
		String joke = jokeService.getJoke("Mayur", "Rathod");
		assertTrue(joke.contains("Mayur") && joke.contains("Rathod"));
	}
}

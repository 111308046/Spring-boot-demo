package com.geocoder.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geocoder.demo.entities.Greeting;
import com.geocoder.demo.services.JokeService;

@RestController
public class HelloRestController {

	@Autowired
	JokeService jokeService;
	
	@GetMapping(value="/rest")
	public Greeting greet(@RequestParam(value="name", required=false, defaultValue="World") String name){
		return new Greeting(String.format("Hello, %s! <br /> %s", name, jokeService.getJoke(name, "sir")));
	}
}

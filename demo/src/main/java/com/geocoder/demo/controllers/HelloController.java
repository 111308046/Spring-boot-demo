package com.geocoder.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@RequestMapping(method=RequestMethod.GET, value="/hello")
	public String sayHello(@RequestParam(value = "name", required = false,
            defaultValue = "World") String name, Model model){
		model.addAttribute("user",  name);
		return "hello";
	}
}

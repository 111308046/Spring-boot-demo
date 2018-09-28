/*package com.geocoder.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import com.geocoder.demo.controllers.HelloController;

public class HelloControllerUnitTests {

	@Test
	public void testSayHello(){
		HelloController h = new HelloController();
		Model model = new ExtendedModelMap();
		String res = h.sayHello("Mayur", model);
		assertEquals("Mayur", model.asMap().get("user"));
		assertEquals("hello", res);
	}
}
*/
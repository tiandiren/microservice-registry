package com.example.lm.demo.Cosumer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/hi")
	public String hi() {
		return "hi! from producer";
	}
	
}

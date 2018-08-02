package com.example.lm.demo.Cosumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Config {

	@Bean
	RestTemplate loadBalancedRestTemplate() {
		return new RestTemplate();
	}
}

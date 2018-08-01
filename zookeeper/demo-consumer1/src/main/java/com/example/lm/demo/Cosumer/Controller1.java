package com.example.lm.demo.Cosumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
public class Controller1 {

	@Autowired
	private AppClient appClient;

	@RequestMapping("/self")
	public String self() {
		return this.appClient.hi();
	}

	@FeignClient("zookeeperProducer")
	interface AppClient {
		@RequestMapping(path = "/hi", method = RequestMethod.GET)
		String hi();
	}
}

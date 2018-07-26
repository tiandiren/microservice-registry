package com.example.lm.demo.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class Consumer {

	@Autowired
	private EurekaClient discoveryClient;
	
	@RequestMapping("/url")
	public String getProvider() {
		String consumer =  new RestTemplate().getForEntity(serviceUrl() + "/hello", String.class).getBody();
		return consumer;
	}
	
	public String serviceUrl() {
	    InstanceInfo instance = discoveryClient.getNextServerFromEureka("demo-client1", false);
	    return "http://" + instance.getIPAddr() + ":" + instance.getPort();
	}
}

package com.example.lm.demo.Consumer;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class Consumer {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private EurekaClient discoveryClient;
	
	@RequestMapping("/url")
	public String getProvider() {
		String consumer = restTemplate.exchange("http://demo-client1/hello", HttpMethod.GET, null, new ParameterizedTypeReference<String>() { }, new HashMap<String, String>()).getBody();
		return consumer;
	}
	
	public String serviceUrl() {
	    InstanceInfo instance = discoveryClient.getNextServerFromEureka("demo-client1", false);
	    System.out.println(instance.getHomePageUrl());
	    System.out.println(instance.getHostName());
//	    return "http://" + instance.getIPAddr() + ":" + instance.getPort();
	    return "http://demo-client1";
	}
}

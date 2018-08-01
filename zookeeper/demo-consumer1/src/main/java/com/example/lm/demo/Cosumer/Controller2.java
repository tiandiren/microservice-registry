package com.example.lm.demo.Cosumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableFeignClients
public class Controller2 {

	@Autowired
	private DiscoveryClient discovery;

	@Autowired
	RestTemplate rest;

	public String rt() {
		return this.rest.getForObject("http://zookeeperProducer/hi", String.class);
	}

	@RequestMapping("/rest")
	public String rest() {
		return rt();
	}
	
	// RestTemplate不加@LoadBalanced时使用ipport进行rest请求
	public String serviceUrl() {
	    List<ServiceInstance> list = discovery.getInstances("zookeeperProducer");
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}
}

package com.example.lm.demo.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.lm.demo.EtcdUtils.EtcdUtil;

@RestController
public class Controller{

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private EtcdUtil etcdUtil;
	
	@RequestMapping("/get")
	public String get(){
		String host = etcdUtil.etvdKVMap.get("producerUrl");
		System.out.println("name:"+host);
		String producer = restTemplate.exchange(host+ "/hello", HttpMethod.GET, null, new ParameterizedTypeReference<String>() { }, new HashMap<String, String>()).getBody();
		return producer;
	}
	
	@RequestMapping("/hello")
	public String hello(){
		return "hello:consumer";
	}
}

package com.example.lm.demo.Cosumer;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.lm.demo.ZKUtils.ZKClientUtil;

@RestController
public class Controller {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/get")
	public String get(){
		String host = ZKClientUtil.linkManager.get("/producer");
		String producer = "";
		if(null != host){
			producer = restTemplate.exchange(host+ "/hi", HttpMethod.GET, null, new ParameterizedTypeReference<String>() { }, new HashMap<String, String>()).getBody();
			
		}
		return producer;
	}
}

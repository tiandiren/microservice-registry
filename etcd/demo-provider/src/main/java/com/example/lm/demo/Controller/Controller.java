package com.example.lm.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller{

//	@Autowired
//	private EtcdClient etcdClient;
	
	@RequestMapping("/hello")
	public String hello(){
		return "hello：producer";
	}
	
	
}

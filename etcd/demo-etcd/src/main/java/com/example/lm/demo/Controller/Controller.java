package com.example.lm.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;

@RestController
public class Controller {

//	@Autowired
//	private EtcdClient etcdClient;
	
	@Autowired
	ServletWebServerInitializedEvent server;
    @LocalServerPort
    int port;
	
	@RequestMapping("/hello")
	public String hello(){
		/*try {
			EtcdResponsePromise promise =  etcdClient.get("msg").send();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return "hello:" + port;
	}
	
	
}

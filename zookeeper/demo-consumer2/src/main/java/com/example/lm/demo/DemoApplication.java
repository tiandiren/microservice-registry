package com.example.lm.demo;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.lm.demo.ZKUtils.BaseListner;
import com.example.lm.demo.ZKUtils.ZKClientUtil;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private ZkClient zkClient;
	@Autowired 
	private ZKClientUtil zkClientUtil;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(zkClient.exists("/producer")){
			byte[] date = zkClient.readData("/producer");
			ZKClientUtil.linkManager.put("/producer", new String(date));
		}
		zkClientUtil.addListner("/producer", new BaseListner("/producer"));
	}
}

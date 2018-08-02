package com.example.lm.demo.ZKUtils;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ZKClientFactory {

	@Bean
	public ZkClient getzkClient(){
		ZkClient zkClient = new ZkClient("10.1.20.105:2181", 8000, 8000, new BytesPushThroughSerializer());
		return zkClient;
	}
	
}

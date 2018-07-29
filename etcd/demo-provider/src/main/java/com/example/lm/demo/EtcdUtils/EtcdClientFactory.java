package com.example.lm.demo.EtcdUtils;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import mousio.etcd4j.EtcdClient;

@Component
public class EtcdClientFactory {

	@Bean
	public EtcdClient getEtcdClient(){
		EtcdClient etcd = new EtcdClient(URI.create("http://192.168.0.104:2379"));
		return etcd;
	}
}

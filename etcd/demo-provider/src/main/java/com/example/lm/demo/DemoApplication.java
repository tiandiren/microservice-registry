package com.example.lm.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

import com.example.lm.demo.EtcdUtils.EtcdUtil;

@SpringBootApplication
public class DemoApplication implements ApplicationListener<ServletWebServerInitializedEvent>{

	@Autowired
	private EtcdUtil etcdUtil;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ServletWebServerInitializedEvent event) {
		InetAddress url;
		try {
			url = InetAddress.getLocalHost();
			int port = event.getWebServer().getPort();
			System.setProperty("producerUrl", "http://" + url.getHostAddress()+":"+port);
			etcdUtil.init();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}

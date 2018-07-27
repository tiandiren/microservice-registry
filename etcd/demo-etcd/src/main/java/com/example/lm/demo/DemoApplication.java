package com.example.lm.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner, ApplicationListener<ServletWebServerInitializedEvent>{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    
	@Override
	public void run(String... args) throws Exception {
		System.setProperty("serverName", "127.0.0.1:");
		
	}

	@Override
	public void onApplicationEvent(ServletWebServerInitializedEvent event) {
		// TODO Auto-generated method stub
		event.getWebServer().getPort();
	}
}

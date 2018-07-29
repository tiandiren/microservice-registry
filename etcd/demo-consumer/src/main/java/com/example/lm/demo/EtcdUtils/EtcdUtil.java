package com.example.lm.demo.EtcdUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import mousio.client.promises.ResponsePromise;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdAuthenticationException;
import mousio.etcd4j.responses.EtcdException;
import mousio.etcd4j.responses.EtcdKeysResponse;

@Configuration
public class EtcdUtil{

	@Autowired 
	private EtcdClient etcdClient;
	
	public Map<String,String> etvdKVMap = new HashMap<>();
	
	public void init(){
		EtcdKeysResponse responce;
		try {
			responce = etcdClient.get("producerUrl").send().get();
			String value = responce.getNode().getValue();
			etvdKVMap.put("producerUrl", value);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EtcdException e) {
			e.printStackTrace();
		} catch (EtcdAuthenticationException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		this.startListener(etcdClient);
	}
	
	public void startListenerThread(EtcdClient etcdClient) {
		new Thread(() -> {
			startListener(etcdClient);
		}).start();
	}

	public void startListener(EtcdClient etcdClient) {
		String serverName = "producerUrl";
		ResponsePromise promise = null;
		try {
			promise = etcdClient.get(serverName).waitForChange().consistent().send();
			promise.addListener(promisea -> {
				System.out.println("found url cause change");
				try {
					String value = etcdClient.get(serverName).consistent().send().get().getNode().getValue();
					etvdKVMap.put(serverName, value);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("listen url change cause exception:{}" + e.getMessage());
				}
				startListener(etcdClient);
			});
		} catch (Exception e) {
			startListener(etcdClient);
			System.out.println("listen url change cause exception:" + e.getMessage());
			e.printStackTrace();
		}
	}
}



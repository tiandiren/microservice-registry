package com.example.lm.demo.ZKUtils;

import java.util.HashMap;
import java.util.Map;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZKClientUtil {

	@Autowired
	private ZkClient zkClient;
	
	public static Map<String,String> linkManager = new HashMap<>();
	
	public void createNode(String node,byte[] value){
		zkClient.create(node, value, CreateMode.EPHEMERAL);
	}
	
	public void addListner(String node,BaseListner listener){
		zkClient.subscribeDataChanges(node, listener);
		zkClient.subscribeStateChanges(listener);
	}
}

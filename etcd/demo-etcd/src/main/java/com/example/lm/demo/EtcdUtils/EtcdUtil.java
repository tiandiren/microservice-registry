package com.example.lm.demo.EtcdUtils;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.responses.EtcdKeysResponse;

@Configuration
public class EtcdUtil {

//	@Autowired 
//	private EtcdClient etcdClient;
	
	@Bean
	public EtcdClient getEtcdClient(){
		EtcdClient etcd = new EtcdClient(URI.create("http://10.1.20.1.5:2379"));
		return etcd;
	}
	
    /*public void regist() { // 注册节点，放在程序启动的入口
        try { // 用put方法发布一个节点
        	String serverName = System.getProperty("serverName");
            EtcdResponsePromise<EtcdKeysResponse> p = etcdClient.put("serverName", serverName).ttl(60).send(); 
            p.get(); // 加上这个get()用来保证设置完成，走下一步，get会阻塞，由上面client的retry策略决定阻塞的方式
            new Thread(new GuardEtcd()).start(); // 启动一个守护线程来定时刷新节点
        } catch (Exception e) {
            // TODO: handle exception
        	 e.printStackTrace();
        }
    }
    
	
    private class GuardEtcd implements Runnable {
    	
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(40*1000l);
                    
                    etcdClient.refresh("serverName",60).send();
                } catch (IOException | InterruptedException  e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }*/
}



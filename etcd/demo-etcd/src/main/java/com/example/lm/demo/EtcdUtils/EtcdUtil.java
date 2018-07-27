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
	
    /*public void regist() { // ע��ڵ㣬���ڳ������������
        try { // ��put��������һ���ڵ�
        	String serverName = System.getProperty("serverName");
            EtcdResponsePromise<EtcdKeysResponse> p = etcdClient.put("serverName", serverName).ttl(60).send(); 
            p.get(); // �������get()������֤������ɣ�����һ����get��������������client��retry���Ծ��������ķ�ʽ
            new Thread(new GuardEtcd()).start(); // ����һ���ػ��߳�����ʱˢ�½ڵ�
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



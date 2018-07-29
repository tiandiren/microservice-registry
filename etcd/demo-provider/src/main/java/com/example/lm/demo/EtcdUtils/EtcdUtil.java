package com.example.lm.demo.EtcdUtils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.responses.EtcdKeysResponse;

@Configuration
public class EtcdUtil{

	@Autowired 
	private EtcdClient etcdClient;
	
//	private static Map<String,String> etvdKVMap = new HashMap<>();
	
	public void init(){
		this.regist();
//		this.startListener(etcdClient);
	}
	
	public void regist() { // 注册节点，放在程序启动的入口
		try { // 用put方法发布一个节点
			String serverName = System.getProperty("producerUrl");
			EtcdResponsePromise<EtcdKeysResponse> p = etcdClient.put("producerUrl", serverName).ttl(60).send();
			p.get(); // 加上这个get()用来保证设置完成，走下一步，get会阻塞，由上面client的retry策略决定阻塞的方式
			new Thread(new GuardEtcd()).start(); // 启动一个守护线程来定时刷新节点
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/*public void startListenerThread(EtcdClient etcdClient) {
		new Thread(() -> {
			startListener(etcdClient);
		}).start();
	}

	public void startListener(EtcdClient etcdClient) {
		String serverName = System.getProperty("producerUrl");
		ResponsePromise promise = null;
		try {
			promise = etcdClient.get(serverName).waitForChange().consistent().send();
			promise.addListener(promisea -> {
				System.out.println("found ETCD's config cause change");
				try {
					String value = etcdClient.get(serverName).consistent().send().get().getNode().getValue();
					etvdKVMap.put(serverName, value);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("listen etcd 's config change cause exception:{}" + e.getMessage());
				}
				startListener(etcdClient);
			});
		} catch (Exception e) {
			startListener(etcdClient);
			System.out.println("listen etcd 's config change cause exception:" + e.getMessage());
			e.printStackTrace();
		}
	}*/

	private class GuardEtcd implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(40 * 1000l);

					etcdClient.refresh("producerUrl", 60).send();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}



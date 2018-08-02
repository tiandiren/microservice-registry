package com.example.lm.demo.ZKUtils;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseListner implements IZkDataListener,IZkStateListener {

	@Autowired
	private ZkClient zkClient;
	@Autowired
	private ZKClientUtil zkClientUtil;
	
	private String dataPath;
	
	public BaseListner(String datePath){
		this.dataPath = datePath;
	}
	
	public void handleDataChange(String dataPath, Object data) throws Exception{
		if(ZKClientUtil.linkManager.containsKey(dataPath)){
			byte[] date = zkClient.readData(dataPath);
			ZKClientUtil.linkManager.put(dataPath, new String(date));
		} 
		zkClientUtil.addListner(this.dataPath, this);
	}

    public void handleDataDeleted(String dataPath) throws Exception{
    	
    }
    
    public void handleStateChanged(KeeperState state) throws Exception{
    	if(state == KeeperState.Disconnected){
    		ZKClientUtil.linkManager.remove(this.dataPath);
    	} else if (state == KeeperState.SyncConnected){
    		byte[] date = zkClient.readData(this.dataPath);
			ZKClientUtil.linkManager.put(this.dataPath, new String(date));
    	}
    	zkClientUtil.addListner(this.dataPath, this);
    }

    public void handleNewSession() throws Exception{
    	
    }

    public void handleSessionEstablishmentError(final Throwable error) throws Exception{};

}

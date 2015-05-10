package cn.sjzc.booksale.utill;

import net.spy.memcached.MemcachedClient;

public class PoolObject {
	private MemcachedClient client;
	private Boolean isUse;
	
	
	public PoolObject(MemcachedClient client) {
		this.client = client;
		this.isUse = false;
	}
	public MemcachedClient getClient() {
		return client;
	}
	public Boolean getIsUse() {
		return isUse;
	}
	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}
	
	
	
}
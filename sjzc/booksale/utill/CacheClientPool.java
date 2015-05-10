package cn.sjzc.booksale.utill;

import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.MemcachedClient;

public class CacheClientPool {

	private static List<PoolObject> objects;
	
	
	
	private static void init() {
		objects = new ArrayList<PoolObject>();
		for (int i = 0; i < 20; i++) {
			objects.add(new PoolObject(CacheServer.getCacheClient()));
		}
	
		
	}
	
	
	public static synchronized MemcachedClient getClient() {
		if(objects == null) {
			init();
		}
		MemcachedClient client = null;
		for (PoolObject iterable_element : objects) {
			if(!iterable_element.getIsUse()) {
				client = iterable_element.getClient();
				break;
			}
			
		}
		
		return client;
	}
	
	
	public static synchronized void returnClient(MemcachedClient client) {
		for (PoolObject iterable_element : objects) {
			if(iterable_element.getClient() == client) {
				iterable_element.setIsUse(false);
				return;
			}
			
		}
		return;
	}
	
	
	
}

package cn.sjzc.booksale.utill;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

public class CacheServer {

	public static MemcachedClient getCacheClient() {

		final String host = "";// 控制台上的“内网地址”
		final String port = ""; // 默认端口 11211，不用改
		final String username = "";// 控制台上的“访问账号“
		final String password = "";// 邮件或短信中提供的“密码”

		MemcachedClient cache = null;
		try {
			AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" },
					new PlainCallbackHandler(username, password));

			cache = new MemcachedClient(
					new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY)
							.setAuthDescriptor(ad).build(), AddrUtil
							.getAddresses(host + ":" + port));

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return cache;

	}// eof
	
	
	public void close(MemcachedClient cache) {
		cache.shutdown();
	}

}

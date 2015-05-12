package cn.sjzc.booksale.utill;


import java.util.Map;

public class SdkRequest {
	public String command;
	public String token = null;
	public Map<String, Object> commandInfo;
	public String url;
	public Long lastRequestTime;
}

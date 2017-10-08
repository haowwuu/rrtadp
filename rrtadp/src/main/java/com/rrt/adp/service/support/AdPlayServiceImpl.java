package com.rrt.adp.service.support;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.rrt.adp.model.Advertisement;
import com.rrt.adp.service.AdPlayService;
import com.rrt.adp.util.HttpClient;

public class AdPlayServiceImpl implements AdPlayService {
	
	private String appId = "yb2B7BB0B1A782A53E";
	private String appSecret = "a051de5b830142828d111b5a89af24bd";
	private String authApi = "http://www.yunbiaowulian.com/api/token.html?appid=yb2B7BB0B1A782A53E&appsecret=a051de5b830142828d111b5a89af24bd";
	@Resource
	private HttpClient httpClient;
	
	@Override
	public boolean play(Advertisement ad) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean auth(){
		Map<String, Object> params = new HashMap<>();
		params.put("appid", "yb2B7BB0B1A782A53E");
		params.put("appsecret", "a051de5b830142828d111b5a89af24bd");
		String retn = httpClient.get(authApi, params);
		return true;
	}

}

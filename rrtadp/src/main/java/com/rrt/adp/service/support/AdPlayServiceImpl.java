package com.rrt.adp.service.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rrt.adp.model.Advertisement;
import com.rrt.adp.service.AdPlayService;
import com.rrt.adp.util.HttpClient;
import com.rrt.adp.util.JsonUtil;
import com.rrt.adp.yb.model.Content;

import springfox.documentation.spring.web.json.Json;

@Service
public class AdPlayServiceImpl implements AdPlayService {
	
	
	private String appId = "yb2B7BB0B1A782A53E";
	private String appSecret = "a051de5b830142828d111b5a89af24bd";
	private String authApi = "http://www.yunbiaowulian.com/api/token.html";
	private String token = "eWIyQjdCQjBCMUE3ODJBNTNFOmEwNTFkZTViODMwMTQyODI4ZDExMWI1YTg5YWYyNGJkOjE1MDc0NzYxMTM0NzM=";
	//@Resource
	private HttpClient httpClient;
	//@Resource
	private JsonUtil jsonUtil;
	
	public AdPlayServiceImpl() {
		httpClient = new HttpClient();
		jsonUtil = new JsonUtil();
	}

	@Override
	public boolean play(Advertisement ad) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String auth(){
		Map<String, Object> params = new HashMap<>();
		params.put("appid", this.appId);
		params.put("appsecret", this.appSecret);
		String retn = httpClient.get(authApi, params);
		System.out.println(retn);
		Token token = jsonUtil.beanFromJson(retn, Token.class);
		if(null==token){
			return null;
		}
		return token.getAccesstoken();
	}
	
	//http://www.yunbiaowulian.com/api/device/list.html?accessToken=密钥&searchDeviceType=1&menuId=1&pageNow=1
	public String getDeviceList(){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		//params.put("menuId", 1);
		params.put("pageNow", 1);
		//params.put("searchDeviceType", 2);
		String retn = httpClient.get("http://www.yunbiaowulian.com/api/device/list.html", params);
		System.out.println(retn);
		return retn;
	}
	//http://www.yunbiaowulian.com/api/user/detail.html?accessToken=密钥
	public String getUserDetail(){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		String retn = httpClient.get("http://www.yunbiaowulian.com/api/user/detail.html", params);
		System.out.println(retn);
		return retn;
	}
	
	//http://www.yunbiaowulian.com/api/layout/list.html?accessToken=密钥&direction=竖屏&layoutFrom=0&pageNow=1
	public String getLayOutList(){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("direction", "");
		params.put("layoutFrom", 1);
		params.put("pageNow", 1);
		String retn = httpClient.get("http://www.yunbiaowulian.com/api/layout/list.html", params);
		System.out.println(retn);
		return retn;
	}
	
	//http://www.yunbiaowulian.com/api/layout/detail.html?accessToken=密钥
	public String getLayoutDetail(String layoutId){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("layoutId", layoutId);
		
		String retn = httpClient.get("http://www.yunbiaowulian.com/api/layout/detail.html", params);
		System.out.println(retn);
		return retn;
	}
	//http://www.yunbiaowulian.com/background/layout/publish.html?layoutId=24172&devices=32004%2C&reservation=2017-10-08+-+2017-10-08&startH=15&startM=00&endH=17&endM=00&run_rule=1&weekArray=1&weekArray=2&weekArray=3&weekArray=4&weekArray=5&weekArray=6&weekArray=7
	//http://www.yunbiaowulian.com/api/layout/publish.html?accessToken=密钥
	public void publish(){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("layoutId", "24190");
		params.put("devices", "32004");
		params.put("runSDateStr", "2017-10-08");
		params.put("runEDateStr", "2017-10-08");
		params.put("runStart", "16:00");
		params.put("runEnd", "23:00");
		params.put("run_rule", "1");
		params.put("weekDayStr", "1,2,3,4,5,6,7");
		 
		String retn = httpClient.get("http://www.yunbiaowulian.com/api/layout/publish.html", params);
		System.out.println(retn);
		//return retn;
	}
	String contentStr = "[{\"center\":[{\"content\":[\"http://imgs.yunbiaowulian.com/imgserver/resource/2017/06/23/b90b5062-c377-4888-8ea8-10282979afc3.png\","
			+ "\"http://imgs.yunbiaowulian.com/imgserver/resource/2017/06/23/b90b5062-c377-4888-8ea8-10282979afc3.png\","
			+ "\"http://imgs.yunbiaowulian.com/imgserver/resource/2017/06/23/566d9ef6-a2dc-49bd-b5a0-31dcb1f99e49.png\"],"
			+ "\"id\":\"row1_col1\",\"imageDetail\":{\"playTime\":\"5\",\"isAutoPlay\":\"true\",\"imagePlayType\":\"0\"},"
			+ "\"container\":{\"height\":\"100%\",\"width\":\"100%\",\"left\":\"0%\",\"top\":\"0%\"},\"type\":\"1\"}],"
			+ "\"footer\":{\"enabled\":false},\"eDate\":\"\",\"weekDay\":\"1,2,3,4,5,6,7\",\"runType\":\"1\",\"start\":\"00:00\","
			+ "\"sDate\":\"\",\"end\":\"00:00\",\"header\":{\"enabled\":false},\"move\":[],\"layoutType\":\"0\"}]";
	//http://www.yunbiaowulian.com/api/layout/update.html?accessToken=密钥&direction=竖屏&layoutFrom=0&pageNow=1
	public void createLayout(){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("id", 0);
		params.put("name", "APILayout1");
		List<String> urls = new ArrayList<>();
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/2017/06/12/cc9f6ee7-4b96-406f-a10e-4493d88a04e8_s.jpg");
		Content content = new Content(urls);
		List<Content> contents = new ArrayList<>();
		contents.add(content);
		//params.put("content", jsonUtil.toJson(contents));
		params.put("content", contentStr);
		params.put("layoutInfo", "0:1_1:1080*1920:1");
		
		String retn = httpClient.get("http://www.yunbiaowulian.com/api/layout/update.html", params);
		System.out.println(retn);
	}
	//http://www.yunbiaowulian.com/api/layout/delete.html?accessToken=密钥&layoutId=布局ID
	public void deleteLayout(){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("layoutId", 24190);
	
		String retn = httpClient.get("http://www.yunbiaowulian.com/api/layout/delete.html", params);
		System.out.println(retn);
	}
	
	public static class Token {
		
		private String accesstoken;
		private String expires;
		
		public String getAccesstoken() {
			return accesstoken;
		}
		public void setAccesstoken(String accesstoken) {
			this.accesstoken = accesstoken;
		}
		public String getExpires() {
			return expires;
		}
		public void setExpires(String expires) {
			this.expires = expires;
		}
	}
	
	public static void main(String[] args){
		AdPlayServiceImpl playService = new AdPlayServiceImpl();
		//playService.auth();
		//playService.getDeviceList();
		//playService.getUserDetail();
		playService.getLayOutList();
		//playService.getLayoutDetail("24192");
		
		//playService.publish();
		//playService.createLayout();
		//playService.deleteLayout();
		
//		List<String> urls = new ArrayList<>();
//		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/2017/06/12/cc9f6ee7-4b96-406f-a10e-4493d88a04e8_s.jpg");
//		Content content = new Content(urls);
//		List<Content> contents = new ArrayList<>();
//		contents.add(content);
//		System.out.println(playService.jsonUtil.toJson(contents));
	}

}

package com.rrt.adp.service.support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rrt.adp.model.Advertisement;
import com.rrt.adp.service.AdPlayService;
import com.rrt.adp.util.HttpClient;
import com.rrt.adp.util.JsonUtil;
import com.rrt.adp.yb.model.Content;


@Service
public class AdPlayServiceImpl implements AdPlayService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdPlayServiceImpl.class);
	
	private String appId = "yb2B7BB0B1A782A53E";
	private String appSecret = "a051de5b830142828d111b5a89af24bd";
	private String authApi = "http://www.yunbiaowulian.com/api/token.html";
	
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
			LOGGER.error("auth fail, return [{}]", retn);
			return null;
		}
		this.token = token.getAccesstoken();
		return token.getAccesstoken();
	}
	
	private boolean isExpired(String result){
		return null!=result&&result.indexOf("\"status\":0")>0;
	}
	
	public String getDeviceList(String searchDeviceType, String menuId, String pageNow){
		String retn = deviceList(searchDeviceType, menuId, pageNow);
		if(isExpired(retn)){
			auth();
			return deviceList(searchDeviceType, menuId, pageNow);
		}
		return retn;
	}
	//http://www.yunbiaowulian.com/api/device/list.html?accessToken=密钥&searchDeviceType=1&menuId=1&pageNow=1
	private String deviceList(String searchDeviceType, String menuId, String pageNow){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		if(null!=pageNow){
			params.put("pageNow", 1);
		}
		if(null!=searchDeviceType){
			params.put("searchDeviceType", 2);
		}
		
		return httpClient.get("http://www.yunbiaowulian.com/api/device/list.html", params);
	}
	
	public String getLayoutList(String direction, String layoutFrom, String pageNow){
		String retn = layOutList(direction, layoutFrom, pageNow);
		if(isExpired(retn)){
			auth();
			return layOutList(direction, layoutFrom, pageNow);
		}
		return retn;
	}
	
	//http://www.yunbiaowulian.com/api/layout/list.html?accessToken=密钥&direction=竖屏&layoutFrom=0&pageNow=1
	public String layOutList(String direction, String layoutFrom, String pageNow){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		if(null!=direction){
			params.put("direction", direction);
		}
		if(null!=layoutFrom){
			params.put("layoutFrom", layoutFrom);
		}
		if(null!=pageNow){
			params.put("pageNow", pageNow);
		}
		
		return  httpClient.get("http://www.yunbiaowulian.com/api/layout/list.html", params);
	}
	
	public String getLayoutDetail(String layoutId){
		if(null==layoutId){
			return null;
		}
		String retn = layoutDetail(layoutId);
		if(isExpired(retn)){
			auth();
			return layoutDetail(layoutId);
		}
		return retn;
	}
	
	//http://www.yunbiaowulian.com/api/layout/detail.html?accessToken=密钥
	private String layoutDetail(String layoutId){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("layoutId", layoutId);
		
		return httpClient.get("http://www.yunbiaowulian.com/api/layout/detail.html", params);
	}
	
	public String publish(String layoutId, List<String> deviceList){
		return publish(layoutId, deviceList, null, null, null, null);
	}
	
	public String publish(String layoutId, List<String> deviceList, String runSDateStr, String runEDateStr, String runStart, String runEnd){
		if(null==layoutId||null==deviceList||deviceList.size()<1){
			return null;
		}
		String retn = publishFull(layoutId, deviceList, runSDateStr, runEDateStr, runStart, runEnd);
		if(isExpired(retn)){
			auth();
			return publishFull(layoutId, deviceList, runSDateStr, runEDateStr, runStart, runEnd);
		}
		return publishFull(layoutId, deviceList, runSDateStr, runEDateStr, runStart, runEnd);
	}
	
	//http://www.yunbiaowulian.com/background/layout/publish.html?layoutId=24172&devices=32004%2C&reservation=2017-10-08+-+2017-10-08&startH=15&startM=00&endH=17&endM=00&run_rule=1&weekArray=1&weekArray=2&weekArray=3&weekArray=4&weekArray=5&weekArray=6&weekArray=7
	//http://www.yunbiaowulian.com/api/layout/publish.html?accessToken=密钥
	private String publishFull(String layoutId, List<String> deviceList, String runSDateStr, String runEDateStr, String runStart, String runEnd) {
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("layoutId", layoutId);
		params.put("devices", deviceList.stream().collect(Collectors.joining(",")));
		runEDateStr = null==runEDateStr?"":runEDateStr;
		runSDateStr = null==runSDateStr?"":runSDateStr;
		runStart = null==runStart?"00:00":runStart;
		runEnd = null==runEnd?"00:00":runEnd;
		params.put("runSDateStr", runSDateStr);
		params.put("runEDateStr", runEDateStr);
		params.put("runStart", runStart);
		params.put("runEnd", runEnd);
		params.put("run_rule", "1");
		params.put("weekDayStr", "1,2,3,4,5,6,7");
		 
		return httpClient.get("http://www.yunbiaowulian.com/api/layout/publish.html", params);
	}
	
	//http://www.yunbiaowulian.com/api/layout/update.html?accessToken=密钥&direction=竖屏&layoutFrom=0&pageNow=1
	public void createLayout(){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("id", 0);
		params.put("name", "APILayoutJsonExample4");
		/*List<String> urls = new ArrayList<>();
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/common/img/yq0KXFZz8JCAC9WpAAHK838qRuw734.jpg");
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/common/img/yq0KZVZz70OAJrxhAAGl8Dhd6yw772.jpg");
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/common/img/yq0KXVZz74SARMo9AAHlTXcbngE032.jpg");
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/common/img/yq0KXVZz74OAZpikAAI78AT7igc991.jpg");
		Content content = new Content(urls);
		List<Content> contents = new ArrayList<>();
		contents.add(content);
		System.out.println(jsonUtil.toJson(contents));
		//params.put("content", jsonUtil.toJson(contents));
*/		params.put("content", readFileByLines("E:\\json3.txt"));
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
	
	 public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString.trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }
	
	private String token = "eWIyQjdCQjBCMUE3ODJBNTNFOmEwNTFkZTViODMwMTQyODI4ZDExMWI1YTg5YWYyNGJkOjE1MDc2MTUxNzgyMjE=";
	public static void main(String[] args){
		AdPlayServiceImplTest playService = new AdPlayServiceImplTest();
		//playService.auth();
		//playService.getDeviceList();
		//playService.getUserDetail();
		//playService.getLayOutList();
		//playService.getLayoutDetail("24298");
		
		playService.publish();
		//playService.createLayout();
		//playService.deleteLayout();
		
//		List<String> urls = new ArrayList<>();
//		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/2017/06/12/cc9f6ee7-4b96-406f-a10e-4493d88a04e8_s.jpg");
//		Content content = new Content(urls);
//		List<Content> contents = new ArrayList<>();
//		contents.add(content);
//		System.out.println(playService.jsonUtil.toJson(contents));
//		System.out.println(readFileByLines("E:\\json.txt"));
	}

}

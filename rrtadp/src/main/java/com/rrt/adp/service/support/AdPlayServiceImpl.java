package com.rrt.adp.service.support;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.MediaDevice;
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
	
	private String token;
	//private String[] devices = {"32004","31575","41076"};
	private String[] devices = {"32004"};
	private String layoutId = "24379";
	
	@Resource
	private HttpClient httpClient;
	@Resource
	private JsonUtil jsonUtil;
	
	
	public String auth(){
		Map<String, Object> params = new HashMap<>();
		params.put("appid", this.appId);
		params.put("appsecret", this.appSecret);
		String retn = httpClient.get(authApi, params);
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
	
	private boolean isSuccess(String result){
		return null!=result&&result.indexOf("\"status\":1")>0;
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
	
	public String publish(String layoutId, List<String> deviceList, String runSDateStr, 
			String runEDateStr, String runStart, String runEnd){
		if(null==layoutId||null==deviceList||deviceList.size()<1){
			return null;
		}
		String retn = doPublish(layoutId, deviceList, runSDateStr, runEDateStr, runStart, runEnd);
		if(isExpired(retn)){
			auth();
			return doPublish(layoutId, deviceList, runSDateStr, runEDateStr, runStart, runEnd);
		}
		return retn;
	}
	
	//http://www.yunbiaowulian.com/api/layout/publish.html?accessToken=密钥
	private String doPublish(String layoutId, List<String> deviceList, String runSDateStr,
			String runEDateStr, String runStart, String runEnd) {
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
	
	public String createLayout(String layoutId, String layoutName, List<String> adResources){
		String retn = doCreateLayout(layoutId, layoutName, adResources);
		if(isExpired(retn)){
			auth();
			return doCreateLayout(layoutId, layoutName, adResources);
		}
		return retn;
	}
	//http://www.yunbiaowulian.com/api/layout/update.html?accessToken=密钥&direction=竖屏&layoutFrom=0&pageNow=1
	private String doCreateLayout(String layoutId, String layoutName, List<String> adResources){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		layoutId = null==layoutId?"0":layoutId;
		params.put("id", layoutId);
		params.put("name", layoutName);
		Content content = new Content(adResources);
		params.put("content", jsonUtil.toJson(content));
		params.put("layoutInfo", "0:1_1:1080*1920:1");
		return httpClient.get("http://www.yunbiaowulian.com/api/layout/update.html", params);
	}
	
	public String deleteLayout(String layoutId){
		if(null==layoutId){
			return null;
		}
		String retn = doDeleteLayout(layoutId);
		if(isExpired(retn)){
			auth();
			return doDeleteLayout(layoutId);
		}
		return retn;
	}
	
	//http://www.yunbiaowulian.com/api/layout/delete.html?accessToken=密钥&layoutId=布局ID
	private String doDeleteLayout(String layoutId){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("layoutId", layoutId);
	
		return httpClient.get("http://www.yunbiaowulian.com/api/layout/delete.html", params);
	}
	
	public String bindDevice(String serialNumber, String serialPwd){
		if(null==serialNumber||null==serialPwd){
			return null;
		}
		String retn = doBindDevice(serialNumber, serialPwd);
		if(isExpired(retn)){
			auth();
			return doBindDevice(serialNumber, serialPwd);
		}
		return retn;
	}
	
	//http://www.yunbiaowulian.com/api/device/bind.html?accessToken=密钥&serialNumber=YB0001&serialPwd=接入密码
	private String doBindDevice(String serialNumber, String serialPwd){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("serialNumber", serialNumber);
		params.put("serialPwd", serialPwd);
	
		return httpClient.get("http://www.yunbiaowulian.com/api/device/bind.html", params);
	}
	
	public String getDeviceDetail(String serialNumber){
		if(null==serialNumber){
			return null;
		}
		String retn = deviceDetail(serialNumber);
		if(isExpired(retn)){
			auth();
			return deviceDetail(serialNumber);
		}
		return retn;
	}
	
	//http://www.yunbiaowulian.com/api/device/detail.html?accessToken=密钥&serialNumber=YB0001
	private String deviceDetail(String serialNumber){
		Map<String, Object> params = new HashMap<>();
		params.put("accessToken", this.token);
		params.put("serialNumber", serialNumber);
	
		return httpClient.get("http://www.yunbiaowulian.com/api/device/detail.html", params);
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

	@Override
	public boolean play(List<Advertisement> ads, MediaDevice device) {
		List<MediaDevice> devices = new ArrayList<>();
		if(null!=device){
			devices.add(device);
		}
		return play(ads, devices);
	}

	@Override
	public boolean play(List<Advertisement> ads, List<MediaDevice> devices) {
		if(null==ads||ads.size()<1){
			return true;
		}
		Set<String> playIds = new HashSet<>();
		playIds.addAll(Arrays.asList(this.devices));
		if(null!=devices&&devices.size()>0){
			playIds.addAll(devices.stream().map(MediaDevice::getPlayId)
					.filter((t)->null!=t).collect(Collectors.toList())) ;
		}
		List<String> contentUrls = ads.stream().map(Advertisement::getContentUrl)
				.filter((t)->null!=t).collect(Collectors.toList());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		createLayout(this.layoutId, dateFormat.format(new Date()), contentUrls);
		List<String> ids = new ArrayList<>();
		ids.addAll(playIds);
		return isSuccess(publish(this.layoutId, ids));
	}

	@Override
	public String bindDevice(MediaDevice device) {
		bindDevice(device.getForeignId(), device.getSerialNumber());
		String retn = getDeviceDetail(device.getForeignId());
		return getDeviceId(retn);
	}
	
	private String getDeviceId(String json){
		if(null==json){
			return null;
		}
		int idIdx = json.indexOf("\"id\":");
		if(idIdx<0){
			return null;
		}
		String idStr = json.substring(idIdx);
		return idStr.substring(5, idStr.indexOf(","));
	}

}

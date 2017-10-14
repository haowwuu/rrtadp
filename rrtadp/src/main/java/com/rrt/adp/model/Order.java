package com.rrt.adp.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Order extends DBModel{
	
	public static final String STATE_PAYED = "P";
	public static final String STATE_BID_SUCCESS = "S";
	public static final String STATE_BID_FAIL = "F";
	
	public static final String STATE_END = "E";  
	
	private static final Set<String> ORDER_STATE_SET = new HashSet<>();
	static{
		ORDER_STATE_SET.addAll(STATE_SET);
		ORDER_STATE_SET.add(STATE_PAYED);
		ORDER_STATE_SET.add(STATE_BID_SUCCESS);
		ORDER_STATE_SET.add(STATE_BID_FAIL);
	}
	
	private String adId;
	private String deviceId;
	private float price;
	private String state;
	private String adOwner;
	private String deviceOwner;
	
	private Advertisement advertisement;
	private MediaDevice mediaDevice;
	
	public boolean isStateLegal(){
		return ORDER_STATE_SET.contains(getState());
	}
	
	public Map<String, Object> dictionary() {
		Map<String, Object> dic = new HashMap<>();
		
		Map<String, String> orderState = new HashMap<>();
		orderState.put("new", STATE_NEW);
		orderState.put("checked", STATE_CHECKED);
		orderState.put("deleted", STATE_DELETE);
		orderState.put("payed", STATE_PAYED);
		orderState.put("bid success", STATE_BID_SUCCESS);
		orderState.put("bid fail", STATE_BID_FAIL);
		orderState.put("illegal", STATE_ILLEAGL);
		
		dic.put("order state", orderState);
		
		return dic;
	}
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAdOwner() {
		return adOwner;
	}
	public void setAdOwner(String adOwner) {
		this.adOwner = adOwner;
	}
	public String getDeviceOwner() {
		return deviceOwner;
	}
	public void setDeviceOwner(String deviceOwner) {
		this.deviceOwner = deviceOwner;
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public MediaDevice getMediaDevice() {
		return mediaDevice;
	}

	public void setMediaDevice(MediaDevice mediaDevice) {
		this.mediaDevice = mediaDevice;
	}

	@Override
	public String toString() {
		return "Order [adId=" + adId + ", deviceId=" + deviceId + ", price=" + price + ", state=" + state + ", adOwner="
				+ adOwner + ", deviceOwner=" + deviceOwner + ", id=" + id + ", getCreateTime()=" + getCreateTime()
				+ ", getUpdateTime()=" + getUpdateTime() + "]";
	}
	
}

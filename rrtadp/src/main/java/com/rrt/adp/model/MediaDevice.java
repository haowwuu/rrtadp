package com.rrt.adp.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaDevice extends DBModel {
	
	public static final String STATUS_WORKING = "W";
	public static final String STATUS_REPAIRING = "R";
	
	public static final String TYPE_SCREEN = "S";
	
	public static final String ATTR_DEVICEPICTURE = "devicePictureUrls";
	
	private String deviceType;
	private String deviceStatus;
	private float basePrice;
	
	private String keyWords;
	private String name;
	private String description;
	private String state;
	private Date playTime;
	private int playFrequency;
	
	private float lng;
	private float lat;
	private String districtCode;
	private String address;
	private String owner;
	
	private List<String> devicePictureUrls;
	
	public Map<String, Object> dictionary() {
		Map<String, Object> dic = new HashMap<>();
		Map<String, String> deviceType = new HashMap<>();
		deviceType.put("device type", TYPE_SCREEN);
		dic.put("media device type", deviceType);
		
		Map<String, String> deviceState = new HashMap<>();
		deviceState.put("new", STATE_NEW);
		deviceState.put("checked", STATE_CHECKED);
		deviceState.put("deleted", STATE_DELETE);
		dic.put("media device state", deviceState);
		
		Map<String, String> deviceStatus = new HashMap<>();
		deviceStatus.put("working", STATUS_WORKING);
		deviceStatus.put("repairing", STATUS_REPAIRING);
		dic.put("media device status", deviceStatus);
		return dic;
	}
	
	public boolean isStatusLegal(){
		return STATUS_REPAIRING.equals(getDeviceStatus())||STATUS_WORKING.equals(getDeviceStatus());
	}
	public boolean isTypeLegal(){
		return TYPE_SCREEN.equals(getDeviceType());
	}
	public boolean isStateLegal(){
		return isStateLegal(getState());
	}
	
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public float getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}
	public int getPlayFrequency() {
		return playFrequency;
	}
	public void setPlayFrequency(int playFrequency) {
		this.playFrequency = playFrequency;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public List<String> getDevicePictureUrls() {
		return devicePictureUrls;
	}
	public void setDevicePictureUrls(List<String> devicePictureUrls) {
		this.devicePictureUrls = devicePictureUrls;
	}

	@Override
	public String toString() {
		return "MediaDevice [deviceType=" + deviceType + ", deviceStatus=" + deviceStatus + ", basePrice=" + basePrice
				+ ", keyWords=" + keyWords + ", name=" + name + ", description=" + description + ", state=" + state
				+ ", playTime=" + playTime + ", playFrequency=" + playFrequency + ", lng=" + lng + ", lat=" + lat
				+ ", districtCode=" + districtCode + ", address=" + address + ", owner=" + owner + ", id=" + id
				+ ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()=" + getUpdateTime() + "]";
	}

}

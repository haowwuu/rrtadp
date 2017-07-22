package com.rrt.adp.model;

import java.util.Date;

public class MediaDevice extends DBModel {
	
	public static final String STATE_NEW = "N";
	public static final String STATE_CHECKED = "C";
	public static final String STATE_ILLEGAL = "I";
	
	public static final String STATUS_WORKING = "W";
	public static final String STATUS_REPAIRING = "R";
	
	private String deviceType;
	private String deviceStatus;
	private float basePrice;
	
	private String keyWords;
	private String description;
	private String state;
	private Date playTime;
	private int playFrequency;
	
	private float lng;
	private float lat;
	private String districtCode;
	private String address;
	private String owner;
	
	public MediaDevice() {
		this.id = MEDIA_DEVICE_ID_PREFIX+this.id;
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
	
}

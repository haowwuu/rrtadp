package com.rrt.adp.model;

public class Order extends DBModel{
	
	public static final String STATE_PAYED = "P";
	public static final String STATE_BID_SUCCESS = "S";
	public static final String STATE_BID_FAIL = "F";
	public static final String STATE_ILLEAGL = "I";
	
	private String adId;
	private String deviceId;
	private float price;
	private String state;
	private String owner;
	
	public Order() {
		this.id = ORDER_ID_PREFIX+this.id;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Order [adId=" + adId + ", deviceId=" + deviceId + ", price=" + price + ", state=" + state + ", owner="
				+ owner + ", id=" + id + ", getId()=" + getId() + ", getCreateTime()=" + getCreateTime()
				+ ", getUpdateTime()=" + getUpdateTime() + "]";
	}
	
	
	
}

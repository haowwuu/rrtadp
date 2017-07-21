package com.rrt.adp.model;

public class MediaDevice extends DBModel {
	
	private String deviceId;
	private String deviceType;
	private String owner;
	private float basePrice;
	
	private String keyWords;
	private String description;
	private String status;//(running repairing)
	private String state;//(new checked illeagel)
	
	private float longitude;
	private float latitude;
	private String address;
}

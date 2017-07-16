package com.rrt.adp.model;

import java.sql.Date;

public class Advertisement extends DBModel{
	
	private static final String AD_TYPE_TEXT = "T";
	private static final String AD_TYPE_PICTURE = "P";
	private static final String AD_TYPE_VIDEO = "V";
	
	
	private String adId;
	private String title;
	private String type;
	private String content;
	private String contentUrl;
	
	private Date playTime;
	private int playFrequency;
	

}

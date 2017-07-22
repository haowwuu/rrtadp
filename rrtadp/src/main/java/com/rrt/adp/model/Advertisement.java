package com.rrt.adp.model;

public class Advertisement extends DBModel{
	
	public static final String AD_TYPE_TEXT = "T";
	public static final String AD_TYPE_PICTURE = "P";
	public static final String AD_TYPE_VIDEO = "V";
	public static final String AD_TYPE_MIX = "M";
	
	private String title;
	private String type;
	private String content;
	private String contentUrl;
	private int timeInSecond;
	private String owner;
	
	public Advertisement() {
		this.id = ADVERTISEMENT_ID_PREFIX+this.id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public int getTimeInSecond() {
		return timeInSecond;
	}
	public void setTimeInSecond(int timeInSecond) {
		this.timeInSecond = timeInSecond;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Advertisement [title=" + title + ", type=" + type + ", content=" + content + ", contentUrl="
				+ contentUrl + ", timeInSecond=" + timeInSecond + ", owner=" + owner + ", id=" + id + ", getId()="
				+ getId() + ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()=" + getUpdateTime() + "]";
	}
	
	
}

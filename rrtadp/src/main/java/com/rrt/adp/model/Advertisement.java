package com.rrt.adp.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Advertisement extends DBModel{
	
	public static final String TYPE_TEXT = "T";
	public static final String TYPE_PICTURE = "P";
	public static final String TYPE_VIDEO = "V";
	public static final String TYPE_MIX = "M";
	private static final Set<String> TYPE_SET = new HashSet<>();
	static{
		TYPE_SET.add(TYPE_TEXT);
		TYPE_SET.add(TYPE_PICTURE);
		TYPE_SET.add(TYPE_VIDEO);
		TYPE_SET.add(TYPE_MIX);
	}
	
	public Map<String, Object> dictionary(){
		Map<String, Object> dic = new HashMap<>();
		Map<String, String> adType = new HashMap<>();
		adType.put("text", TYPE_TEXT);
		adType.put("picture", TYPE_PICTURE);
		adType.put("video", TYPE_VIDEO);
		adType.put("mix", TYPE_MIX);
		dic.put("advertisement type", adType);
		
		Map<String, String> adState = new HashMap<>();
		adState.put("new", STATE_NEW);
		adState.put("checked", STATE_CHECKED);
		adState.put("deleted", STATE_DELETE);
		dic.put("advertisement stae", adState);
		
		return dic;
	}
	
	private String title;
	private String type;
	private String state;
	private String content;
	private String contentUrl;
	private int timeInSecond;
	private String owner;
	
	public boolean isTypeLegal(){
		return TYPE_SET.contains(getType());
	}
	
	public boolean isStateLegal(){
		return isStateLegal(getState());
	}
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
		return "Advertisement [title=" + title + ", type=" + type + ", state=" + state + ", content=" + content
				+ ", contentUrl=" + contentUrl + ", timeInSecond=" + timeInSecond + ", owner=" + owner + ", id=" + id
				+ ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()=" + getUpdateTime() + "]";
	}

}

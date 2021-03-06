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
	
	public static final String ATTR_COVER = "coverUrl";
	public static final String ATTR_CONTENT = "contentUrl";
	
	
	private static final Set<String> TYPE_SET = new HashSet<>();
	static{
		TYPE_SET.add(TYPE_TEXT);
		TYPE_SET.add(TYPE_PICTURE);
		TYPE_SET.add(TYPE_VIDEO);
		TYPE_SET.add(TYPE_MIX);
	}
	
	private String title;
	private String type;
	private String state;
	private String content;
	private String contentUrl;
	private int timeInSecond;
	private String adCompanyId;
	private String owner;
	
	private String coverUrl;
	
	Page<Comments> comments;
	
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
	
	public boolean isTypeLegal(){
		return TYPE_SET.contains(getType());
	}
	
	public boolean isStateLegal(){
		return isStateLegal(getState());
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
	public String getAdCompanyId() {
		return adCompanyId;
	}
	public void setAdCompanyId(String adCompanyId) {
		this.adCompanyId = adCompanyId;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public Page<Comments> getComments() {
		return comments;
	}
	public void setComments(Page<Comments> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Advertisement [title=" + title + ", type=" + type + ", state=" + state + ", content=" + content
				+ ", contentUrl=" + contentUrl + ", timeInSecond=" + timeInSecond + ", adCompanyId=" + adCompanyId
				+ ", owner=" + owner + ", coverUrl=" + coverUrl + ", id=" + id + "]";
	}

}

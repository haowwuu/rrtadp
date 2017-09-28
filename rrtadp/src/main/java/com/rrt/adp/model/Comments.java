package com.rrt.adp.model;

public class Comments extends DBModel {
	
	public static final String TYPE_PLATFORM = "P";
	public static final String TYPE_ADVERTISEMENT = "A";
	public static final String TYPE_DEVICE = "D";
	public static final String TYPE_ORDER = "O";
	
	String account;
	String type;
	String content;
	String replayTo;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getReplayTo() {
		return replayTo;
	}
	public void setReplayTo(String replayTo) {
		this.replayTo = replayTo;
	}
	
	
	@Override
	public String toString() {
		return "Comments [account=" + account + ", type=" + type + ", content=" + content + ", replayTo=" + replayTo
				+ "]";
	}
	
}

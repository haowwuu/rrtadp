package com.rrt.adp.model;

public class Code {
	
	public static final String TYPE_AD_TAG = "ad.tag";
	
	private String id;
	private String code;
	private String type;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CodeDictionary [id=" + id + ", code=" + code + ", type=" + type + ", name=" + name + "]";
	}
	
}

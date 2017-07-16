package com.rrt.adp.model;

public class Account extends DBModel{
	
	public static final String TYPE_COMPANY_USER = "C";
	public static final String TYPE_PERSON_USER = "P";
	
	public static final String ROLE_ADMIN = "A";
	public static final String ROLE_NORMAL = "N";
	
	public static final String STATE_NEW = "N";
	public static final String STATE_CHECKED = "C";
	public static final String STATE_LOCK = "L";
	public static final String STATE_DELETE = "D";
	
	private String account;
	private String password;
	
	private String description;
	
	private String type;
	private String role;
	private String state;
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}

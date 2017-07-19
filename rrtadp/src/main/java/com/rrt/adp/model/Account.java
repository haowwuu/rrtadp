package com.rrt.adp.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年7月19日
 * 
 */
public class Account extends DBModel{
	
	public static final String TYPE_COMPANY_USER = "C";
	public static final String TYPE_PERSON_USER = "P";
	private static final Set<String> TYPE_SET = new HashSet<>();
	
	public static final String ROLE_ADMIN = "A";
	public static final String ROLE_NORMAL = "N";
	private static final Set<String> ROLE_SET = new HashSet<>();
	
	public static final String STATE_NEW = "N";
	public static final String STATE_CHECKED = "C";
	public static final String STATE_LOCK = "L";
	public static final String STATE_DELETE = "D";
	private static final Set<String> STATE_SET= new HashSet<>();
	static{
		TYPE_SET.add(TYPE_COMPANY_USER);
		TYPE_SET.add(TYPE_PERSON_USER);
		
		ROLE_SET.add(ROLE_ADMIN);
		ROLE_SET.add(ROLE_NORMAL);
		
		STATE_SET.add(STATE_NEW);
		STATE_SET.add(STATE_CHECKED);
		STATE_SET.add(STATE_LOCK);
		STATE_SET.add(STATE_DELETE);
	}
	
	private String account;
	private String password;
	private String token;
	
	private String description;
	
	private String type;
	private String role;
	private String state;
	
	public static boolean isTypeLegal(String type){
		return TYPE_SET.contains(type);
	}
	public boolean isTypeLegal(){
		return isTypeLegal(getType());
	}
	public static boolean isRoleLegal(String role){
		return ROLE_SET.contains(role);
	}
	public boolean isRoleLegal(){
		return isRoleLegal(getRole());
	}
	public boolean isAdmin(){
		return Account.ROLE_ADMIN.equals(getRole());
	}
	public static boolean isStateLegal(String state){
		return STATE_SET.contains(state);
	}
	public boolean isStateLegal(){
		return isStateLegal(getState());
	}
	
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}

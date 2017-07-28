package com.rrt.adp.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	
	static{
		TYPE_SET.add(TYPE_COMPANY_USER);
		TYPE_SET.add(TYPE_PERSON_USER);
		
		ROLE_SET.add(ROLE_ADMIN);
		ROLE_SET.add(ROLE_NORMAL);
	}
	
	private String account;
	private String password;
	private String token;
	
	private String description;
	
	private String type;
	private String role;
	private String state;
	
	
	
	public Account() {
		super();
	}

	public Account(String account, String password,
			String description, String type, String role, String state) {
		super();
		this.account = account;
		this.password = password;
		this.description = description;
		this.type = type;
		this.role = role;
		this.state = state;
	}
	
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
	public boolean isStateLegal(){
		return isStateLegal(getState());
	}
	public static Map<String, Object> dictionary() {
		Map<String, Object> dic = new HashMap<>();
		Map<String, String> userType = new HashMap<>();
		userType.put("company", TYPE_COMPANY_USER);
		userType.put("person ", TYPE_PERSON_USER);
		dic.put("user_type", userType);
		
		Map<String, String> userRole = new HashMap<>();
		userRole.put("admin", ROLE_ADMIN);
		userRole.put("normal", ROLE_NORMAL);
		dic.put("user_role", userRole);
		
		Map<String, String> userState = new HashMap<>();
		userState.put("new", STATE_NEW);
		userState.put("checked", STATE_CHECKED);
		userState.put("locked", STATE_LOCK);
		userState.put("deleted", STATE_DELETE);
		dic.put("user_state", userState);
		
		return dic;
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

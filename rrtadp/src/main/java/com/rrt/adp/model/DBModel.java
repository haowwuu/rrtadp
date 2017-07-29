package com.rrt.adp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.rrt.adp.util.SequenceGenerator;

public class DBModel {
	
	public static final String ADVERTISEMENT_ID_PREFIX = "AD";
	public static final String MEDIA_DEVICE_ID_PREFIX = "MD";
	public static final String ORDER_ID_PREFIX = "OR";
	
	public static final String STATE_NEW = "N";
	public static final String STATE_CHECKED = "C";
	public static final String STATE_LOCK = "L";
	public static final String STATE_DELETE = "D";
	protected static final Set<String> STATE_SET= new HashSet<>();
	static{
		STATE_SET.add(STATE_NEW);
		STATE_SET.add(STATE_CHECKED);
		STATE_SET.add(STATE_LOCK);
		STATE_SET.add(STATE_DELETE);
	}
	public static boolean isStateLegal(String state){
		return STATE_SET.contains(state);
	}
	
	protected String id = SequenceGenerator.next();
	private Date createTime = new Date();
	private Date updateTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}

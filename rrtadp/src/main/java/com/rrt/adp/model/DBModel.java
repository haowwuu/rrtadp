package com.rrt.adp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DBModel {
	
	public static final String PREFIX_ADVERTISEMENT = "AD";
	public static final String PREFIX_MEDIA_DEVICE = "MD";
	public static final String PREFIX_ORDER = "OR";
	
	public static final String STATE_NEW = "N";
	public static final String STATE_CHECKED = "C";
	public static final String STATE_LOCK = "L";
	public static final String STATE_DELETE = "D";
	public static final String STATE_ILLEAGL = "I";
	protected static final Set<String> STATE_SET= new HashSet<>();
	static{
		STATE_SET.add(STATE_NEW);
		STATE_SET.add(STATE_CHECKED);
		STATE_SET.add(STATE_LOCK);
		STATE_SET.add(STATE_DELETE);
		STATE_SET.add(STATE_ILLEAGL);
	}
	public static boolean isStateLegal(String state){
		return STATE_SET.contains(state);
	}
	
	protected String id;
	private String dataState;
	private Date createTime;
	private Date updateTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDataState() {
		return dataState;
	}
	public void setDataState(String dataState) {
		this.dataState = dataState;
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
	
	@Override
	public String toString() {
		return "DBModel [id=" + id + ", dataState=" + dataState + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
}

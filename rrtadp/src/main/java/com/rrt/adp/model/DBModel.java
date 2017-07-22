package com.rrt.adp.model;

import java.util.Date;

import com.rrt.adp.util.SequenceGenerator;

public class DBModel {
	
	protected static final String ADVERTISEMENT_ID_PREFIX = "AD";
	protected static final String MEDIA_DEVICE_ID_PREFIX = "MD";
	protected static final String ORDER_ID_PREFIX = "OR";
	
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

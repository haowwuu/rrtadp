package com.rrt.adp.model;

import java.util.Date;

import com.rrt.adp.util.SequenceGenerator;

public class DBModel {
	
	private String id = SequenceGenerator.next();
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

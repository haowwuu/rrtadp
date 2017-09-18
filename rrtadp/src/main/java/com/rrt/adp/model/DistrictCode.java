package com.rrt.adp.model;

public class DistrictCode {
	
	private int id;
	private int parentId;
	private int level;
	
	private String areaCode;
	private String zipCode;
	private String cityCode;
	private String name;
	private String shortName;
	private String mergerName;
	private String pinyin;
	private double lng;
	private double lat;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getMergerName() {
		return mergerName;
	}
	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "DistrictCode [id=" + id + ", parentId=" + parentId + ", level=" + level + ", areaCode=" + areaCode
				+ ", zipCode=" + zipCode + ", cityCode=" + cityCode + ", name=" + name + ", shortName=" + shortName
				+ ", mergerName=" + mergerName + ", pinyin=" + pinyin + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
}

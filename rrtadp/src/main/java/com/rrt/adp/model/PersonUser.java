package com.rrt.adp.model;

public class PersonUser extends Account {
	
	private String name;
	private String nickName;
	private String phone;
	private String email;
	private String address;
	
	private String IDCard;
	
	private String IDCardFrontPicUrl;
	private String IDCardBackPicUrl;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getIDCardFrontPicUrl() {
		return IDCardFrontPicUrl;
	}
	public void setIDCardFrontPicUrl(String iDCardFrontPicUrl) {
		IDCardFrontPicUrl = iDCardFrontPicUrl;
	}
	public String getIDCardBackPicUrl() {
		return IDCardBackPicUrl;
	}
	public void setIDCardBackPicUrl(String iDCardBackPicUrl) {
		IDCardBackPicUrl = iDCardBackPicUrl;
	}
	
}

package com.rrt.adp.model;

public class AdCompany extends DBModel {
	
	private String name;
	private String orgCode;
	private String legalPerson;
	private String address;
	private String contactPerson;
	private String contactPhone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@Override
	public String toString() {
		return "AdCompany [name=" + name + ", orgCode=" + orgCode + ", legalPerson=" + legalPerson + ", address="
				+ address + ", contactPerson=" + contactPerson + ", contactPhone=" + contactPhone + ", toString()="
				+ super.toString() + "]";
	}
		
}

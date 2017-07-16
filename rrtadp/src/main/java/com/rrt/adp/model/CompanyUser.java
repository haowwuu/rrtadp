package com.rrt.adp.model;

public class CompanyUser extends Account{
	
	private String companyName;
	private String legalPerson;
	private String contactPerson;
	private String officePhone;
	private String companyAddress;
	
	private String certificate;
	private String certificateFrontPicUrl;
	private String certificateBackPicUrl;
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getCertificateFrontPicUrl() {
		return certificateFrontPicUrl;
	}
	public void setCertificateFrontPicUrl(String certificateFrontPicUrl) {
		this.certificateFrontPicUrl = certificateFrontPicUrl;
	}
	public String getCertificateBackPicUrl() {
		return certificateBackPicUrl;
	}
	public void setCertificateBackPicUrl(String certificateBackPicUrl) {
		this.certificateBackPicUrl = certificateBackPicUrl;
	}
	
}

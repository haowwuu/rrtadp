package com.rrt.adp.model;

public class CompanyUser extends Account{
	
	private String companyName;
	private String shortName;
	private String legalPerson;
	private String contactPerson;
	private String contactPhone;
	private String officePhone;
	private String districtCode;
	private String companyAddress;
	
	private String certificate;
	private String certificateFrontPicUrl;
	private String certificateBackPicUrl;
	
    public CompanyUser() {
		super();
	}
    
	public CompanyUser(Account account) {
		super(account.getAccount(), account.getPassword(), 
				account.getDescription(), account.getType(), 
				account.getRole(), account.getState());
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
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
	
	@Override
	public String toString() {
		return "CompanyUser [companyName=" + companyName + ", legalPerson=" + legalPerson + ", contactPerson="
				+ contactPerson + ", contactPhone=" + contactPhone + ", officePhone=" + officePhone + ", districtCode="
				+ districtCode + ", companyAddress=" + companyAddress + ", certificate=" + certificate
				+ ", certificateFrontPicUrl=" + certificateFrontPicUrl + ", certificateBackPicUrl="
				+ certificateBackPicUrl + ", isTypeLegal()=" + isTypeLegal() + ", isRoleLegal()=" + isRoleLegal()
				+ ", isAdmin()=" + isAdmin() + ", isStateLegal()=" + isStateLegal() + ", getAccount()=" + getAccount()
				+ ", getPassword()=" + getPassword() + ", getDescription()=" + getDescription() + ", getType()="
				+ getType() + ", getRole()=" + getRole() + ", getState()=" + getState() + ", getToken()=" + getToken()
				+ ", getId()=" + getId() + ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()="
				+ getUpdateTime() + "]";
	}
	
}

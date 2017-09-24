package com.rrt.adp.service;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.AdCompany;
import com.rrt.adp.model.Page;

public interface AdCompanyService {
	
	boolean addAdCompany(AdCompany company, Account account);
	
	boolean updateAdCompany(AdCompany company, Account account);
	
	boolean deleteAdCompany(String companyId, Account account);
	
	Page<AdCompany> getAdCompanyPage(AdCompany company, Page<AdCompany> page);

}

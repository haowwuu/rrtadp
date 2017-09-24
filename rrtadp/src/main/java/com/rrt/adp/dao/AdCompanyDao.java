package com.rrt.adp.dao;

import java.util.List;

import com.rrt.adp.model.AdCompany;
import com.rrt.adp.model.Page;

public interface AdCompanyDao {
	
	int insertAdCompany(AdCompany company);
	
	int updateAdCompany(AdCompany company);
	
	int deleteAdCompany(String companyId);
	
	int countAdCompany(AdCompany company);
	
	List<AdCompany> selectAdCompany(AdCompany company, Page<?> page);

}

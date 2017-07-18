package com.rrt.adp.service;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;

public interface UserService {
	
	Account login(String account, String password);
	
	PersonUser registPersonUser(PersonUser user);
	
	CompanyUser registCompanyUser(CompanyUser user);
}

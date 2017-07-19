package com.rrt.adp.service;

import java.util.List;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;

public interface UserService {
	
	Account login(Account account);
	
	PersonUser registPersonUser(PersonUser user);
	
	CompanyUser registCompanyUser(CompanyUser user);
	
	boolean updateAccount(Account account);
	
	boolean updatePersonUser(PersonUser person);
	
	boolean updateCompanyUser(CompanyUser companyUser);
	
	List<PersonUser> getPersonUserList();
	
	List<CompanyUser> getCompanyUserList();
}

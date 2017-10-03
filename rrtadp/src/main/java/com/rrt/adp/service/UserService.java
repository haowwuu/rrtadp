package com.rrt.adp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;

public interface UserService {
	
	Account login(Account account);
	
	PersonUser registPersonUser(PersonUser user, MultipartFile frontIdPicFile, MultipartFile backIdPicFile);
	
	CompanyUser registCompanyUser(CompanyUser user, MultipartFile frontCertPicFile, MultipartFile backCertPicFile);
	
	boolean updateAccount(Account account);
	
	boolean updatePersonUser(PersonUser person);
	
	boolean updateCompanyUser(CompanyUser companyUser);
	
	List<PersonUser> getPersonUserList();
	
	List<CompanyUser> getCompanyUserList();
	
	PersonUser getPersonUser(String account);
}

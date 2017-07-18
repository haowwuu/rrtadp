package com.rrt.adp.service.support;

import javax.annotation.Resource;

import com.rrt.adp.dao.CompanyUserDao;
import com.rrt.adp.dao.PersonUserDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;
import com.rrt.adp.service.UserService;
import com.rrt.adp.util.RequestMessageContext;

public class UserServiceImpl implements UserService {
	
	@Resource
	private PersonUserDao personUseDao;
	@Resource
	private CompanyUserDao companyUserDao;

	@Override
	public Account login(Account account) {
		if(null==account||null==account.getAccount()){
			RequestMessageContext.setMsg("account null");
			return null;
		}
		Account user = null;
		if(Account.TYPE_PERSON_USER.equals(account.getType())){
			user = personUseDao.selectUserByAccount(account.getAccount());
		}else if(Account.TYPE_COMPANY_USER.equals(account.getType())){
			user = companyUserDao.selectUserByAccount(account.getAccount());
		}else{
			RequestMessageContext.setMsg("accout type incorrect");
			return null;
		}
		if(null==user){
			RequestMessageContext.setMsg("accoount not exist");
			return null;
		}
		if(user.getPassword().equals(account.getPassword())){
			return user;
		}else{
			RequestMessageContext.setMsg("password incorrect");
		}
		return null;
	}

	@Override
	public PersonUser registPersonUser(PersonUser user) {
		if(checkAccount(user)){
			personUseDao.insertUser(user);
			return user;
		}
		return null;
	}

	@Override
	public CompanyUser registCompanyUser(CompanyUser user) {
		if(checkAccount(user)){
			companyUserDao.insertUser(user);
			return user;
		}
		return null;
	}
	
	private boolean checkAccount(Account account){
		if(null==account||null==account.getAccount()){
			RequestMessageContext.setMsg("account null");
			return false;
		}
		if(null==account.getPassword()){
			RequestMessageContext.setMsg("password null");
			return false;
		}
		if(!Account.TYPE_COMPANY_USER.equals(account.getType())
				&&!Account.TYPE_PERSON_USER.equals(account.getType())){
			RequestMessageContext.setMsg("account type illegal");
		}
		account.setRole(Account.ROLE_NORMAL);
		account.setState(Account.STATE_NEW);
		return true;
	}

}

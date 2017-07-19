package com.rrt.adp.service.support;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rrt.adp.dao.CompanyUserDao;
import com.rrt.adp.dao.PersonUserDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;
import com.rrt.adp.service.UserService;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.RequestMessageContext;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private PersonUserDao personUserDao;
	@Resource
	private CompanyUserDao companyUserDao;
	@Resource
	private MessageUtil msgUtil;

	@Override
	public Account login(Account account) {
		if(null==account||null==account.getAccount()){
			RequestMessageContext.setMsg(msgUtil.get("account.null"));
			return null;
		}
		Account user = null;
		if(Account.TYPE_PERSON_USER.equals(account.getType())){
			user = personUserDao.selectUserByAccount(account.getAccount());
		}else if(Account.TYPE_COMPANY_USER.equals(account.getType())){
			user = companyUserDao.selectUserByAccount(account.getAccount());
		}else{
			RequestMessageContext.setMsg(msgUtil.get("accout.type.illegal"));
			return null;
		}
		if(null==user||!Account.STATE_CHECKED.equals(user.getState())){
			RequestMessageContext.setMsg(msgUtil.get("accoount.not.exist"));
			return null;
		}
		if(user.getPassword().equals(account.getPassword())){
			return user;
		}else{
			RequestMessageContext.setMsg(msgUtil.get("password.incorrect"));
		}
		return null;
	}

	@Override
	public PersonUser registPersonUser(PersonUser user) {
		if(checkAccount(user)){
			user.setType(Account.TYPE_PERSON_USER);
			try{
				personUserDao.insertUser(user);
			}catch (DataIntegrityViolationException e) {
				RequestMessageContext.setMsg(msgUtil.get("account.exist"));
				return null;
			}		
			return user;
		}
		return null;
	}

	@Override
	public CompanyUser registCompanyUser(CompanyUser user) {
		if(checkAccount(user)){
			user.setType(Account.TYPE_COMPANY_USER);
			try {
				companyUserDao.insertUser(user);
			} catch (DataIntegrityViolationException e) {
				RequestMessageContext.setMsg(msgUtil.get("account.exist"));
				return null;
			}
			
			return user;
		}
		return null;
	}
	
	private boolean checkAccount(Account account){
		if(null==account||null==account.getAccount()){
			RequestMessageContext.setMsg(msgUtil.get("account.null"));
			return false;
		}
		if(null==account.getPassword()){
			RequestMessageContext.setMsg(msgUtil.get("password.null"));
			return false;
		}
	
		account.setRole(Account.ROLE_NORMAL);
		account.setState(Account.STATE_NEW);
		return true;
	}

	@Override
	public boolean updateAccount(Account account) {
		if(null==account||null==account.getAccount()){
			RequestMessageContext.setMsg(msgUtil.get("account.null"));
			return false;
		}
		account.setRole(null);
		account.setPassword(null);
		if(Account.TYPE_PERSON_USER.equals(account.getType())){
			personUserDao.updateUser((PersonUser)account);
		}else if(Account.TYPE_COMPANY_USER.equals(account.getType())){
			companyUserDao.updateUser((CompanyUser)account);
		}else{
			RequestMessageContext.setMsg(msgUtil.get("accout.type.illegal"));
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePersonUser(PersonUser person) {
		if(null==person||null==person.getAccount()){
			RequestMessageContext.setMsg(msgUtil.get("account.null"));
			return false;
		}
		personUserDao.updateUser(person);
		return true;
	}

	@Override
	public boolean updateCompanyUser(CompanyUser companyUser) {
		if(null==companyUser||null==companyUser.getAccount()){
			RequestMessageContext.setMsg(msgUtil.get("account.null"));
			return false;
		}
		companyUserDao.updateUser(companyUser);
		return true;
	}

	@Override
	public List<PersonUser> getPersonUserList() {
		 return personUserDao.selectUser();
	}

	@Override
	public List<CompanyUser> getCompanyUserList() {
		return companyUserDao.selectUser();
	}

}

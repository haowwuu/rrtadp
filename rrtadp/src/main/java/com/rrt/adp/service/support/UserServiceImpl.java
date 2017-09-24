package com.rrt.adp.service.support;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.dao.CompanyUserDao;
import com.rrt.adp.dao.PersonUserDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;
import com.rrt.adp.service.UserService;
import com.rrt.adp.util.EncryptUtil;
import com.rrt.adp.util.FileUtil;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.MessageContext;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private PersonUserDao personUserDao;
	@Resource
	private CompanyUserDao companyUserDao;
	@Resource
	private MessageUtil msgUtil;
	@Resource
	private FileUtil fileUtil;

	@Override
	public Account login(Account account) {
		if(null==account||null==account.getAccount()){
			MessageContext.setMsg(msgUtil.get("account.null"));
			return null;
		}
		Account user = personUserDao.selectUserByAccount(account.getAccount());
		if(null==user){
			user = companyUserDao.selectUserByAccount(account.getAccount());
		}
		if(null==user||!Account.STATE_CHECKED.equals(user.getState())){
			MessageContext.setMsg(msgUtil.get("accoount.not.exist"));
			return null;
		}
		String pwd = EncryptUtil.md5(user.getPassword()+account.getToken());
		if(pwd.equalsIgnoreCase(account.getPassword())){
			user.setPassword(null);
			return user;
		}else{
			MessageContext.setMsg(msgUtil.get("password.incorrect"));
		}
		return null;
	}

	@Override
	public PersonUser registPersonUser(PersonUser user, 
			MultipartFile idFrontPicFile, MultipartFile idBackPicFile) {
		if(checkAccount(user)){
			user.setType(Account.TYPE_PERSON_USER);
			if(null!=idFrontPicFile){
				String frontUrl = fileUtil.uploadFile(user.getAccount()+"/idcardfrontpic", idFrontPicFile);
				if(null==frontUrl){
					return null;
				}
				user.setIDCardFrontPicUrl(frontUrl);
			}
			if(null!=idBackPicFile){
				String backUrl = fileUtil.uploadFile(user.getAccount()+"/idcardbackpic", idBackPicFile);
				if(null==backUrl){
					return null;
				}
				user.setIDCardBackPicUrl(backUrl);
			}
			if(null!=companyUserDao.selectUserByAccount(user.getAccount())){
				MessageContext.setMsg(msgUtil.get("account.exist"));
				return null;
			}
			try{
				personUserDao.insertUser(user);
			}catch (DataIntegrityViolationException e) {
				MessageContext.setMsg(msgUtil.get("account.exist"));
				LOGGER.error("insert person user exception. [{}]", e.getMessage());
				return null;
			}		
			return user;
		}
		return null;
	}

	@Override
	public CompanyUser registCompanyUser(CompanyUser user, 
			MultipartFile certFrontPicFile, MultipartFile certBackPicFile) {
		if(checkAccount(user)){
			user.setType(Account.TYPE_COMPANY_USER);
			if(null!=certFrontPicFile){
				String frontUrl = fileUtil.uploadFile(user.getAccount()+"/certfrontpic", certFrontPicFile);
				if(null==frontUrl){
					return null;
				}
				user.setCertificateFrontPicUrl(frontUrl);
			}
			if(null!=certBackPicFile){
				String backUrl = fileUtil.uploadFile(user.getAccount()+"/certbackpic", certBackPicFile);
				if(null==backUrl){
					return null;
				}
				user.setCertificateBackPicUrl(backUrl);
			}
			if(null!=personUserDao.selectUserByAccount(user.getAccount())){
				MessageContext.setMsg(msgUtil.get("account.exist"));
				return null;
			}
			try {
				companyUserDao.insertUser(user);
			} catch (DataIntegrityViolationException e) {
				MessageContext.setMsg(msgUtil.get("account.exist"));
				LOGGER.error("insert company person user exception. [{}]", e.getMessage());
				return null;
			}
			
			return user;
		}
		return null;
	}
	
	private boolean checkAccount(Account account){
		if(null==account||null==account.getAccount()){
			MessageContext.setMsg(msgUtil.get("account.null"));
			return false;
		}
		if(null==account.getPassword()){
			MessageContext.setMsg(msgUtil.get("password.null"));
			return false;
		}
	
		account.setRole(Account.ROLE_NORMAL);
		account.setState(Account.STATE_NEW);
		return true;
	}

	@Override
	public boolean updateAccount(Account account) {
		if(null==account||null==account.getAccount()){
			MessageContext.setMsg(msgUtil.get("account.null"));
			return false;
		}
		account.setRole(null);
		account.setPassword(null);
		if(Account.TYPE_PERSON_USER.equals(account.getType())){
			personUserDao.updateUser(new PersonUser(account));
		}else if(Account.TYPE_COMPANY_USER.equals(account.getType())){
			companyUserDao.updateUser(new CompanyUser(account));
		}else{
			MessageContext.setMsg(msgUtil.get("accout.type.illegal"));
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePersonUser(PersonUser person) {
		if(null==person||null==person.getAccount()){
			MessageContext.setMsg(msgUtil.get("account.null"));
			return false;
		}
		personUserDao.updateUser(person);
		return true;
	}

	@Override
	public boolean updateCompanyUser(CompanyUser companyUser) {
		if(null==companyUser||null==companyUser.getAccount()){
			MessageContext.setMsg(msgUtil.get("account.null"));
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

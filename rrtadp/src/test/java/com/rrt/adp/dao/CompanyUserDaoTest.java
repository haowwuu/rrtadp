package com.rrt.adp.dao;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.util.EncryptUtil;
import com.rrt.adp.util.SequenceGenerator;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class CompanyUserDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private CompanyUserDao userDao;
	private Random random;

	@Test
	public void testInsertUser() {
		CompanyUser user = new CompanyUser();
		user.setId(SequenceGenerator.next());
		//user.setAccount("account"+random.nextInt(1000));
		user.setAccount("testjdbc");
		user.setPassword(EncryptUtil.md5("rrtgg123456"));
		user.setDescription("testdescription");
		user.setType(Account.TYPE_COMPANY_USER);
		user.setRole(Account.ROLE_NORMAL);
		user.setState(Account.STATE_CHECKED);
		user.setCompanyName("testcompany");
		user.setLegalPerson("legalperson");
		user.setContactPerson("contact");
		user.setContactPhone("13312345678");
		user.setOfficePhone("0571-123456");
		user.setDistrictCode("3302");
		user.setCompanyAddress("companyaddress");
		user.setCertificate("1231345");
		user.setCertificateFrontPicUrl("www.id.front");
		user.setCertificateBackPicUrl("www.idcard.back");
		int i = userDao.insertUser(user);
	}

	@Test
	public void testDeleteUser() {
		userDao.deleteUser("account417");
	}

	@Test
	public void testUpdateUser() {
		CompanyUser user = new CompanyUser();
		user.setAccount("testjsbc");
		user.setCompanyAddress("updateAddress");
		user.setDistrictCode("3303");
		user.setContactPhone("13111111111");
		user.setPassword(EncryptUtil.md5("rrtgg123456"));
		userDao.updateUser(user);
	}

	@Test
	public void testSelectUser() {
		List<CompanyUser> users = userDao.selectUser();
		users.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectUserByAccount() {
		CompanyUser user = userDao.selectUserByAccount("account257");
		System.out.println(user);
	}

}

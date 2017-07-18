package com.rrt.adp.dao;

import static org.junit.Assert.*;

import java.sql.Date;
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

import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.util.SequenceGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class CompanyUserDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private CompanyUserDao userDao;
	private Random random;
	private SqlProvider sqlProvider;
	
	@Before
	public void setUp() throws Exception {
		random = new Random(System.currentTimeMillis());
		sqlProvider = new SqlProvider();
	}

	@Ignore
	public void testInsertUser() {
		CompanyUser user = new CompanyUser();
		user.setId(SequenceGenerator.next());
		user.setAccount("account"+random.nextInt(1000));
		user.setPassword("123456");
		user.setDescription("testdescription");
		user.setType(Account.TYPE_PERSON_USER);
		user.setRole(Account.ROLE_NORMAL);
		user.setState(Account.STATE_NEW);
		user.setCompanyName("testcompany");
		user.setLegalPerson("legalperson");
		user.setContactPerson("contact");
		user.setOfficePhone("0571-123456");
		user.setCompanyAddress("companyaddress");
		user.setCertificate("1231345");
		user.setCertificateFrontPicUrl("www.id.front");
		user.setCertificateBackPicUrl("www.idcard.back");
		int i = userDao.insertUser(user);
	}

	@Ignore
	public void testDeleteUser() {
		userDao.deleteUser("account417");
	}

	@Ignore
	public void testUpdateUser() {
		CompanyUser user = new CompanyUser();
		user.setAccount("account257");
		user.setCompanyAddress("updateAddress");
		userDao.updateUser(user);
	}

	@Ignore
	public void testSelectUser() {
		List<CompanyUser> users = userDao.selectUser();
		users.stream().forEach(System.out::println);
	}

	@Ignore
	public void testSelectUserByAccount() {
		CompanyUser user = userDao.selectUserByAccount("account257");
		System.out.println(user);
	}

}

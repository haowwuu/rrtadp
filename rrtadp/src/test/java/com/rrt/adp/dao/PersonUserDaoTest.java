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
import com.rrt.adp.model.PersonUser;
import com.rrt.adp.util.SequenceGenerator;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class PersonUserDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private PersonUserDao userDao;
	
	private Random random;
	private SqlProvider sqlProvider;

	@Before
	public void setUp() throws Exception {
		random = new Random(System.currentTimeMillis());
		sqlProvider = new SqlProvider();
	}

	@Ignore
	public void testInsertUser() {
		PersonUser user = new PersonUser();
		user.setId(SequenceGenerator.next());

		user.setAccount("account"+random.nextInt(1000));
		user.setPassword("123456");
		user.setDescription("testdescription");
		user.setType(Account.TYPE_PERSON_USER);
		user.setRole(Account.ROLE_NORMAL);
		user.setState(Account.STATE_NEW);
		user.setName("test");
		user.setNickName("testNick");
		user.setPhone("131123456789");
		user.setDistrictCode("3301");
		user.setAddress("testAddress");
		user.setEmail("email@email.test");
		user.setIDCard("3301112001112111234");
		user.setIDCardFrontPicUrl("www.id.front");
		user.setIDCardBackPicUrl("www.idcard.back");
		int i = userDao.insertUser(user);
	}

	@Ignore
	public void testDeleteUser() {
		userDao.deleteUser("456");
	}

	@Ignore
	public void testUpdateUser() {
		PersonUser user = new PersonUser();
		user.setAccount("account877");
		user.setName("newnew");
		user.setDistrictCode("3302");
		//System.out.println(sqlProvider.updateUser(user));
		userDao.updateUser(user);
	}

	@Test
	public void testSelectUser() {
		List<PersonUser> users = userDao.selectUser();
		users.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectUserByAccount() {
		PersonUser user = userDao.selectUserByAccount("account877");
		System.out.println(user);		
	}

}

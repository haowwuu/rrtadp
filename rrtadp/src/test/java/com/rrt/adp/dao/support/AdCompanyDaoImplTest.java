package com.rrt.adp.dao.support;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rrt.adp.dao.AdCompanyDao;
import com.rrt.adp.model.AdCompany;
import com.rrt.adp.model.Page;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class AdCompanyDaoImplTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private AdCompanyDao adcompanyDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testInsertAdCompany() {
		AdCompany company = new AdCompany();
		company.setName("人人广告公司");
		company.setOrgCode("123456");
		company.setLegalPerson("张法人");
		company.setAddress("北京");
		company.setContactPerson("联系人");
		company.setContactPhone("54321");
		adcompanyDao.insertAdCompany(company);
	}

	@Test
	public void testUpdateAdCompany() {
		AdCompany company = new AdCompany();
		company.setId("1506219215217");
		company.setName("人人广告公司");
		company.setOrgCode("123456");
		company.setLegalPerson("张法人");
		company.setAddress("北京");
		company.setContactPerson("联系人");
		company.setContactPhone("54321");
		adcompanyDao.updateAdCompany(company);
	}

	@Test
	public void testDeleteAdCompany() {
		adcompanyDao.deleteAdCompany("1506219215272");
	}

	@Test
	public void testSelectAdCompany() {
		AdCompany company = new AdCompany();
		adcompanyDao.selectAdCompany(company, new Page<>(1, 10)).forEach(System.out::println);
	}

}

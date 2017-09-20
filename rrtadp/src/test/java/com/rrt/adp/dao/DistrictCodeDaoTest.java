package com.rrt.adp.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class DistrictCodeDaoTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private DistrictCodeDao districtCodeDao;
	
	
	@Test
	public void testSelectTopLevel() {
		districtCodeDao.selectTopLevel().stream().forEach(System.out::println);
	}

	@Test
	public void testSelectChild() {
		districtCodeDao.selectChild(181794).stream().forEach(System.out::println);
	}
	
	@Test
	public void testSelectByCityCode() {
		System.out.println(districtCodeDao.selectByCityCode("330102"));
	}

}

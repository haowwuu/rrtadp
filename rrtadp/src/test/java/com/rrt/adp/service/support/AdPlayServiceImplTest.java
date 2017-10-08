package com.rrt.adp.service.support;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rrt.adp.service.AdPlayService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class AdPlayServiceImplTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private AdPlayService adPlayService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Ignore
	public void testPlay() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuth() {
		String token = adPlayService.auth();
		System.out.println(token);
	}

}

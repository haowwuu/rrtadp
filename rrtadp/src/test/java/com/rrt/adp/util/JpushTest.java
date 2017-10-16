package com.rrt.adp.util;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class JpushTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private PushUtil pushUtil;

	@Test
	public void testGetTagAlias() throws Exception{
		
		pushUtil.push(null, "testAlert1014Apiall", "testTitle1014");

	}

}

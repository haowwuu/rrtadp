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

import com.rrt.adp.dao.CodeDao;
import com.rrt.adp.model.Code;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class CodeDaoImplTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private CodeDao codeDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testInsertCode() {
		Code code = new Code();
		code.setCode("A");
		code.setType(Code.TYPE_AD_TAG);
		code.setName("外卖");
		codeDao.insertCode(code);
	}

	@Test
	public void testUpdateCode() {
		Code code = new Code();
		code.setId("1506222620518");
		//code.setCode("U");
		code.setType(Code.TYPE_AD_TAG);
		code.setName("上班族");
		codeDao.updateCode(code);
	}

	@Test
	public void testDeleteCode() {
		codeDao.deleteCode("123");
	}

	@Test
	public void testSelectCode() {
		Code code = new Code();
		
		codeDao.selectCode(code).forEach(System.out::println);
	}

}

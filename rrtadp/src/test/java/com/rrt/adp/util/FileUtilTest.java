package com.rrt.adp.util;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rrt.adp.model.ObjectFile;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class FileUtilTest {
	
	@Resource
	private FileUtil fileUtil;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Ignore
	public void testUploadFileStringByteArray() {
		fileUtil.uploadFile("id/attr/idx/e.txt", "abce".getBytes());
	}

	@Ignore
	public void testUploadFileStringMultipartFile() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testObjectFileBuilder() throws Exception {
		ObjectFile file = new ObjectFile();
		file.setObjectId(SequenceGenerator.next());
		file.setObjectAttr("testattr");
		file.setFileIndex(0);
		file.setFileName("filename");
		file.setFileUrl("fileurl");
		
		System.out.println(file.buildFullFileName());
		
	}

}

package com.rrt.adp.dao.support;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rrt.adp.dao.ObjectFileDao;
import com.rrt.adp.model.ObjectFile;
import com.rrt.adp.util.SequenceGenerator;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ObjectFileDaoImplTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private ObjectFileDao objFileDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testInsertObjectFile() {
		ObjectFile file = new ObjectFile();
		file.setObjectId(SequenceGenerator.next());
		file.setObjectAttr("testattr");
		file.setFileIndex(0);
		file.setFileName("filename");
		file.setFileUrl("fileurl");
		objFileDao.insertObjectFile(file);
	}

	@Test
	public void testUpdateObjectFile() {
		ObjectFile file = new ObjectFile();
		file.setObjectId(SequenceGenerator.next());
		file.setObjectAttr("testattr");
		file.setFileIndex(0);
		file.setFileName("filename");
		file.setFileUrl("fileurl");
		objFileDao.updateObjectFile(file);
	}

	@Test
	public void testReplaceObjectFile() {
		ObjectFile file = new ObjectFile();
		file.setObjectId("1507081476745");
		file.setObjectAttr("testattr");
		file.setFileIndex(0);
		file.setFileName("filename");
		file.setFileUrl("fileurlreplace4");
		objFileDao.replaceObjectFile(file);
	}

	@Test
	public void testDeleteObjectFile() {
		objFileDao.deleteObjectFile("objid", "testattr", 0);
	}

	@Test
	public void testSelectObjFileUrlList() {
		List<String> urls = objFileDao.selectObjFileUrlList("1507081476744", "testattr");
		System.out.println(urls);
	}

	@Test
	public void testSelectObjFileUrl() {
		String url = objFileDao.selectObjFileUrl("1507081476744", "testattr", 0);
		System.out.println(url);
	}

	@Test
	public void testSelectObjFile() {
		objFileDao.selectObjFile("objid", "testattr", 0);
	}

}

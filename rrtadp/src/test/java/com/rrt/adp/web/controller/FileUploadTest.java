package com.rrt.adp.web.controller;

import static org.junit.Assert.*;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rrt.adp.util.FileUtil;
import com.rrt.adp.web.TestUtil;




public class FileUploadTest {
	
	private static String baseUrl = TestUtil.baseUrl;
	private static String token;
	@Resource
	private FileUtil fileUtil;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		token = TestUtil.getToken();
	}


	
	@Ignore
	public void testUpload() throws Exception {
		String url = baseUrl + "user/uploadFile";
		//String filePath = "C:\\Users\\MikanMu\\Desktop\\test.txt";
		String filePath = "F:\\2.jpg";

		RestTemplate rest = new RestTemplate();
		FileSystemResource resource = new FileSystemResource(new File(filePath));
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("jarFile", resource);
		param.add("fileName", "2.jpg");

		String string = rest.postForObject(url, param, String.class);
		System.out.println(string);
	}
	
	@Ignore
	public void testFileUpload() {
		String retn = fileUtil.uploadFile("abc", "test".getBytes());
		System.out.println(retn);
	}
	
	@Ignore
	public void testUploadFileIndex() throws Exception {
		String url = baseUrl + "/file/upload";
		//String filePath = "C:\\Users\\MikanMu\\Desktop\\test.txt";
		String filePath = "/Users/wuhao/git/rrt/rrtadp/target/rrtgg/images/logo.png";

		RestTemplate rest = new RestTemplate();
		FileSystemResource resource = new FileSystemResource(new File(filePath));
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("file", resource);
		param.add("id", "zhagnsan");
		param.add("attr", "avatar");
		param.add("token", token);

		String string = rest.postForObject(url, param, String.class);
		System.out.println(string);
	}
	
	@Test
	public void testUploadObjfile(){
		String url = baseUrl + "/file/upload/v1";
		//String filePath = "C:\\Users\\MikanMu\\Desktop\\test.txt";
		String filePath = "/Users/wuhao/git/rrt/rrtadp/target/rrtgg/images/logo.png";

		RestTemplate rest = new RestTemplate();
		FileSystemResource resource = new FileSystemResource(new File(filePath));
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("file", resource);
		param.add("objectId", "MD1500729361092");
		param.add("objectAttr", "avatar");
		param.add("token", token);

		String string = rest.postForObject(url, param, String.class);
		System.out.println(string);
		
	}

}

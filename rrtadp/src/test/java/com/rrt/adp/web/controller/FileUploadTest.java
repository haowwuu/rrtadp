package com.rrt.adp.web.controller;

import static org.junit.Assert.*;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rrt.adp.util.FileUtil;




public class FileUploadTest {
	
	private String basicUrl = UserControllerTest.baseUrl;
	@Resource
	private FileUtil fileUtil;
	//http://blog.csdn.net/mhmyqn/article/details/26395743
	@Before
	public void setUp() throws Exception {
	}

	
	@Ignore
	public void testUpload() throws Exception {
		String url = basicUrl + "user/uploadFile";
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
	
	@Test
	public void testUploadFileIndex() throws Exception {
		String url = basicUrl + "/file/upload";
		//String filePath = "C:\\Users\\MikanMu\\Desktop\\test.txt";
		String filePath = "F:\\2.jpg";

		RestTemplate rest = new RestTemplate();
		FileSystemResource resource = new FileSystemResource(new File(filePath));
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("file", resource);
		param.add("id", "zhagnsan");
		//param.add("index", "2");
		param.add("token", "04A8D70A989A0B707FE337C7F6C50C45");

		String string = rest.postForObject(url, param, String.class);
		System.out.println(string);
	}

}

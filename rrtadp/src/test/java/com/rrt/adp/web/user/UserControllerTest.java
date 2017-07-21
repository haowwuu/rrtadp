package com.rrt.adp.web.user;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class UserControllerTest {
	
	//http://blog.csdn.net/mhmyqn/article/details/26395743
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRegistPersonUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistCompanyUser() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpload() throws Exception {
		String url = "http://127.0.0.1:8080/test/upload.do";
		String filePath = "C:\\Users\\MikanMu\\Desktop\\test.txt";

		RestTemplate rest = new RestTemplate();
		FileSystemResource resource = new FileSystemResource(new File(filePath));
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("jarFile", resource);
		param.add("fileName", "test.txt");

		String string = rest.postForObject(url, param, String.class);
		System.out.println(string);
	}

}

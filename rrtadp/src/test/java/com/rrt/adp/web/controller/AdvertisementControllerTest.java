package com.rrt.adp.web.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rrt.adp.web.TestUtil;


public class AdvertisementControllerTest {

	private String baseUrl = TestUtil.baseUrl;
	private String token;
	
	@Before
	public void setUp() throws Exception {
		token = TestUtil.getToken();
	}

	@Ignore
	public void testCreateAd() {
		RestTemplate restTemplate = new RestTemplate();  
		String filePath = "/Users/wuhao/git/rrt/rrtadp/target/rrtgg/images/logo.png";
		FileSystemResource frontPic = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();  
        form.add("title", "adttiel1");
        form.add("type", "V");
        form.add("content", "adcontent");
        form.add("adFile", frontPic);
        form.add("token", token);
        form.add("coverFile", frontPic);
        String result = restTemplate.postForObject(baseUrl+"/ad/new", form, String.class);  
        System.out.println(result);
	}

	@Ignore
	public void testGetUserAdList() {
		RestTemplate restTemplate = new RestTemplate();  
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();  
        form.add("token", "9FFCE962640718F2956BAB499048C6E3");
      
        String result = restTemplate.postForObject(baseUrl+"/ad/list", form, String.class);  
        System.out.println(result);
	}
	
	@Test
	public void testPageUserAd() {
		RestTemplate restTemplate = new RestTemplate();  
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();  
        form.add("token", token);
        form.add("pageNum", "1");
        form.add("pageSize", "20");
        //form.add("owner", "rrtgg");
        form.add("id", "AD1500895029525");
      
        String result = restTemplate.postForObject(baseUrl+"/ad/page", form, String.class);  
        System.out.println(result);
	}

	@Ignore
	public void testUpdateAd() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testDeleteAd() {
		fail("Not yet implemented");
	}

}

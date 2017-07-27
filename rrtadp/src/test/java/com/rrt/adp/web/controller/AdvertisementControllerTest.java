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


public class AdvertisementControllerTest {

	private String baseUrl = UserControllerTest.baseUrl;
	//private String baseUrl = "http://localhost:8080/rrtadp/";
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateAd() {
		RestTemplate restTemplate = new RestTemplate();  
		FileSystemResource frontPic = new FileSystemResource(new File("F:\\2.jpg"));
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();  
        form.add("title", "adttiel1");
        form.add("type", "T");
        form.add("content", "adcontent");
        form.add("adFile", frontPic);
        form.add("token", "9D4F16C0209408986E9BE2BC05F3CD1E");
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

	@Ignore
	public void testUpdateAd() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testDeleteAd() {
		fail("Not yet implemented");
	}

}

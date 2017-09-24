package com.rrt.adp.web.controller;

import static org.junit.Assert.*;

import org.apache.ibatis.ognl.Token;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.Module.SetupContext;
import com.rrt.adp.web.TestUtil;

public class MediaDeviceControllerTest {
	
	private String baseUrl = TestUtil.baseUrl;
	private String token;
	
	@Before
	public void setUp() throws Exception {
		token = TestUtil.getToken();
	}
	
	@Ignore
	public void testCreateDevice() {
		RestTemplate restTemplate = new RestTemplate();  
		//FileSystemResource frontPic = new FileSystemResource(new File("F:\\2.jpg"));
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();  
        form.add("deviceType", "S");
        form.add("deviceStatus", "W");
        form.add("name", "newDevicennn");
        //form.add("adFile", frontPic);
        form.add("keyWords", "白领");
        form.add("districeCode", "330102");
        form.add("lng", 120);
        form.add("lat", 30);
        form.add("address", "浙江杭州");
        form.add("basePrice", 123);
        form.add("playTime", "2018-09-07 00:00:00");
        form.add("playFrequency", 11);
        
        form.add("token", "8A23CDB0A4DC07108613F01658BF2D38");
        String result = restTemplate.postForObject(baseUrl+"/device/new", form, String.class);  
        System.out.println(result);
	}

	@Ignore
	public void testGetUserDeviceList() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetDeviceList() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testUpdateDevice() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPageDevice() {
		RestTemplate restTemplate = new RestTemplate();  
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();  
        form.add("token", token);
        form.add("pageNum", "1");
        form.add("pageSize", "20");
        //form.add("owner", "rrtgg");
      
        String result = restTemplate.postForObject(baseUrl+"/device/page", form, String.class);  
        System.out.println(result);
	}

}

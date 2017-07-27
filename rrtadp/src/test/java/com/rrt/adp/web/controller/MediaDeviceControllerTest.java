package com.rrt.adp.web.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class MediaDeviceControllerTest {
	
	private String baseUrl = UserControllerTest.baseUrl;
	
	@Test
	public void testCreateDevice() {
		RestTemplate restTemplate = new RestTemplate();  
		//FileSystemResource frontPic = new FileSystemResource(new File("F:\\2.jpg"));
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();  
        form.add("deviceType", "S");
        form.add("deviceStatus", "W");
        form.add("name", "newDevice");
        //form.add("adFile", frontPic);
        form.add("keyWords", "白领");
        form.add("districeCode", "330102");
        form.add("lng", 120);
        form.add("lat", 30);
        form.add("address", "浙江杭州");
        form.add("basePrice", 123);
        form.add("playTime", "2018-09-07 00:00:00");
        form.add("playFrequency", 11);
        
        form.add("token", "09EC27CD220868ACF17F4CB693F71B9A");
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

}

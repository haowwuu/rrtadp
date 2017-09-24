package com.rrt.adp.web.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rrt.adp.web.TestUtil;

public class OrderControllerTest {
	
	private String baseUrl = TestUtil.baseUrl;
	private static String token;
	
	@Before
	public void setUp() throws Exception {
		token = TestUtil.getToken();
	}

	@Test
	public void testCreateOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserOrderList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrderList() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testBidOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testPageAd() {
		RestTemplate restTemplate = new RestTemplate();  
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();  
        form.add("token", token);
        form.add("pageNum", "1");
        form.add("pageSize", "20");
        //form.add("owner", "rrtgg");
      
        String result = restTemplate.postForObject(baseUrl+"/order/page", form, String.class);  
        System.out.println(result);
	}

}

package com.rrt.adp.web.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rrt.adp.model.Comments;
import com.rrt.adp.web.TestUtil;

public class SystemControllerTest {
	private static String baseUrl = TestUtil.baseUrl;
	private static String token;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		token = TestUtil.getToken();
	}

	@Ignore
	public void testAddComments() {
		RestTemplate restTemplate = new RestTemplate();  
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>(); 
        Comments comments = new Comments();
        comments.setContent("comment contents");
        comments.setType("A");
        comments.setReplayTo("toto");
        form.add("content", comments.getContent());
        form.add("type", comments.getType());
        form.add("replayTo", comments.getReplayTo());
        
        form.add("token", token);
      
        String result = restTemplate.postForObject(baseUrl+"/sys/comments/new", form, String.class);  
        //System.out.println(result);
	}

	@Ignore
	public void testDeleteComments() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testPageComments() {
		RestTemplate restTemplate = new RestTemplate();  
        
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", "1");
        map.put("pageSize", "10");
       
        String result = restTemplate.getForObject(
        		baseUrl+"/sys/comments/page?pageNum={pageNum}&pageSize={pageSize}", String.class, map);
        System.out.println(result);
	}
	
	@Ignore
	public void testZanComments() {
		RestTemplate restTemplate = new RestTemplate();  
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>(); 
        
        form.add("adId", "testzanaccout");
        form.add("token", token);
      
        String result = restTemplate.postForObject(baseUrl+"/sys/comments/zan", form, String.class);  
        System.out.println(result);
	}
	
	@Test
	public void testPush() {
		RestTemplate restTemplate = new RestTemplate();  
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>(); 
        
        form.add("adId", "testzanaccout");
        form.add("alert", "testApiPushAlert1114");
        form.add("title", "testApiPushTitle");
        //form.add("token", token);
      
        String result = restTemplate.postForObject(baseUrl+"/sys/push", form, String.class);  
        System.out.println(result);
	}

}

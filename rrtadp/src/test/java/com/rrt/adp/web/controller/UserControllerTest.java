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

import com.rrt.adp.model.Account;
import com.rrt.adp.util.EncryptUtil;

public class UserControllerTest {
	
	//public static String baseUrl = "http://localhost:8080/rrtadp/";
	public static String baseUrl = "http://47.92.100.40/rrtadp/";
	public static String token = null;

	@Before
	public void setUp() throws Exception {
	}

	//token=9FFCE962640718F2956BAB499048C6E3
	@Test
	public void testLogin() {
		Account account = new Account();
		account.setAccount("rrtgg");
		account.setPassword("123456");
		account.setType("P");
		account.setToken("anyRandomStr");
		RestTemplate restTemplate = new RestTemplate();  
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();  
        form.add("account", account.getAccount());
        form.add("password", EncryptUtil.md5(EncryptUtil.md5(account.getAccount()+account.getPassword())+account.getToken()));
        form.add("token", account.getToken());
        form.add("type", account.getType());
       
        String result = restTemplate.postForObject(baseUrl+"/user/login", form, String.class);  
        System.out.println(result);
	}

	@Ignore
	public void testRegistPersonUser() {
		RestTemplate restTemplate = new RestTemplate();  
		FileSystemResource frontPic = new FileSystemResource(new File("F:\\2.jpg"));
		FileSystemResource backtPic = new FileSystemResource(new File("F:\\2.jpg"));
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();  
        form.add("account", "pictest");
        form.add("password", EncryptUtil.md5("pictest123456"));
        form.add("type", "P");
        form.add("idFrontPicFile", frontPic);
        form.add("idBackPicFile", backtPic);
        String result = restTemplate.postForObject(baseUrl+"/user/regist/person", form, String.class);  
        System.out.println(result);
	}

	@Ignore
	public void testAuditUser() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetPersonUserList() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetCompanyUserList() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testTestSession() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testUpdatePersonUser() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testUpdateCompanyUser() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testUpload() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testObject() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testClone() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testToString() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testWait() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testFinalize() {
		fail("Not yet implemented");
	}

}

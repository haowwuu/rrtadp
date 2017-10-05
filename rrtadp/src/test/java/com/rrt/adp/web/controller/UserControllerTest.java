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
import com.rrt.adp.web.TestUtil;


public class UserControllerTest {
	
	
	public static String baseUrl = TestUtil.baseUrl;
	public static String token = null;

	@Before
	public void setUp() throws Exception {
	}

	//token=9FFCE962640718F2956BAB499048C6E3
	@Ignore
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

	@Test
	public void testRegistPersonUser() {
		RestTemplate restTemplate = new RestTemplate();  
		String filePath = "/Users/wuhao/git/rrt/rrtadp/target/rrtgg/images/logo.png";
		FileSystemResource frontPic = new FileSystemResource(new File(filePath));
		FileSystemResource backtPic = new FileSystemResource(new File(filePath));
		FileSystemResource profilePhoto = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();  
        form.add("account", "testuserphoto");
        form.add("password", EncryptUtil.md5("testuserpic123456"));
        form.add("type", "P");
        form.add("IDCard", "301233245567365676");
        form.add("idFrontPicFile", frontPic);
        form.add("idBackPicFile", backtPic);
        form.add("profilePhotoFile", profilePhoto);
        String result = restTemplate.postForObject(baseUrl+"/user/regist/person", form, String.class);  
        System.out.println(result);
	}
	
	@Ignore
	public void testRegistCompanyUser() {
		RestTemplate restTemplate = new RestTemplate();  
		FileSystemResource frontPic = new FileSystemResource(new File("/Users/wuhao/Pictures/logo.png"));
		FileSystemResource backtPic = new FileSystemResource(new File("/Users/wuhao/Pictures/logo.png"));
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();  
        form.add("account", "testcompanypic5");
        form.add("password", EncryptUtil.md5("testcompanypic5123456"));
        form.add("type", "C");
        form.add("certificate", "1233245567365676");
        form.add("certFrontPicFile", frontPic);
        form.add("certBackPicFile", backtPic);
        form.add("certificate", "12332455");
        form.add("certFrontPicFile", frontPic);
        form.add("certBackPicFile", backtPic);
        String result = restTemplate.postForObject(baseUrl+"/user/regist/company", form, String.class);  
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


}

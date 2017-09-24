package com.rrt.adp.web;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rrt.adp.model.Account;
import com.rrt.adp.util.EncryptUtil;

public class TestUtil {
	
	public static String baseUrl = "http://localhost:8080/rrtadp/";
	//public static String baseUrl = "http://47.92.100.40/rrtadp/";
	
	public static boolean isSuccessRestResult(String restResult){
		return null!=restResult&&restResult.indexOf("\"code\":\"0\"")>=0;
	}
	
	public static String getToken(){
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
        int idx = result.indexOf("token");
        String token = null;
        if(idx>0){
        	token = result.substring(idx+8, idx+40);
        }
        System.out.println("token:"+token);
        return token;
	}
	
	public static void main(String[] args){
		getToken();
	}

}

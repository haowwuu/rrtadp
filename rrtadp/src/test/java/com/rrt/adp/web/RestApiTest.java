package com.rrt.adp.web;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rrt.adp.model.Order;
import com.rrt.adp.util.EncryptUtil;

import sun.reflect.generics.tree.VoidDescriptor;



/**
 * 意博智能灯接口测试
 * @author Wuwuhao
 * 
 */
public class RestApiTest {
	
	//private String baseUrl = "http://183.129.184.212:8081/site/light.php/exinterface/";
	private String baseUrl = "http://localhost:8080/rrtadp/";
	//private CloseableHttpClient httpclient = HttpClients.createDefault();
	ObjectMapper jsonMapper = new ObjectMapper();
	
	public String post(String url, Map<String, String> params) {
		HttpHost proxy = new HttpHost("localhost", 8888);
		CloseableHttpClient httpclient = HttpClients.custom().setProxy(proxy).build();
		HttpPost httpPost = new HttpPost(url);		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for(Entry<String, String> entry:params.entrySet()){
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		String retn = null;
		CloseableHttpResponse response = null;
		try {
			StringEntity reqEntity = new StringEntity(jsonMapper.writeValueAsString(params));
			reqEntity.setContentEncoding("UTF-8");
		    reqEntity.setContentType("application/json");
			httpPost.setEntity(reqEntity);
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
		    retn = EntityUtils.toString(entity);
		    //System.out.println(retn);
			int code = response.getStatusLine().getStatusCode();
			assertTrue(code<300);
		    
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		finally {
			try {
				response.close();
			} catch (Exception e2) {
				
			}  
		}
		
		return retn;
	}
	
	public String postForm(String url, Map<String, String> params) {
		HttpHost proxy = new HttpHost("localhost", 8888);
		CloseableHttpClient httpclient = HttpClients.custom().setProxy(proxy).build();
		HttpPost httpPost = new HttpPost(url);		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for(Entry<String, String> entry:params.entrySet()){
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		String retn = null;
		CloseableHttpResponse response = null;
		try {
			StringEntity reqEntity = new UrlEncodedFormEntity(nvps);
			reqEntity.setContentEncoding("UTF-8");
		    reqEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(reqEntity);
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
		    retn = EntityUtils.toString(entity);
		    //System.out.println(retn);
			int code = response.getStatusLine().getStatusCode();
			assertTrue(code<300);
		    
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		finally {
			try {
				response.close();
			} catch (Exception e2) {
				
			}  
		}
		
		return retn;
	}

	
	@Test
	public void testLogin() throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("account", "rrtgg");
		String password = EncryptUtil.md5("rrtgg123456");
		System.out.println(password );
		password = EncryptUtil.md5(password+123);
		params.put("password", password);
		
		params.put("type", "P");
		params.put("token", "123");
		//System.out.println(jsonMapper.writeValueAsString(params));
		
		String retn = postForm(baseUrl+"user/login", params);
		System.out.println(retn);
	}
	
}

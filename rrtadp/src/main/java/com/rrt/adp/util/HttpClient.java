package com.rrt.adp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年9月25日
 * 
 */
@Component
public class HttpClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);
	
	private RequestConfig requestConfig;
	private CloseableHttpClient closeableHttpClient;
	
	public HttpClient() {
		requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
		closeableHttpClient = HttpClients.createDefault();
	}

	public String post(String url, String data) {
		String payLoad = null;
		if(!StringUtils.hasText(url)||!StringUtils.hasText(payLoad=data)){
			return null;
		}
		HttpPost httpPost = null;
		try{
			httpPost = new HttpPost(url);
		}catch (IllegalArgumentException e) {
			LOGGER.error("illegal url [{}], data[{}] exception[{}]", url, data, e.getMessage());
			return null;
		}
		httpPost.setConfig(requestConfig);
		try {
			StringEntity entity = new StringEntity(payLoad, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			CloseableHttpResponse response = this.closeableHttpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			LOGGER.error("post url[{}] data[{}] exception [{}]", url, data, e.getMessage());
		}
		return null;
	}
	
	public String post(String url, Map<String, Object> data){
		if(!StringUtils.hasText(url)){
			return null;
		}
		HttpPost httpPost = null;
		try{
			httpPost = new HttpPost(url);
		}catch (IllegalArgumentException e) {
			LOGGER.error("illegal url [{}], data[{}] exception[{}]", url, data, e.getMessage());
			return null;
		}
		httpPost.setConfig(requestConfig);
		try {
			if(null!=data){
				List <NameValuePair> nvps = new ArrayList <NameValuePair>();
				for(Entry<String, Object> entry:data.entrySet()){
					if(null!=entry.getKey()&&null!=entry.getValue()){
						nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
					}
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			}
			CloseableHttpResponse response = this.closeableHttpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			LOGGER.error("post url[{}] data[{}] {} [{}]", url, data, e.getClass(), e.getMessage());
		}
		return null;
	}
	
	public String get(String url, Map<String, Object> data){
		if(!StringUtils.hasText(url)){
			return null;
		}
		HttpGet httpGet = null;
		try{
			if(null!=data){
				List <NameValuePair> nvps = new ArrayList <NameValuePair>();
				for(Entry<String, Object> entry:data.entrySet()){
					if(null!=entry.getKey()&&null!=entry.getValue()){
						nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
					}
				}
				String params = EntityUtils.toString(new UrlEncodedFormEntity(nvps, "UTF-8"));
				url = url+"?"+params;
			}
			httpGet = new HttpGet(url);
			//System.out.println(url);
		}catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("illegal url [{}], data[{}] {} [{}]", url, data, e.getClass(), e.getMessage());
			return null;
		}
		try {
			CloseableHttpResponse response = this.closeableHttpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}else{
				System.out.println(url);
				System.out.println(response);
			}
		} catch (Exception e) {
			LOGGER.error("get url[{}] data[{}] exception [{}]", url, data, e.getMessage());
		}
		return null;
	}
	
}

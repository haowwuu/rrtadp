package com.rrt.adp.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.annotation.Resource;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
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
public class HttpClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);
	
	@Resource
	private RequestConfig requestConfig;
	@Resource
	private CloseableHttpClient closeableHttpClient;
	@Resource
	private JsonUtil jsonUtil;
	
	private final Executor executor = Executors.newFixedThreadPool(10, new ThreadFactory() {
		
		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setDaemon(true);
			return thread;
		}
	});
	
	public void post(String url, String data){
		executor.execute(()->{
			doPost(url, data);
		});
	}
	
	private String doPost(String url, String data) {
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
	
}

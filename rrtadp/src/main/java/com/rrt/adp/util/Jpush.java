package com.rrt.adp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

public class Jpush {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Jpush.class);
	
	private static final String APP_KEY = "0d199fa5dac4bfda6f9cffdd";
	private static final String APP_SECRET = "e8bbae7115435da651ea0939";
	
	private JPushClient jpushClient = new JPushClient(APP_SECRET, APP_KEY, null, ClientConfig.getInstance());
	
	public void push(PushPayload payload){
		try {
	        PushResult result = jpushClient.sendPush(payload);

	    } catch (APIConnectionException e) {
	        LOGGER.error("Connection error, should retry later [{}]", e.getMessage());

	    } catch (APIRequestException e) {
	        LOGGER.error("APIRequestException HTTP Status[{}] Error Code[{}] Error Message[{}]",
	        		e.getStatus(), e.getErrorCode(), e.getMessage());
	    }
	}


}

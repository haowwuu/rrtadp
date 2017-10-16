package com.rrt.adp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

@Component
public class PushUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PushUtil.class);
	
	public static final String APP_KEY = "0d199fa5dac4bfda6f9cffdd";
	public static final String APP_SECRET = "e8bbae7115435da651ea0939";
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	private JPushClient jpushClient = new JPushClient(APP_SECRET, APP_KEY, null, ClientConfig.getInstance());
	
	public void push(String account, String alert, String title){
		PushPayload payload = buildPayload(account, alert, title);
		try {
	        PushResult result = jpushClient.sendPush(payload);
	        LOGGER.info("push result [{}]", result);
	    } catch (APIConnectionException e) {
	        LOGGER.error("Connection error, should retry later [{}]", e.getMessage());
	    } catch (APIRequestException e) {
	        LOGGER.error("APIRequestException HTTP Status[{}] Error Code[{}] Error Message[{}]",
	        		e.getStatus(), e.getErrorCode(), e.getMessage());
	    }
	}
	
	private PushPayload buildPayload(String account, String alert, String title){	
		Audience audience = Audience.all();
		if(StringUtils.hasText(account)){
			audience = Audience.alias(account);
		}
		return PushPayload.newBuilder().setPlatform(Platform.all())
				.setAudience(audience)
				//.setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder()
								.setAlert(alert)
								.addExtra("title", title)
								.addExtra("date", dateFormat.format(new Date()))
								.build())
						.build())
				//.setMessage(Message.content(message))
				.build();
	}


}

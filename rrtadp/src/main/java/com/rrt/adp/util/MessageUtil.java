package com.rrt.adp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:message.properties")
public class MessageUtil {
	
	@Autowired
	Environment env;
	
	public String get(String key){
		if(null==key){
			return null;
		}
		
		return env.getProperty(key, key);	
	}
	
	public String get(String key, String param){
		if(null==key){
			return null;
		}
		String value = env.getProperty(key);
		if(null==value){
			return param + " " + key;
		}else{
			return String.format(value, param);
		}
	}

}

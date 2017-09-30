package com.rrt.adp.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	
	private final ObjectMapper mapper = new ObjectMapper(); 
	
	public String toJson(Object obj) {
		if(null==obj){
			return null;
		}
		try{
			return mapper.writeValueAsString(obj);  
		}catch (JsonProcessingException e) {
			LOGGER.error("[{}] toJson exception [{}]", obj, e.getMessage());
		}
		return null;
	}
	
	public <T> T beanFromJson(String json, Class<T> clazz) {
		if(!StringUtils.hasText(json)||null==clazz){
			return null;
		}
		try{
			return mapper.readValue(json, clazz); 
		}catch (Exception e) {
			LOGGER.error("[{}] beanFromJson [{}] exception [{}]", clazz, json, e.getMessage());
		}
		return null;
	}
	
	public <T> List<T> listFromJson(String json, Class<T> eleClazz) {
		if(!StringUtils.hasText(json)||null==eleClazz){
			return null;
		}
		JavaType type = mapper.getTypeFactory().constructParametricType(List.class, eleClazz);
		try{
			return mapper.readValue(json, type);
		}catch (Exception e) {
			LOGGER.error("[{}] listFromJson [{}] exception [{}]", eleClazz, json, e.getMessage());
		}
		return null;
	}
	
	public <K,V> Map<K, V> mapFromJson(String json, Class<K> keyClazz, Class<V> valueClazz){
		if(!StringUtils.hasText(json)||null==keyClazz||null==valueClazz){
			return null;
		}
		JavaType type = mapper.getTypeFactory().constructMapType(Map.class, keyClazz, valueClazz);
		try{
			return mapper.readValue(json, type);
		}catch (Exception e) {
			LOGGER.error("mapFromJson [{}] exception [{}]", json, e.getMessage());
		}
		return null;
	}

}

package com.rrt.adp.util;


public class RequestMessageContext {
	
	private static ThreadLocal<String> msgContext = new ThreadLocal<>();
	
	public static void setMsg(String msg) {
		msgContext.set(msg);
	}
	
	public static String getMsg() {
		return msgContext.get();
	}
}

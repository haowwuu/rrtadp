/*
 * File Name: SequenceGenerator.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-10-21

 * Modifier: Wuwuhao
 * Modify Date: 2016-10-21
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.rrt.adp.util;

/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2016年10月16日
 * 
 */
public class SequenceGenerator {
	
	private static long last;
	public static synchronized String next(){
		long l = System.currentTimeMillis();
		while(l==last){
			l = System.currentTimeMillis();
		}
		last = l;
		return String.valueOf(l);
	}
	
	public static String next(String prefix){
		return prefix+next();
	}
	
}

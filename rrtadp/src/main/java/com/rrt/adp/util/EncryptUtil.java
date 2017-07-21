/*
 * File Name: EncryptUtil.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-6-15

 * Modifier: Wuwuhao
 * Modify Date: 2016-6-15
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.rrt.adp.util;

import java.security.MessageDigest;

/**
 * 〈一句话功能简述〉
 * 
 * @author Wuwuhao
 * @version WSERP V1.0.0, 2016-6-15
 * @see
 * @since WSERP V1.0.0
 */

public class EncryptUtil {
	public static String md5(String s) {
		String retn = null;
		if (null == s || s.length() < 1) {
			return retn;
		}
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(s.getBytes("UTF-8"));
			retn = toHexString(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return retn;
	}

	private static String toHexString(byte[] bytes) {
		StringBuilder retn = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				retn.append("0");
			}
			retn.append(hex);
		}

		return retn.toString();
	}
}

package com.rrt.adp.web;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class RestSecurityTest {
	
	private static Cache<String, String> sessionCache = 
			CacheBuilder.newBuilder().expireAfterAccess(100, TimeUnit.SECONDS).build();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception{
		sessionCache.put("test", "testcache");
		System.out.println(sessionCache.getIfPresent("test"));
		Thread.sleep(500);
		System.out.println("sleep "+sessionCache.getIfPresent("test"));
		Thread.sleep(1010);
		System.out.println("sleep "+sessionCache.getIfPresent("test"));
	}

}

package com.rrt.adp.util;

import static org.junit.Assert.*;


import org.junit.Test;


public class JpushTest {
	
	

	@Test
	public void testGetTagAlias() throws Exception{
		PushUtil jpush = new PushUtil();
		jpush.push("13000000000", "testAlert1300", "testTitle1300");
		
		
	}

}

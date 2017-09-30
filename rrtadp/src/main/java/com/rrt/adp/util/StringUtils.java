package com.rrt.adp.util;

import java.util.HashSet;
import java.util.Set;

public class StringUtils {
	
	public static Set<String> toSet(String str){
		if(null==str){
			return null;
		}
		Set<String> set = new HashSet<>();
		for(String s: str.split(",")){
			set.add(s);
		}
		return set;
	}

}

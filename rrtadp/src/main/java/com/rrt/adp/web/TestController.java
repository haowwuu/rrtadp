package com.rrt.adp.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	public TestController() {
		System.out.println("TestController constructed......");
	}
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String testMVC(Map<String,Object> map){
		map.put("info", "陈鹏万里");
		return "test";
	}

}

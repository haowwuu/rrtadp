package com.rrt.adp.web;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rrt.adp.util.JsonUtil;
import com.rrt.adp.web.RestResult.CODE;

/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年9月30日
 * 
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc
 */
public class LoginRestIntercepter extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestIntercepter.class);
	private Set<String> bypassApiSet = new HashSet<>();
	@Resource
	private JsonUtil jsonUtil;
	
	public LoginRestIntercepter(String bypassApis) {
		if(null!=bypassApis){
			Set<String> set = new HashSet<>();
			for(String s: bypassApis.split(",")){
				set.add(s);
			}
			this.bypassApiSet = set;
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof HandlerMethod){
			/*HandlerMethod hm = (HandlerMethod)handler;
			Method method = hm.getMethod();*/
			String uri = request.getRequestURI();
	    	String context = request.getContextPath();
	    	String subUri = uri.substring(uri.indexOf(context)+context.length());
	    	
	    	//System.out.println("subUrl:"+subUri);
	    	
	    	if(!isLogedIn(request)&&!bypassApiSet.contains(subUri)){
	    		RestResult result = new RestResult(CODE.RET_NOT_LOGGED_IN, "session expired", null);
	    		try {
	    			response.setContentType("application/json");
	    			PrintWriter out = response.getWriter();
	    			out.print(jsonUtil.toJson(result));
	        		out.flush();
	        		out.close();
	    		} catch (Exception e) {
	    			LOGGER.error("write response [{}] exception [{}]", jsonUtil.toJson(result), e.getMessage());
	    		}
	    		return false;
	    	}
	    	
		}

		return true;
	}
	
	private boolean isLogedIn(HttpServletRequest request) {
		return null!=RestSecurity.getSessionAccount(request);
	}

	public Set<String> getBypassApiSet() {
		return bypassApiSet;
	}

	public void setBypassApiSet(Set<String> bypassApiSet) {
		this.bypassApiSet = bypassApiSet;
	}

}

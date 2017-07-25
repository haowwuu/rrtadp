/*
 * File Name: PageFilter.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-12-2

 * Modifier: Wuwuhao
 * Modify Date: 2016-12-2
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.rrt.adp.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-12-2
 * @see       
 * @since     DSPlite V0.2.0
 */

public class PageFilter implements Filter{
	
	private Set<String> bypassPageSet = new HashSet<String>();
	private String basePath = "/WEB-INF/html";
	
	@Override
	public  void init(FilterConfig filterconfig) throws ServletException{
		String bypassPages = filterconfig.getInitParameter("bypass-page");
		String[] arr = bypassPages.split(",");
		if(null!=arr&&arr.length>0){
			for(String s:arr){
				bypassPageSet.add(s.trim());
			}
		}
		String bp = filterconfig.getInitParameter("base-path");
		if(null!=bp){
			basePath = bp;
		}
	}
	
	@Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse,
    		FilterChain filterchain) throws IOException, ServletException{
    	HttpServletRequest request = (HttpServletRequest)servletrequest;
    	String uri = request.getRequestURI();
    	String context = request.getContextPath();
    	String page = uri.substring(uri.indexOf(context)+context.length());

    	if(bypassPageSet.contains(page)||page.indexOf(".html")<0){
    		filterchain.doFilter(servletrequest, servletresponse);
    		return;
    	}
    	
    	RequestDispatcher rd= hasPrivilege(request)? 
    			request.getRequestDispatcher(basePath+page):request.getRequestDispatcher("/");
    	rd.forward(request, servletresponse);
    	//filterchain.doFilter(servletrequest, servletresponse);
    	return;
    }
    
    static boolean hasPrivilege(HttpServletRequest request){
    	if(null==request||null==request.getSession()||
				null==request.getSession().getAttribute("isAdmin")){
			return false;
		}
		
		return (Boolean)request.getSession().getAttribute("isAdmin");
    }
    
    public void destroy(){
    	
    }
}

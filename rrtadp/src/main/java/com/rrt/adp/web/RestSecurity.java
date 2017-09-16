package com.rrt.adp.web;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.rrt.adp.model.Account;

public class RestSecurity {
	private static final String SESSION_NAME = "account";
	private static final String TOKEN = "token";
	
	private static Cache<String, Account> sessionCache = 
			CacheBuilder.newBuilder().expireAfterAccess(240, TimeUnit.MINUTES).build();
	
	public static String writeSession(Account account, HttpServletRequest request){
		if(null==account||null==request){
			return null;
		}
		String sessionId = request.getSession().getId();
		account.setToken(sessionId);
		account.setPassword(null);
		request.getSession().setAttribute(SESSION_NAME, account);
		sessionCache.put(sessionId, account);
		return sessionId;
	}
	
	public static Account getSessionAccount(HttpServletRequest request){
		if(null==request){
			return null;
		}
		Account account = (Account)request.getSession().getAttribute(SESSION_NAME);
		if(null!=account){
			return account;
		}
		
		String token = request.getHeader(TOKEN);
		if(null==token){
			token = request.getParameter(TOKEN);
		}
		return null==token?null:sessionCache.getIfPresent(token);
	}
	
	public static boolean isAdmin(HttpServletRequest request){
		Account account = getSessionAccount(request);
		return null!=account&&account.isAdmin();
	}
	
	public static boolean isUserOwn(HttpServletRequest request){
		Account sessionAccount = getSessionAccount(request);
		String account = request.getParameter("account");
		if(null==sessionAccount||null==account){
			return false;
		}
		return account.equals(sessionAccount.getAccount());
	}
}

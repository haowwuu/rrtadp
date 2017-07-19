package com.rrt.adp.web.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;
import com.rrt.adp.service.UserService;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.RequestMessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	@Resource
	private MessageUtil msgUtil;
	
	@RequestMapping("/login")
	public RestResult login(Account account, HttpServletRequest request) {
		System.out.println("sessionid" + request.getSession().getId());
		Account retn = userService.login(account);
		if(null!=retn){
			RestSecurity.writeSession(retn, request);
			return RestResult.defaultSuccessResult(retn, msgUtil.get("login.success"));
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@RequestMapping("/regist/person")
	public RestResult registPersonUser(PersonUser user){
		PersonUser retn = userService.registPersonUser(user);
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn, msgUtil.get("regist.success"));
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@RequestMapping("/regist/company")
	public RestResult registCompanyUser(CompanyUser user) {
		CompanyUser retn = userService.registCompanyUser(user);
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn, msgUtil.get("regist.success"));
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@RequestMapping("/audit")
	public RestResult auditUser(Account account, HttpServletRequest request){
		if(!RestSecurity.isAdmin(request)){
			return RestResult.defaultFailResult(msgUtil.get("permission.deny"));
		}
		if(null==account||!account.isStateLegal()){
			return RestResult.defaultFailResult(msgUtil.get("user.state.illegal"));
		}
		if(userService.updateAccount(account)){
			return RestResult.defaultSuccessResult("success");
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@RequestMapping("/testSession")
	public RestResult testSession(HttpServletRequest request){
		return RestResult.defaultSuccessResult(RestSecurity.getSessionAccount(request), "test session");
	}

}

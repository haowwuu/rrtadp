package com.rrt.adp.web;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;
import com.rrt.adp.service.UserService;
import com.rrt.adp.util.RequestMessageContext;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public RestResult login(Account account) {
		Account retn = userService.login(account.getAccount(), account.getPassword());
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn, "login success");
		}else{
			return RestResult.defaultFailResult(null, RequestMessageContext.getMsg());
		}
	}
	
	@RequestMapping("/regist/person")
	public RestResult registPersonUser(PersonUser user){
		PersonUser retn = userService.registPersonUser(user);
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn, "regist success");
		}else{
			return RestResult.defaultFailResult(null, RequestMessageContext.getMsg());
		}
	}
	
	@RequestMapping("regist/company")
	public RestResult registCompanyUser(CompanyUser user) {
		CompanyUser retn = userService.registCompanyUser(user);
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn, "regist success");
		}else{
			return RestResult.defaultFailResult(null, RequestMessageContext.getMsg());
		}
	}

}

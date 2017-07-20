package com.rrt.adp.web.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;
import com.rrt.adp.service.UserService;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.RequestMessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	@Resource
	private MessageUtil msgUtil;
	
	@ApiOperation("用户登录")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public RestResult login(@RequestBody Account account, HttpServletRequest request) {
		System.out.println("sessionid" + request.getSession().getId());
		Account retn = userService.login(account);
		if(null!=retn){
			RestSecurity.writeSession(retn, request);
			return RestResult.defaultSuccessResult(retn, msgUtil.get("login.success"));
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("个人用户注册")
	@RequestMapping(value="/regist/person", method=RequestMethod.POST)
	public RestResult registPersonUser(@RequestBody PersonUser user){
		PersonUser retn = userService.registPersonUser(user);
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn, msgUtil.get("regist.success"));
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("企业用户注册")
	@RequestMapping(value="/regist/company", method=RequestMethod.POST)
	public RestResult registCompanyUser(@RequestBody CompanyUser user) {
		CompanyUser retn = userService.registCompanyUser(user);
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn, msgUtil.get("regist.success"));
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("用户审核，修改用户注册状态")
	@RequestMapping(value="/audit", method=RequestMethod.POST)
	public RestResult auditUser(@RequestBody Account account, HttpServletRequest request){
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
	
	@ApiOperation("删除用户")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public RestResult deleteUser(String account, HttpServletRequest request){
		Account user = new Account();
		user.setAccount(account);
		user.setState(Account.STATE_DELETE);
		return auditUser(user, request);
	}
	
	@ApiOperation("获取个人用户列表")
	@RequestMapping(value="/personUerList", method=RequestMethod.GET)
	public RestResult getPersonUserList(HttpServletRequest request){
		if(!RestSecurity.isAdmin(request)){
			return RestResult.defaultFailResult(msgUtil.get("permission.deny"));
		}
		return RestResult.defaultSuccessResult(userService.getPersonUserList(), "success");
	}
	
	@ApiOperation("获取企业用户列表")
	@RequestMapping(value="/companyUserList", method=RequestMethod.GET)
	public RestResult getCompanyUserList(HttpServletRequest request){
		if(!RestSecurity.isAdmin(request)){
			return RestResult.defaultFailResult(msgUtil.get("permission.deny"));
		}
		return RestResult.defaultSuccessResult(userService.getCompanyUserList(), "success");
	}
	
	@ApiOperation("接口字典")
	@RequestMapping(value="/dictionary", method=RequestMethod.GET)
	public RestResult testSession(){
		return RestResult.defaultSuccessResult(Account.dictionary(), "dictionary");
	}
	
	@ApiOperation("更新个人用户信息")
	@RequestMapping(value="/updatePersonUser", method=RequestMethod.POST)
	public RestResult updatePersonUser(@RequestBody PersonUser user, HttpServletRequest request){
		if(!RestSecurity.isUserOwn(request)){
			return RestResult.defaultFailResult(msgUtil.get("permission.deny"));
		}
		if(userService.updatePersonUser(user)){
			return RestResult.defaultSuccessResult("success");
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("更新企业用户信息")
	@RequestMapping(value="/updateCompanyUser", method=RequestMethod.POST)
	public RestResult updateCompanyUser(@RequestBody CompanyUser user, HttpServletRequest request){
		if(!RestSecurity.isUserOwn(request)){
			return RestResult.defaultFailResult(msgUtil.get("permission.deny"));
		}
		if(userService.updateCompanyUser(user)){
			return RestResult.defaultSuccessResult("success");
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}

}

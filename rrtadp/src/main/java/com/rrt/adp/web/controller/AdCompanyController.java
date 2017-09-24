package com.rrt.adp.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.AdCompany;
import com.rrt.adp.model.Page;
import com.rrt.adp.service.AdCompanyService;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/adcompany")
public class AdCompanyController {
	
	@Resource
	private AdCompanyService companyService;
	
	@ApiOperation("新建广告公司")
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public RestResult createAdCompany(AdCompany company, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		boolean retn = companyService.addAdCompany(company, account);
		if(retn){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("修改广告公司")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public RestResult updateAdCompany(AdCompany company, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		boolean retn = companyService.updateAdCompany(company, account);
		if(retn){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("删除广告公司")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public RestResult deleteAdCompany(String companyId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		boolean retn = companyService.deleteAdCompany(companyId, account);
		if(retn){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("分页查询广告公司")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public RestResult deleteAdCompany(AdCompany company, Page<AdCompany> page){
		Page<AdCompany> companys = companyService.getAdCompanyPage(company, page);
		if(null!=companys){
			return RestResult.defaultSuccessResult(companys);
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}	

}

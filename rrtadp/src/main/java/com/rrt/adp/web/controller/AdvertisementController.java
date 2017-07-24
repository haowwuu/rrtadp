package com.rrt.adp.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Advertisement;
import com.rrt.adp.service.AdvertisementService;
import com.rrt.adp.util.RequestMessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/ad")
public class AdvertisementController {
	
	@Resource
	private AdvertisementService adService;
	
	@ApiOperation("新建广告")
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public RestResult createAd(Advertisement ad, MultipartFile adFile, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		Advertisement retn = adService.addAd(ad, adFile, account);
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn);
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("获取个人广告列表")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public RestResult getUserAdList(HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		return RestResult.defaultSuccessResult(adService.getUserAdList(account));
	}
	
	@ApiOperation("更新广告信息、状态")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public RestResult updateAd(Advertisement ad, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(adService.updateAd(ad, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("删除广告")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public RestResult deleteAd(String adId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(adService.deleteAd(adId, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
}

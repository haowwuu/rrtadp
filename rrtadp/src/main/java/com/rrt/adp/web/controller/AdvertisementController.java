package com.rrt.adp.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.Page;
import com.rrt.adp.service.AdvertisementService;
import com.rrt.adp.util.MessageContext;
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
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("depreciated 获取个人广告列表，建议使用page接口传入owner参数")
	@RequestMapping(value="/my", method=RequestMethod.GET)
	public RestResult getUserAdList(HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(RestSecurity.isAdmin(request)){
			return RestResult.defaultSuccessResult(adService.getAdList(null, account));
		}
		return RestResult.defaultSuccessResult(adService.getUserAdList(account));
	}
	
	@ApiOperation("根据条件获取广告列表")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public RestResult geAdList(Advertisement ad, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		return RestResult.defaultSuccessResult(adService.getAdList(ad, account));
	}
	
	@ApiOperation("更新广告信息、状态")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public RestResult updateAd(Advertisement ad, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(adService.updateAd(ad, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("删除广告")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public RestResult deleteAd(String adId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(adService.deleteAd(adId, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("分页获取广告，分页参数pageNum， pageSize， 默认返回审核通过的广告，传入owner参数可以获取个人所有广告")
	@RequestMapping(value="page", method={RequestMethod.GET, RequestMethod.POST})
	public RestResult pageAd(Advertisement ad, Page<Advertisement> page, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		Page<Advertisement> ads = adService.getUserAdPage(ad, account, page);
		if(null!=ads){
			return RestResult.defaultSuccessResult(ads);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
}

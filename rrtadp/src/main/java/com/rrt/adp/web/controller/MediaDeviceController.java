package com.rrt.adp.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.service.MediaDeviceService;
import com.rrt.adp.util.RequestMessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/device")
public class MediaDeviceController {
	
	@Resource
	private MediaDeviceService deviceService;
	
	@ApiOperation("新建媒体设备")
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public RestResult createAd(MediaDevice device, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(deviceService.addMediaDevice(device, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("获取个人媒体设备列表")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public RestResult getUserAdList(HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		return RestResult.defaultSuccessResult(deviceService.getUserMediaDevcieList(account));
	}
	
	@ApiOperation("更新媒体设备信息、状态")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public RestResult updateAd(MediaDevice device, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(deviceService.updateMediaDevice(device, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("删除媒体设备")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public RestResult deleteAd(String deviceId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(deviceService.deleteMediaDevice(deviceId, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
}

package com.rrt.adp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.model.ObjectFile;
import com.rrt.adp.model.Page;
import com.rrt.adp.service.MediaDeviceService;
import com.rrt.adp.util.FileUtil;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/device")
public class MediaDeviceController {
	
	@Resource
	private MediaDeviceService deviceService;
	@Resource
	private FileUtil fileUtil;
	
	@ApiOperation("新建媒体设备")
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public RestResult createDevice(MediaDevice device, HttpServletRequest request, 
			MultipartFile devicePicture0, MultipartFile devicePicture1, MultipartFile devicePicture2){
		
		Account account = RestSecurity.getSessionAccount(request);
		if(!deviceService.addMediaDevice(device, account)){
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
		List<String> picList = new ArrayList<>();
		if(null!=devicePicture0){
			ObjectFile objFile = new ObjectFile(device.getId(), MediaDevice.ATTR_DEVICEPICTURE, 0);
			String up = fileUtil.uploadFile(objFile, devicePicture0);
			if(null==up){
				deviceService.deleteMediaDevice(device.getId(), account);
				return RestResult.defaultFailResult(MessageContext.getMsg());
			}
			picList.add(up);
		}
		if(null!=devicePicture1){
			ObjectFile objFile = new ObjectFile(device.getId(), MediaDevice.ATTR_DEVICEPICTURE, 1);
			String up = fileUtil.uploadFile(objFile, devicePicture1);
			if(null==up){
				deviceService.deleteMediaDevice(device.getId(), account);
				return RestResult.defaultFailResult(MessageContext.getMsg());
			}
			picList.add(up);
		}
		if(null!=devicePicture2){
			ObjectFile objFile = new ObjectFile(device.getId(), MediaDevice.ATTR_DEVICEPICTURE, 2);
			String up = fileUtil.uploadFile(objFile, devicePicture2);
			if(null==up){
				deviceService.deleteMediaDevice(device.getId(), account);
				return RestResult.defaultFailResult(MessageContext.getMsg());
			}
			picList.add(up);
		}
		device.setDevicePictureUrls(picList);
		return RestResult.defaultSuccessResult(device);
	}
	
	@ApiOperation("获取个人媒体设备列表")
	@RequestMapping(value="/my", method=RequestMethod.GET)
	public RestResult getUserDeviceList(HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(RestSecurity.isAdmin(request)){
			return RestResult.defaultSuccessResult(deviceService.getMediaDevcieList(null, account));
		}
		return RestResult.defaultSuccessResult(deviceService.getUserMediaDevcieList(account));
	}
	
	@ApiOperation("根据条件获取个人媒体设备列表")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public RestResult getDeviceList(MediaDevice device, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		return RestResult.defaultSuccessResult(deviceService.getMediaDevcieList(device, account));
	}
	
	@ApiOperation("更新媒体设备信息、状态")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public RestResult updateDevice(MediaDevice device, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(deviceService.updateMediaDevice(device, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("删除媒体设备")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public RestResult deleteDevice(String deviceId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(deviceService.deleteMediaDevice(deviceId, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("分页获取设备，分页参数pageNum， pageSize， 默认返回审核通过的设备，传入owner参数可以获取个人所有设备")
	@RequestMapping(value="page", method={RequestMethod.GET, RequestMethod.POST})
	public RestResult pageDevice(MediaDevice device, Page<MediaDevice> page, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		Page<MediaDevice> devices = deviceService.getMediaDevicePage(device, account, page);
		if(null!=devices){
			return RestResult.defaultSuccessResult(devices);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("分页获取热门设备，分页参数pageNum， pageSize， 当前按照设备的订单数量排序")
	@RequestMapping(value="hotbyorder", method={RequestMethod.GET, RequestMethod.POST})
	public RestResult pageHotDevice(MediaDevice device, Page<MediaDevice> page, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		Page<MediaDevice> devices = deviceService.getHotMediaDevicePage(device, account, page);
		if(null!=devices){
			return RestResult.defaultSuccessResult(devices);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("传入设备id, basePrice， 获取设备建议价格")
	@RequestMapping(value="adviseprice", method=RequestMethod.GET)
	public RestResult getAdvistPirce(MediaDevice device) {
		Float advisePrice = deviceService.getAdvisePrice(device);
		if(null!=advisePrice){
			return RestResult.defaultSuccessResult(advisePrice);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
}

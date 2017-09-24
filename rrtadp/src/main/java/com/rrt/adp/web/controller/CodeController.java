package com.rrt.adp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Code;
import com.rrt.adp.model.DistrictCode;
import com.rrt.adp.service.CodeService;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/code")
public class CodeController {
	
	@Resource
	private CodeService codeService;
	
	@ApiOperation("获取省级行政区划列表")
	@RequestMapping(value="/district/toplist", method=RequestMethod.GET)
	public RestResult getTopDistrictList(){
		List<DistrictCode> codes = codeService.getTopDistrictCode();
		if(null!=codes){
			return RestResult.defaultSuccessResult(codes);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("根据id获取下级行政区划列表")
	@RequestMapping(value="/district/childlist", method=RequestMethod.GET)
	public RestResult getChildDistrictList(int id){
		List<DistrictCode> codes = codeService.getChildDistrictCode(id);
		if(null!=codes){
			return RestResult.defaultSuccessResult(codes);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("根据行政区划代码districtCode获取详情")
	@RequestMapping(value="/district/detail", method=RequestMethod.GET)
	public RestResult getDistrictDetail(String districtCode) {
		DistrictCode code = codeService.getDistrictCode(districtCode);
		if(null!=code){
			return RestResult.defaultSuccessResult(code);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("获取所有设备标签")
	@RequestMapping(value="/deviceTag/all", method=RequestMethod.GET)
	public RestResult getDeviceTag() {
		List<Code> codes = codeService.getCodeList(Code.TYPE_AD_TAG);
		if(null!=codes){
			return RestResult.defaultSuccessResult(codes);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("新增设备标签")
	@RequestMapping(value="/deviceTag/add", method=RequestMethod.POST)
	public RestResult addDeviceTag(Code code, HttpServletRequest request) {
		Account account = RestSecurity.getSessionAccount(request);
		code.setType(Code.TYPE_AD_TAG);
		boolean retn = codeService.addCode(code, account);
		if(retn){
			return RestResult.defaultSuccessResult();
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("更新设备标签")
	@RequestMapping(value="/deviceTag/update", method=RequestMethod.POST)
	public RestResult updateDeviceTag(Code code, HttpServletRequest request) {
		Account account = RestSecurity.getSessionAccount(request);
		code.setType(Code.TYPE_AD_TAG);
		boolean retn = codeService.updateCode(code, account);
		if(retn){
			return RestResult.defaultSuccessResult();
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("删除设备标签")
	@RequestMapping(value="/deviceTag/delete", method=RequestMethod.POST)
	public RestResult deleteDeviceTag(String codeId, HttpServletRequest request) {
		Account account = RestSecurity.getSessionAccount(request);
		boolean retn = codeService.deleteCode(codeId, account);
		if(retn){
			return RestResult.defaultSuccessResult();
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	

}

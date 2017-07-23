package com.rrt.adp.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.dao.DistrictCodeDao;
import com.rrt.adp.web.RestResult;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/code")
public class CodeController {
	
	@Resource
	private DistrictCodeDao districtCodeDao;
	
	@ApiOperation("获取省级行政区划列表")
	@RequestMapping(value="/district/toplist", method=RequestMethod.GET)
	public RestResult getTopDistrictList(){
		return RestResult.defaultSuccessResult(districtCodeDao.selectTopLevel(), "success");
	}
	
	@ApiOperation("根据id获取下级行政区划列表")
	@RequestMapping(value="/district/childlist", method=RequestMethod.GET)
	public RestResult getChildDistrictList(int id){
		return RestResult.defaultSuccessResult(districtCodeDao.selectChild(id), "success");
	}
	
	@ApiOperation("根据行政区划代码districtCode获取详情")
	@RequestMapping(value="/district/detail", method=RequestMethod.GET)
	public RestResult getDistrictDetail(String districtCode) {
		return RestResult.defaultSuccessResult(districtCodeDao.selectByCityCode(districtCode), "success");
	}

}

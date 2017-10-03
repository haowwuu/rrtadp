package com.rrt.adp.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.model.Account;
import com.rrt.adp.util.FileUtil;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
public class FileUploadController {
	
	@Resource
	private FileUtil fileUtil;
	
	@ApiOperation("上传文件， 参数id为设备id，或者广告id，或用户账号等(如MD1500729361092)， attr为id实体的图片属性 如封面cover 头像avatar， file为文件"
			+ "通过 host/adfile/${id}/${attr} 获取文件")
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public RestResult createAd(String id, String attr, MultipartFile file, HttpServletRequest request){
		if(null==id){
			return RestResult.defaultFailResult("[id] null");
		}
		if(null==attr){
			return RestResult.defaultFailResult("[attr] null");
		}
		Account account = RestSecurity.getSessionAccount(request);
		if(null==account||null==account.getAccount()){
			return RestResult.defaultFailResult("permission deny, please login.");
		}
		String fileName = id+"/"+attr;
		
		String retn = fileUtil.uploadFile(fileName, file);
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn);
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
}

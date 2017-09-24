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
	
	@ApiOperation("上传文件， (参数id为设备id(如MD1500729361092)， index为文件编号(如1,2,3等)， file为文件)"
			+ "通过 host/adfile/${owner}/${id-index} 获取文件")
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public RestResult createAd(String id, Integer index, MultipartFile file, HttpServletRequest request){
		if(null==id){
			return RestResult.defaultFailResult("id null");
		}
		Account account = RestSecurity.getSessionAccount(request);
		if(null==account||null==account.getAccount()){
			return RestResult.defaultFailResult("permission deny, please login.");
		}
		String fileName = account.getAccount()+"/"+id;
		if(null!=index){
			fileName += "-"+index%5;
		}
		String retn = fileUtil.uploadFile(fileName, file);
		if(null!=retn){
			return RestResult.defaultSuccessResult(retn, "success");
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
}

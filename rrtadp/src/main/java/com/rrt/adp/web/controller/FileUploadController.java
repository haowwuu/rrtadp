package com.rrt.adp.web.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.dao.ObjectFileDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.ObjectFile;
import com.rrt.adp.util.FileUtil;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
public class FileUploadController {
	private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
	@Resource
	private FileUtil fileUtil;
	@Resource
	private MessageUtil msgUtil;
	@Resource
	private ObjectFileDao objFileDao;
	
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
	
	@ApiOperation("上传文件， 参数objectId, objectAttr, fileIndex, file, 文件的url会保存到对象相应的Attr属性中")
	@RequestMapping(value="/upload/v1", method=RequestMethod.POST)
	public RestResult createAd(ObjectFile objFile, MultipartFile file, HttpServletRequest request){
		if(null==objFile||null==objFile.getObjectId()){
			return RestResult.defaultFailResult("[objectId] null");
		}
		if(null==objFile.getObjectAttr()){
			return RestResult.defaultFailResult("[objectAttr] null");
		}
		Account account = RestSecurity.getSessionAccount(request);
		if(null==account||null==account.getAccount()){
			return RestResult.defaultFailResult(msgUtil.get("permission.deny"));
		}
		String fileName = file.getOriginalFilename();
		objFile.setFileName(fileName);
		fileName = objFile.buildFullFileName();
		
		String retn = fileUtil.uploadFile(fileName, file);
		if(null!=retn){
			objFile.setFileUrl(retn);
			if(saveFileUrl(objFile)){
				return RestResult.defaultSuccessResult(retn);
			}
		}
		
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	private boolean saveFileUrl(ObjectFile objectFile){
		try{
			objFileDao.replaceObjectFile(objectFile);
		}catch (Exception e) {
			LOGGER.error("saveFileUrl [{}] exception [{}]", objectFile, e.getMessage());
			MessageContext.setMsg(msgUtil.get("db.exception"));
			return false;
		}
		return true;
	}
	
}

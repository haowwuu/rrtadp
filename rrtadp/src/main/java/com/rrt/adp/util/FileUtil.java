package com.rrt.adp.util;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

@Component
public class FileUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	@Value("${file.base.path}")
	private String baseFilePath;
	@Value("${file.base.url}")
	private String baseFileUrl;
	@Resource
	private MessageUtil msgUtil;
	
	public String uploadFile(String fileName, byte[] contents){
		if(null==fileName){
			fileName = SequenceGenerator.next();
		}
		File file = new File(baseFilePath+fileName);
		File parenFile = file.getParentFile();
		if(!parenFile.exists()){
			parenFile.mkdirs();
		}
		try{
			Files.write(contents, file);
		}catch (IOException e) {
			RequestMessageContext.setMsg(msgUtil.get("upload.exception"));
			LOGGER.error("upload file exception. " + e.getMessage());
			return null;
		}
		return baseFileUrl+fileName;
	}
	
	public String uploadFile(String fileName, MultipartFile mfile){
		if(null==mfile){
			return null;
		}
		String mfileName = mfile.getOriginalFilename();
		if(null==fileName){
			fileName = mfileName;
		}/*else{
			int idx = mfileName.indexOf(".");
			if(idx>=0){
				String subfix = mfileName.substring(idx);
				fileName += subfix;
			}
		}*/
		byte[] contents = new byte[0];
		try{
			contents =  mfile.getBytes();
		}catch (IOException e) {
			RequestMessageContext.setMsg(msgUtil.get("file.broken"));
			LOGGER.error("file broken. "+e.getMessage());
			return null;
		}
		return uploadFile(fileName, contents);
	}
	
	
}

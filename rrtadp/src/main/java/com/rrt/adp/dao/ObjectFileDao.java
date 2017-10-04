package com.rrt.adp.dao;

import java.util.List;

import com.rrt.adp.model.ObjectFile;

public interface ObjectFileDao {
	
	int insertObjectFile(ObjectFile objFile);
	
	int updateObjectFile(ObjectFile objectFile);
	
	int replaceObjectFile(ObjectFile objectFile);
	
	int deleteObjectFile(String objId, String objAttrName, int fileIdx);
	
	String selectObjFileUrl(String objId, String objAttrName, int fileIdx);
	
	List<String> selectObjFileUrlList(String objId, String objAttrName);
	
	ObjectFile selectObjFile(String objId, String objAttrName, int fileIdx);

}

package com.rrt.adp.dao;

import java.util.List;

import com.rrt.adp.model.Code;

public interface CodeDao {
	
	int insertCode(Code code);
	
	int updateCode(Code code);
	
	int deleteCode(String codeId);
	
	List<Code> selectCode(Code code);

}

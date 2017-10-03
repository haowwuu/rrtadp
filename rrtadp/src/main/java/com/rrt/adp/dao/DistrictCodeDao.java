package com.rrt.adp.dao;

import java.util.List;

import com.rrt.adp.model.DistrictCode;

public interface DistrictCodeDao {
	
	List<DistrictCode> selectTopLevel();
	
	List<DistrictCode> selectChild(int parentId);
	
	DistrictCode selectByCityCode(String cityCode);
}

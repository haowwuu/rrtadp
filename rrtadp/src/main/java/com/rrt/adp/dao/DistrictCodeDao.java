package com.rrt.adp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.rrt.adp.model.DistrictCode;

public interface DistrictCodeDao {
	
	@Select("select id, city_code, short_name from district_code where level = 0")
	@Results({ 
		@Result(property = "id", column = "id"),
		@Result(property = "cityCode", column = "city_code"),
	    @Result(property = "shortName", column = "short_name"),
	})
	List<DistrictCode> selectTopLevel();
	
	@Select("select id, city_code, short_name from district_code where parent_id = #{parentId}")
	@Results({ 
		@Result(property = "id", column = "id"),
		@Result(property = "cityCode", column = "city_code"),
	    @Result(property = "shortName", column = "short_name")
	})
	List<DistrictCode> selectChild(int parentId);
	
	@Select("select * from district_code where city_code = #{cityCode} order by id asc limit 1")
	@Results({ 
		@Result(property = "id", column = "id"),
		@Result(property = "parentId", column = "parent_id"),
	    @Result(property = "level", column = "level"),
	    @Result(property = "areaCode", column = "area_code"),
	    @Result(property = "zipCode", column = "zip_code"),
		@Result(property = "cityCode", column = "city_code"),
	    @Result(property = "shortName", column = "short_name"),
	    @Result(property = "name", column = "name"),
	    @Result(property = "mergerName", column = "merger_name"),
		@Result(property = "pinyin", column = "pinyin"),
	    @Result(property = "lng", column = "lng"),
	    @Result(property = "lat", column = "lat")
	})
	DistrictCode selectByCityCode(String cityCode);
}

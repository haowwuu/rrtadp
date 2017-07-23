package com.rrt.adp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.rrt.adp.model.Advertisement;

public interface AdvertisementDao {
	
	@Insert("insert into rrt_ad (id, create_time, update_time, title, type, content," +
			"state, content_url, time_in_second, owner)"+
			"values (#{id}, #{createTime}, #{updateTime}, #{title}, #{type}, #{content}, " +
			"#{state}, #{contentUrl}, #{timeInSecond}, #{owner})")
	int insertAd(Advertisement ad);
	
	@Delete("delete from rrt_ad where id = #{adId}")
	int deleteAd(String adId);
	
	@UpdateProvider(type=SqlProvider.class, method="updateAdvertisement")
	int updateAd(Advertisement ad);
	
	@Select("select * from rrt_ad where id = #{adId}")
	@Results({ 
		@Result(property = "id", column = "id"),
	    @Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
		@Result(property = "title", column = "title"), 
	    @Result(property = "type", column = "type"),
	    @Result(property = "state", column = "state"),
	    @Result(property = "content", column = "content"),
		@Result(property = "contentUrl", column = "content_url"),
		@Result(property = "timeInSecond", column = "time_in_second"),
		@Result(property = "owner", column = "owner")
	})
	Advertisement selectAd(String adId);
	
	@Select("select * from rrt_ad")
	@Results({
		@Result(property = "id", column = "id"),
	    @Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
		@Result(property = "title", column = "title"), 
	    @Result(property = "type", column = "type"),
	    @Result(property = "state", column = "state"),
	    @Result(property = "content", column = "content"),
		@Result(property = "contentUrl", column = "content_url"),
		@Result(property = "timeInSecond", column = "time_in_second"),
		@Result(property = "owner", column = "owner")
	})
	List<Advertisement> selectAdList();
	
	@Select("select * from rrt_ad where owner = #{account}")
	@Results({
		@Result(property = "id", column = "id"),
	    @Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
		@Result(property = "title", column = "title"), 
	    @Result(property = "type", column = "type"),
	    @Result(property = "state", column = "state"),
	    @Result(property = "content", column = "content"),
		@Result(property = "contentUrl", column = "content_url"),
		@Result(property = "timeInSecond", column = "time_in_second"),
		@Result(property = "owner", column = "owner")
	})
	List<Advertisement> selectUserAdList(String account);
}

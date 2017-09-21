package com.rrt.adp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.model.Page;


public interface MediaDeviceDao {
	
	@Insert("insert into rrt_media_device (id, create_time, update_time, device_type, device_status, " +
			"base_price, key_words, name, description, state, play_time, play_frequency, lng, lat, " +
			"district_code, address, owner)"+
			"values (#{id}, #{createTime}, #{updateTime}, #{deviceType}, #{deviceStatus}, " +
			"#{basePrice}, #{keyWords}, #{name}, #{description}, #{state}, #{playTime}, #{playFrequency}, #{lng}, #{lat}, " +
			"#{districtCode}, #{address}, #{owner})")
	int insertDevice(MediaDevice device);
	
	@Delete("delete from rrt_media_device where id = #{deviceId}")
	int deleteDevice(@Param("deviceId") String deviceId);
	
	@UpdateProvider(type=SqlProvider.class, method="updateMediaDevice")
	int updateDevice(MediaDevice device);
	
	@Select("select * from rrt_media_device where id = #{deviceId}")
	@Results({ 
		@Result(property = "id", column = "id"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
	    @Result(property = "deviceType", column = "device_type"),
		@Result(property = "deviceStatus", column = "device_status"),
		@Result(property = "basePrice", column = "base_price"), 
	    @Result(property = "keyWords", column = "key_words"),
	    @Result(property = "name", column = "name"),
	    @Result(property = "description", column = "description"),
		@Result(property = "state", column = "state"),
		@Result(property = "playTime", column = "play_time"),
		@Result(property = "playFrequency", column = "play_frequency"),
		@Result(property = "lng", column = "lng"),
		@Result(property = "lat", column = "lat"),
		@Result(property = "districtCode", column = "district_code"),
		@Result(property = "address", column = "address"),
		@Result(property = "owner", column = "owner")
	})
	MediaDevice selectDevice(@Param("deviceId") String deviceId);
	
	@Select("select * from rrt_media_device where owner = #{account}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
	    @Result(property = "deviceType", column = "device_type"),
		@Result(property = "deviceStatus", column = "device_status"),
		@Result(property = "basePrice", column = "base_price"), 
	    @Result(property = "keyWords", column = "key_words"),
	    @Result(property = "name", column = "name"),
	    @Result(property = "description", column = "description"),
		@Result(property = "state", column = "state"),
		@Result(property = "playTime", column = "play_time"),
		@Result(property = "playFrequency", column = "play_frequency"),
		@Result(property = "lng", column = "lng"),
		@Result(property = "lat", column = "lat"),
		@Result(property = "districtCode", column = "district_code"),
		@Result(property = "address", column = "address"),
		@Result(property = "districtCode", column = "district_code"),
		@Result(property = "address", column = "address"),
		@Result(property = "owner", column = "owner")
	})
	List<MediaDevice> selectUserDeviceList(String account);
	
	@SelectProvider(type=SqlProvider.class, method="selectMediaDevice")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
	    @Result(property = "deviceType", column = "device_type"),
		@Result(property = "deviceStatus", column = "device_status"),
		@Result(property = "basePrice", column = "base_price"), 
	    @Result(property = "keyWords", column = "key_words"),
	    @Result(property = "name", column = "name"),
	    @Result(property = "description", column = "description"),
		@Result(property = "state", column = "state"),
		@Result(property = "playTime", column = "play_time"),
		@Result(property = "playFrequency", column = "play_frequency"),
		@Result(property = "lng", column = "lng"),
		@Result(property = "lat", column = "lat"),
		@Result(property = "districtCode", column = "district_code"),
		@Result(property = "address", column = "address"),
		@Result(property = "districtCode", column = "district_code"),
		@Result(property = "address", column = "address"),
		@Result(property = "owner", column = "owner")
	})
	List<MediaDevice> selectDeviceList(MediaDevice device);
	
	List<MediaDevice> selectDeviceList(MediaDevice device, Page<?> page);
	
	int countDevice(MediaDevice device);
	
}

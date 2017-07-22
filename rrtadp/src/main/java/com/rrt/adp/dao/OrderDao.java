package com.rrt.adp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.rrt.adp.model.Order;

public interface OrderDao {
	
	@Insert("insert into rrt_order (id, create_time, update_time, ad_id, device_id," +
			"price, state, owner)"+
			"values (#{id}, #{createTime}, #{updateTime}, #{adId}, #{deviceId}, " +
			"#{price}, #{state}, #{owner})")
	int insertOrder(Order order);
	
	@Delete("delete from rrt_order where id = #{orderId}")
	int deleteOrder(String orderId);
	
	@UpdateProvider(type=SqlProvider.class, method="updateOrder")
	int updateOrder(Order order);
	
	@Select("select * from rrt_order where id = #{orderId}")
	@Results({ 
		@Result(property = "id", column = "id"),
	    @Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
		@Result(property = "adId", column = "ad_id"), 
	    @Result(property = "deviceId", column = "device_id"),
	    @Result(property = "price", column = "price"),
		@Result(property = "state", column = "state"),
		@Result(property = "owner", column = "owner")
	})
	Order selectOrder(String orderId);
	
	@Select("select * from rrt_order")
	@Results({ 
		@Result(property = "id", column = "id"),
	    @Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
		@Result(property = "adId", column = "ad_id"),
	    @Result(property = "deviceId", column = "device_id"),
	    @Result(property = "price", column = "price"),
		@Result(property = "state", column = "state"),
		@Result(property = "owner", column = "owner")
	})
	List<Order> selectOrderList();
}

package com.rrt.adp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.rrt.adp.model.Order;

public interface OrderDao {
	
	@Insert("insert into rrt_order (id, create_time, update_time, ad_id, device_id," +
			"price, state, ad_owner, device_owner)"+
			"values (#{id}, #{createTime}, #{updateTime}, #{adId}, #{deviceId}, " +
			"#{price}, #{state}, #{adOwner}, #{deviceOwner})")
	int insertOrder(Order order);
	
	@Delete("delete from rrt_order where id = #{orderId}")
	int deleteOrder(String orderId);
	
	@UpdateProvider(type=SqlProvider.class, method="updateOrder")
	int updateOrder(Order order);
	
	@Update("")
	int updateOrderBid(Order order);
	
	@Select("select * from rrt_order where id = #{orderId}")
	@Results({ 
		@Result(property = "id", column = "id"),
	    @Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
		@Result(property = "adId", column = "ad_id"), 
	    @Result(property = "deviceId", column = "device_id"),
	    @Result(property = "price", column = "price"),
		@Result(property = "state", column = "state"),
		@Result(property = "adOwner", column = "ad_owner"),
		@Result(property = "deviceOwner", column = "device_owner")
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
		@Result(property = "adOwner", column = "ad_owner"),
		@Result(property = "deviceOwner", column = "device_owner")
	})
	List<Order> selectOrderList();
	
	@Select("select * from rrt_order where ad_owner = #{account} or device_owner = #{account}")
	@Results({ 
		@Result(property = "id", column = "id"),
	    @Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
		@Result(property = "adId", column = "ad_id"),
	    @Result(property = "deviceId", column = "device_id"),
	    @Result(property = "price", column = "price"),
		@Result(property = "state", column = "state"),
		@Result(property = "adOwner", column = "ad_owner"),
		@Result(property = "deviceOwner", column = "device_owner")
	})
	List<Order> selectUserOrderList(String account);
	
	@Select("select DISTINCT device_id  from rrt_order WHERE create_time > DATE_SUB(NOW(), INTERVAL 1 DAY)")
	List<String> selectBidDevice();
	@Update("update rrt_order set state = 'S' "
			+ "WHERE device_id = #{deviceId} and state = 'N' or state = 'C' order by price DESC LIMIT 20")
	int updateDeviceBidSuccess(String deviceId);
	
	@Update("update rrt_order set state = 'F' "
			+ "WHERE state = 'N' or state = 'C' and create_time > DATE_SUB(NOW(), INTERVAL 10 DAY)")
	int updateDeviceBidFail();
}

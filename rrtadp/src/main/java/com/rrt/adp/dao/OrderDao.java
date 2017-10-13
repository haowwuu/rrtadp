package com.rrt.adp.dao;

import java.util.List;
import java.util.Map;

import com.rrt.adp.model.Order;
import com.rrt.adp.model.Page;

public interface OrderDao {
	
	int insertOrder(Order order);
	
	int deleteOrder(String orderId);
	
	int updateOrder(Order order);
	
	int updateOrderBid(Order order);
	
	Order selectOrder(String orderId);
	
	Order selectUserOrder(String orderId, String account);
	
	List<Order> selectOrderList();
	
	List<Order> selectUserOrderList(String account);
	
	List<String> selectBidDevice();
	
	int updateDeviceBidSuccess(String deviceId);
	
	int updateDeviceBidFail(String deviceId);
	
	int updateDeviceBidFail();
	
	List<Order> selectOrderList(Order order, Page<?> page);
	
	int countOrder(Order order);
	
	Map<Object, Object> selectGroupbyAdOrderbyCount(Page<?> page);
	
	Map<Object, Object> selectGroupbyDeviceOrderbyCount(Page<?> page);
}

package com.rrt.adp.service;

import java.util.List;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Order;
import com.rrt.adp.model.Page;

public interface OrderService {
	
	boolean addOrder(Order order, Account account);
	
	List<Order> getUserOrderList(Account account);
	
	Order getOrder(String orderId, Account account);
	
	boolean updateOrder(Order order, Account account);
	
	boolean deleteOrder(String orderId, Account account);
	
	void bid();
	
	public boolean bid(String deviceId);
	
	Page<Order> getOrderPage(Order order, Account account, Page<Order> page);
	
}

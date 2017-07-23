package com.rrt.adp.service;

import java.util.List;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Order;

public interface OrderService {
	
	boolean addOrder(Order order, Account account);
	
	List<Order> getUserOrderList(Account account);
	
	boolean updateOrder(Order order, Account account);
	
	boolean deleteOrder(String orderId, Account account);
}

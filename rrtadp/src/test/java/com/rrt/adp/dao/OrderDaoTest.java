package com.rrt.adp.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rrt.adp.model.Order;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class OrderDaoTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private OrderDao orderDao;
	
	@Ignore
	public void testInsertOrder() {
		Order order = new Order();
		order.setAdId("AD1500731358736");
		order.setDeviceId("MD1500729361092");
		order.setPrice(200);
		order.setState(Order.STATE_NEW);
		order.setAdOwner("rrtgg");
		order.setDeviceOwner("admin");
		orderDao.insertOrder(order);
	}

	@Ignore
	public void testDeleteOrder() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testUpdateOrder() {
		Order order = new Order();
		order.setId("OR1500735546969");
		order.setState(Order.STATE_CHECKED);
		order.setDeviceOwner("admin");
		orderDao.updateOrder(order);
	}

	@Ignore
	public void testSelectOrder() {
		Order order = orderDao.selectOrder("OR1500735546969");
		System.out.println(order);
	}

	@Ignore
	public void testSelectOrderList() {
		List<Order> orders = orderDao.selectOrderList();
		orders.stream().forEach(System.out::println);
	}
	
	@Ignore
	public void testSelectUserOrderList() {
		List<Order> orders = orderDao.selectUserOrderList("rrtgg");
		orders.stream().forEach(System.out::println);
	}
	
	@Ignore
	public void testUpdataBid(){
		List<String> list = orderDao.selectBidDevice();
		list.stream().forEach(System.out::println);
		orderDao.updateDeviceBidSuccess("abc");
		orderDao.updateDeviceBidFail();
	}
	
	@Test
	public void testSelectUserOrder(){
		System.out.println(orderDao.selectUserOrder("OR1500815056870", "admin0"));
	}

}

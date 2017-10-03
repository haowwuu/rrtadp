package com.rrt.adp.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.request.NativeWebRequest;

import com.rrt.adp.model.Order;
import com.rrt.adp.model.Page;

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
	
	@Ignore
	public void testSelectUserOrder(){
		System.out.println(orderDao.selectUserOrder("OR1500815056870", "admin0"));
	}
	
	@Ignore
	public void testSelectPage(){
		Order order = new Order();
		Page<?> page = new Page<>(1, 4);
		orderDao.selectOrderList(order, page).stream().forEach(System.out::println);
	}
	
	@Ignore
	public void testCount() {
		Order order = new Order();
		int count = orderDao.countOrder(order);
		System.out.println(count);
	}
	
	@Test
	public void testSelectGroupbyAdOrderbyCount() {
		Page<?> page = new Page<>(1,10);
		Map<Object, Object> retn = orderDao.selectGroupbyAdOrderbyCount(page);
		System.out.println(retn);
		System.out.println(page);
	}
	
	@Test
	public void testSelectGroupbyDeviceOrderbyCount() {
		Page<?> page = new Page<>(1,10);
		Map<Object, Object> retn = orderDao.selectGroupbyDeviceOrderbyCount(page);
		System.out.println(retn);
		System.out.println(page);
	}

}

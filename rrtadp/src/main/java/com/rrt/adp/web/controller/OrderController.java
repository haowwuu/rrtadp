package com.rrt.adp.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Order;
import com.rrt.adp.model.Page;
import com.rrt.adp.service.OrderService;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private OrderService orderService;
	@Resource
	private MessageUtil msgUtil;
		
	
	@ApiOperation("新建订单")
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public RestResult createOrder(Order order, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(orderService.addOrder(order, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("获取个人订单列表")
	@RequestMapping(value="/my", method=RequestMethod.GET)
	public RestResult getUserOrderList(HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		return RestResult.defaultSuccessResult(orderService.getUserOrderList(account));
	}
	
	@ApiOperation("根据订单编号获取订单详细信息")
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public RestResult getOrderList(String orderId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		return RestResult.defaultSuccessResult(orderService.getOrder(orderId, account));
	}
	
	@ApiOperation("更新订单信息、状态")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public RestResult updateOrder(Order order, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(orderService.updateOrder(order, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("删除订单")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public RestResult deleteOrder(String orderId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(orderService.deleteOrder(orderId, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(MessageContext.getMsg());
		}
	}
	
	@ApiOperation("对某个设备开始订单竞价")
	@RequestMapping(value="/bid", method=RequestMethod.POST)
	public RestResult bidDevice(String deviceId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(orderService.bid(deviceId, account)){
			return RestResult.defaultSuccessResult();
		}
		
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("播放设备竞价成功广告")
	@RequestMapping(value="/play", method=RequestMethod.POST)
	public RestResult playAd(String deviceId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(orderService.palyAd(deviceId, account)){
			return RestResult.defaultSuccessResult();
		}
		
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	
	@ApiOperation("分页获取订单，分页参数pageNum， pageSize")
	@RequestMapping(value="page", method={RequestMethod.GET, RequestMethod.POST})
	public RestResult pageAd(Order order, Page<Order> page, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		Page<Order> orders = orderService.getOrderPage(order, account, page);
		if(null!=orders){
			return RestResult.defaultSuccessResult(orders);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}

}

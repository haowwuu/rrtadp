package com.rrt.adp.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Order;
import com.rrt.adp.service.OrderService;
import com.rrt.adp.util.RequestMessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private OrderService orderService;
	
	@ApiOperation("新建订单")
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public RestResult createAd(Order order, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(orderService.addOrder(order, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("获取个人订单列表")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public RestResult getUserAdList(HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		return RestResult.defaultSuccessResult(orderService.getUserOrderList(account));
	}
	
	@ApiOperation("更新订单信息、状态")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public RestResult updateAd(Order order, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(orderService.updateOrder(order, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}
	
	@ApiOperation("删除订单")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public RestResult deleteAd(String orderId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(orderService.deleteOrder(orderId, account)){
			return RestResult.defaultSuccessResult();
		}else{
			return RestResult.defaultFailResult(RequestMessageContext.getMsg());
		}
	}

}

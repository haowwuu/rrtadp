package com.rrt.adp.service.support;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rrt.adp.dao.AdvertisementDao;
import com.rrt.adp.dao.MediaDeviceDao;
import com.rrt.adp.dao.OrderDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.DBModel;
import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.model.Order;
import com.rrt.adp.service.OrderService;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.RequestMessageContext;
import com.rrt.adp.util.SequenceGenerator;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Resource
	private OrderDao orderDao;
	@Resource
	private AdvertisementDao adDao;
	@Resource
	private MediaDeviceDao deviceDao;
	
	@Resource
	private MessageUtil msgUtil;
	
	@Override
	public boolean addOrder(Order order, Account account) {
		if(null==order||null==account||null==account.getAccount()){
			return false;
		}
		order.setId(DBModel.PREFIX_ORDER+SequenceGenerator.next());
		if(null==order.getAdId()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.null", "adId"));
			return false;
		}
		if(null==order.getDeviceId()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.null", "deviceId"));
			return false;
		}
		Advertisement ad = adDao.selectAd(order.getAdId());
		if(null==ad){
			RequestMessageContext.setMsg(msgUtil.get("parameter.not.exist", "adId"));
			return false;
		}
		MediaDevice device = deviceDao.selectDevice(order.getDeviceId());
		if(null==device){
			RequestMessageContext.setMsg(msgUtil.get("parameter.not.exist", "deviceId"));
			return false;
		}
		order.setDeviceOwner(device.getOwner());
		order.setAdOwner(account.getAccount());
		order.setState(Order.STATE_NEW);
		orderDao.insertOrder(order);
		return true;
	}
	

	@Override
	public List<Order> getUserOrderList(Account account) {
		if(null==account||null==account.getAccount()){
			return null;
		}
		List<Order> orders = null;
		if(account.isAdmin()){
			orders = orderDao.selectOrderList();
		}else{
			orders = orderDao.selectUserOrderList(account.getAccount());
		}
		return orders;
	}
	
	@Override
	public Order getOrder(String orderId, Account account) {
		if(null==orderId||null==account||null==account.getAccount()){
			return null;
		}
		Order order = null;
		if(account.isAdmin()){
			order = orderDao.selectOrder(orderId);
		}else{
			order = orderDao.selectUserOrder(orderId, account.getAccount());
		}
		if(null!=order){
			order.setAdvertisement(adDao.selectAd(order.getAdId()));
			order.setMediaDevice(deviceDao.selectDevice(order.getDeviceId()));
		}
		return order;
	}

	@Override
	public boolean updateOrder(Order order, Account account) {
		if(null==order&&null==account){
			return false;
		}
		if(null!=order.getState()&&!order.isStateLegal()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.illegal", "state"));
			return false;
		}
		Order dbOrder = orderDao.selectOrder(order.getId());
		if(null==dbOrder){
			return false;
		}
		if(!account.getAccount().equals(order.getAdOwner())&&!account.isAdmin()){
			RequestMessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		orderDao.updateOrder(order);
		return true;
	}

	@Override
	public boolean deleteOrder(String orderId, Account account) {
		if(null==orderId){
			return false;
		}
		Order order = new Order();
		order.setId(orderId);
		order.setState(Order.STATE_DELETE);
		return updateOrder(order, account);
	}

	@Scheduled(cron="0 0 17 * * ?")
	@Override
	public void bid() {
		List<String> deviceList = orderDao.selectBidDevice();
		for(String device:deviceList){
			orderDao.updateDeviceBidSuccess(device);
		}
		orderDao.updateDeviceBidFail();
		LOGGER.error("info message, report the bid procedure has proceeded");
	}

}

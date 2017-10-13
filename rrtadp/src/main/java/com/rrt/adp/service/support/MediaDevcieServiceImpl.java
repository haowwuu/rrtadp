package com.rrt.adp.service.support;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rrt.adp.dao.MediaDeviceDao;
import com.rrt.adp.dao.ObjectFileDao;
import com.rrt.adp.dao.OrderDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.DBModel;
import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.model.Order;
import com.rrt.adp.model.Page;
import com.rrt.adp.service.AdPlayService;
import com.rrt.adp.service.MediaDeviceService;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.util.SequenceGenerator;

@Service
public class MediaDevcieServiceImpl implements MediaDeviceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MediaDevcieServiceImpl.class);
	
	@Resource
	private MediaDeviceDao deviceDao;
	@Resource
	private ObjectFileDao objFileDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private MessageUtil msgUtil;
	@Resource
	private AdPlayService adPlayService;
	
	@Override
	public boolean addMediaDevice(MediaDevice device, Account account) {
		if(null==device||null==account){
			return false;
		}
		String playId = adPlayService.bindDevice(device);
		if(null==playId){
			MessageContext.setMsg(msgUtil.get("device.bind.fail"));
			return false;
		}
		device.setPlayId(playId);
		device.setId(DBModel.PREFIX_MEDIA_DEVICE+SequenceGenerator.next());
		device.setState(MediaDevice.STATE_NEW);
		if(null==device.getOwner()){
			device.setOwner(account.getAccount());
		}
		if(!device.isStatusLegal()){
			MessageContext.setMsg(msgUtil.get("parameter.illegal","deviceStatus"));
			return false;
		}
		if(!device.isTypeLegal()){
			MessageContext.setMsg(msgUtil.get("parameter.illegal", "deviceType"));
			return false;
		}
		deviceDao.insertDevice(device);
		return true;
	}

	@Override
	public List<MediaDevice> getUserMediaDevcieList(Account account) {
		if(null==account||null==account.getAccount()){
			return null;
		}
		
		return deviceDao.selectUserDeviceList(account.getAccount());
	}
	
	@Override
	public List<MediaDevice> getMediaDevcieList(MediaDevice device, Account account) {
		if(null==account){
			return null;
		}
		if(!account.isAdmin()){
			if(null==device){
				device = new MediaDevice();
			}
			device.setState(MediaDevice.STATE_CHECKED);
		}
		return deviceDao.selectDeviceList(device);
	}

	@Override
	public boolean updateMediaDevice(MediaDevice device, Account account) {
		if(null==device||null==device.getId()||null==account){
			return false;
		}
		if(null!=device.getDeviceStatus()&&!device.isStatusLegal()){
			MessageContext.setMsg(msgUtil.get("parameter.illegal","deviceStatus"));
			return false;
		}
		if(null!=device.getState()&&!device.isStateLegal()){
			MessageContext.setMsg(msgUtil.get("parameter.illegal","state"));
			return false;
		}
		if(null!=device.getDeviceType()&&!device.isTypeLegal()){
			MessageContext.setMsg(msgUtil.get("parameter.illegal", "deviceType"));
			return false;
		}
		MediaDevice dbDevice = deviceDao.selectDevice(device.getId());
		if(null==dbDevice){
			return false;
		}
		if(!dbDevice.getOwner().equals(account.getAccount())&&!account.isAdmin()){
			MessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		deviceDao.updateDevice(device);
		return true;
	}

	@Override
	public boolean deleteMediaDevice(String deviceId, Account account) {
		if(null==deviceId){
			return false;
		}
		MediaDevice device = new MediaDevice();
		device.setId(deviceId);
		device.setState(MediaDevice.STATE_DELETE);
		return updateMediaDevice(device, account);
	}

	@Override
	public Page<MediaDevice> getMediaDevicePage(MediaDevice device, Account account, Page<MediaDevice> page) {
		if(null==account||null==account.getAccount()){
			return null;
		}
		device = null==device? new MediaDevice():device;
		if(!account.isAdmin()&&!account.getAccount().equals(device.getOwner())){
			device.setState(MediaDevice.STATE_CHECKED);
		}
		return selectDevicePage(device, page);
	}
	
	private Page<MediaDevice> selectDevicePage(MediaDevice device, Page<MediaDevice> page){
		CompletableFuture<List<MediaDevice>> devicefuture = new CompletableFuture<>();
		new Thread(() -> {
			try{
				List<MediaDevice> devices = deviceDao.selectDeviceList(device, page);
				for(MediaDevice dev:devices){
					dev.setDevicePictureUrls(objFileDao.selectObjFileUrlList(dev.getId(), MediaDevice.ATTR_DEVICEPICTURE));
				}
				devicefuture.complete(devices);
			}catch (Exception e) {
				LOGGER.error("selectDevicePage device[{}] page[{}] exception [{}]", device, page, e.getMessage());
				devicefuture.completeExceptionally(e);
			}
		}).start();
		
		try{
			page.setTotal(deviceDao.countDevice(device));
			page.setList(devicefuture.get());
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("selectDevicePage ad[{}] page[{}] exception [{}]", device, page, e.getMessage());
			return null;
		} 
		
		return page;
	}

	@Override
	public Page<MediaDevice> getHotMediaDevicePage(MediaDevice device, Account account, Page<MediaDevice> page) {
		if(null==account||null==account.getAccount()){
			return null;
		}
		device = null==device? new MediaDevice():device;
		device.setState(MediaDevice.STATE_CHECKED);
		
		final MediaDevice selectDev = device;
		CompletableFuture<List<MediaDevice>> devicefuture = new CompletableFuture<>();
		new Thread(() -> {
			try{
				List<MediaDevice> devices = deviceDao.selectDeviceListOrderByOrder(selectDev, page);
				for(MediaDevice dev:devices){
					dev.setDevicePictureUrls(objFileDao.selectObjFileUrlList(dev.getId(), MediaDevice.ATTR_DEVICEPICTURE));
				}
				devicefuture.complete(devices);
			}catch (Exception e) {
				LOGGER.error("selectDevicePage device[{}] page[{}] exception [{}]", selectDev, page, e.getMessage());
				devicefuture.completeExceptionally(e);
			}
		}).start();
		
		try{
			page.setTotal(deviceDao.countDevice(selectDev));
			page.setList(devicefuture.get());
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("selectDevicePage ad[{}] page[{}] exception [{}]", selectDev, page, e.getMessage());
			return null;
		} 
		
		return page;
	}

	@Override
	public Float getAdvisePrice(MediaDevice device) {
		if(null==device||null==device.getId()){
			return 0f;
		}
		float price = device.getBasePrice();
		if(device.getBasePrice()<0.001){
			MediaDevice md = deviceDao.selectDevice(device.getId());
			if(null==md){
				return 0f;
			}
			price = md.getBasePrice();
		}
		Order order = new Order();
		order.setDeviceId(device.getId());
		order.setState(Order.STATE_NEW);
		int newOrderCount = 0;
		try{
			newOrderCount = orderDao.countOrder(order);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("getAdvisePrice device[{}] exception [{}]", device, e.getMessage());
			return null;
		}
		
		if(newOrderCount>=5){
			price *= 1.2;
		}else if(newOrderCount>=3){
			price *=1.05;
		}
		return price;
	}

}

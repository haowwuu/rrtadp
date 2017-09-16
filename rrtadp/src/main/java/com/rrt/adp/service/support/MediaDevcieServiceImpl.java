package com.rrt.adp.service.support;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.rrt.adp.dao.MediaDeviceDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.DBModel;
import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.service.MediaDeviceService;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.RequestMessageContext;
import com.rrt.adp.util.SequenceGenerator;

@Service
public class MediaDevcieServiceImpl implements MediaDeviceService {
	
	@Resource
	private MediaDeviceDao deviceDao;
	@Resource
	private MessageUtil msgUtil;
	
	@Override
	public boolean addMediaDevice(MediaDevice device, Account account) {
		if(null==device||null==account){
			return false;
		}
		device.setId(DBModel.PREFIX_MEDIA_DEVICE+SequenceGenerator.next());
		device.setState(MediaDevice.STATE_NEW);
		device.setOwner(account.getAccount());
		if(!device.isStatusLegal()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.illegal","deviceStatus"));
			return false;
		}
		if(!device.isTypeLegal()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.illegal", "deviceType"));
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
			RequestMessageContext.setMsg(msgUtil.get("parameter.illegal","deviceStatus"));
			return false;
		}
		if(null!=device.getState()&&!device.isStateLegal()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.illegal","state"));
			return false;
		}
		if(null!=device.getDeviceType()&&!device.isTypeLegal()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.illegal", "deviceType"));
			return false;
		}
		MediaDevice dbDevice = deviceDao.selectDevice(device.getId());
		if(null==dbDevice){
			return false;
		}
		if(!dbDevice.getOwner().equals(account.getAccount())&&!account.isAdmin()){
			RequestMessageContext.setMsg(msgUtil.get("permission.deny"));
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

}

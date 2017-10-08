package com.rrt.adp.service;

import java.util.List;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.model.Page;

public interface MediaDeviceService {
	
	boolean addMediaDevice(MediaDevice device, Account account);
	
	List<MediaDevice> getUserMediaDevcieList(Account account);
	
	List<MediaDevice> getMediaDevcieList(MediaDevice device, Account account);
	
	boolean updateMediaDevice(MediaDevice device, Account account);
	
	boolean deleteMediaDevice(String deviceId, Account account);
	
	Page<MediaDevice> getMediaDevicePage(MediaDevice device, Account account, Page<MediaDevice> page);
	
	Page<MediaDevice> getHotMediaDevicePage(MediaDevice device, Account account, Page<MediaDevice> page);
	
	Float getAdvisePrice(MediaDevice device);
}

package com.rrt.adp.service;

import java.util.List;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.MediaDevice;

public interface MediaDeviceService {
	
	boolean addMediaDevice(MediaDevice device, Account account);
	
	List<MediaDevice> getUserMediaDevcieList(Account account);
	
	boolean updateMediaDevice(MediaDevice device, Account account);
	
	boolean deleteMediaDevice(String deviceId, Account account);
}

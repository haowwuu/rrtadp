package com.rrt.adp.dao;

import java.util.List;

import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.model.Page;


public interface MediaDeviceDao {
	
	int insertDevice(MediaDevice device);
	
	int deleteDevice(String deviceId);
	
	int updateDevice(MediaDevice device);
	
	MediaDevice selectDevice(String deviceId);
	
	List<MediaDevice> selectUserDeviceList(String account);
	
	List<MediaDevice> selectDeviceList(MediaDevice device);
	
	List<MediaDevice> selectDeviceList(MediaDevice device, Page<?> page);
	
	int countDevice(MediaDevice device);
	
	public List<MediaDevice> selectDeviceListOrderByOrder(MediaDevice device, Page<?> page);
}

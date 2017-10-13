package com.rrt.adp.service;

import java.util.List;

import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.MediaDevice;

public interface AdPlayService {
	
	boolean play(List<Advertisement> ads, MediaDevice device);
	
	boolean play(List<Advertisement> ads, List<MediaDevice> devices);
	
	//return playId
	String bindDevice(MediaDevice device);
}

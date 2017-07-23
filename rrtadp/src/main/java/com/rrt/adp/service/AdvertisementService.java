package com.rrt.adp.service;

import java.util.List;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Advertisement;

public interface AdvertisementService {
	
	boolean addAd(Advertisement ad, Account account);
	
	List<Advertisement> getUserAdList(Account account);
	
	boolean updateAd(Advertisement ad, Account account);
	
	boolean deleteAd(String adId, Account account);
}

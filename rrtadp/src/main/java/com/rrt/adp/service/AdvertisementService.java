package com.rrt.adp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.Page;

public interface AdvertisementService {
	
	Advertisement addAd(Advertisement ad, MultipartFile adFile, Account account);
	
	List<Advertisement> getUserAdList(Account account);
	
	List<Advertisement> getAdList(Advertisement ad, Account account);
	
	boolean updateAd(Advertisement ad, Account account);
	
	boolean deleteAd(String adId, Account account);
	
	Page<Advertisement> getUserAdPage(Advertisement ad, Account account, Page<Advertisement> page);
	
	Page<Advertisement> getHotAdPage(Advertisement ad, Account account, Page<Advertisement> page);
}

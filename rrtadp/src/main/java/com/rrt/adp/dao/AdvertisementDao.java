package com.rrt.adp.dao;

import java.util.List;

import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.Page;

public interface AdvertisementDao {
	
	int insertAd(Advertisement ad);
	
	int deleteAd(String adId);
	
	int updateAd(Advertisement ad);
	
	Advertisement selectAd(String adId);
	
	List<Advertisement> selectAdList(Advertisement ad);
	
	List<Advertisement> selectUserAdList(String account);
	
	List<Advertisement> selectAdList(Advertisement ad, Page<?> page);
	
	int countAd(Advertisement ad);
	
	public List<Advertisement> selectAdListOrderByOrder(Advertisement ad, Page<?> page);
}

package com.rrt.adp.service.support;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.dao.AdvertisementDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.Advertisement;
import com.rrt.adp.service.AdvertisementService;
import com.rrt.adp.util.FileUtil;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.RequestMessageContext;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	
	@Resource
	private AdvertisementDao adDao;
	@Resource
	private MessageUtil msgUtil;
	@Resource
	private FileUtil fileUtil;
	
	@Override
	public Advertisement addAd(Advertisement ad, MultipartFile adFile, Account account) {
		if(null==ad||null==account){
			return null;
		}
		ad.setState(Advertisement.STATE_NEW);
		ad.setOwner(account.getAccount());
		if(!ad.isTypeLegal()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.illegal", "type"));
			return null;
		}
		if(null!=adFile){
			String url = fileUtil.uploadFile(account.getAccount()+"/"+ad.getId(), adFile);
			if(null==url){
				return null;
			}
			ad.setContentUrl(url);
		}
		adDao.insertAd(ad);
		return ad;
	}
	
	@Override
	public List<Advertisement> getUserAdList(Account account) {
		if(null==account){
			return null;
		}
		if(account.isAdmin()){
			return adDao.selectAdList();
		}
		if(null!=account.getAccount()){
			return adDao.selectUserAdList(account.getAccount());
		}
		return null;
	}

	@Override
	public boolean updateAd(Advertisement ad, Account account) {
		if(null==ad||null==ad.getId()||null==account){
			return false;
		}
		if(null!=ad.getType()&&!ad.isTypeLegal()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.illegal", "type"));
			return false;
		}
		if(null!=ad.getState()&&!ad.isStateLegal()){
			RequestMessageContext.setMsg(msgUtil.get("parameter.illegal", "state"));
			return false;
		}
		Advertisement dbAd = adDao.selectAd(ad.getId());
		if(null==dbAd){
			return false;
		}
		if(!dbAd.getOwner().equals(account.getAccount())&&!account.isAdmin()){
			RequestMessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		adDao.updateAd(ad);
		return true;
	}

	@Override
	public boolean deleteAd(String adId, Account account) {
		if(null==adId){
			return false;
		}
		Advertisement ad = new Advertisement();
		ad.setId(adId);
		ad.setState(Advertisement.STATE_DELETE);
		return updateAd(ad, account);
	}

}

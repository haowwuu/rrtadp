package com.rrt.adp.service.support;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rrt.adp.dao.AdvertisementDao;
import com.rrt.adp.dao.ObjectFileDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.DBModel;
import com.rrt.adp.model.ObjectFile;
import com.rrt.adp.model.Page;
import com.rrt.adp.service.AdvertisementService;
import com.rrt.adp.util.FileUtil;
import com.rrt.adp.util.MessageUtil;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.util.SequenceGenerator;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementServiceImpl.class);
	
	@Resource
	private AdvertisementDao adDao;
	@Resource
	private ObjectFileDao objFileDao;
	@Resource
	private MessageUtil msgUtil;
	@Resource
	private FileUtil fileUtil;
	
	@Override
	public Advertisement addAd(Advertisement ad, MultipartFile adFile, Account account) {
		if(null==ad||null==account){
			return null;
		}
		ad.setId(DBModel.PREFIX_ADVERTISEMENT+SequenceGenerator.next());
		ad.setState(Advertisement.STATE_NEW);
		ad.setOwner(account.getAccount());
		if(!ad.isTypeLegal()){
			MessageContext.setMsg(msgUtil.get("parameter.illegal", "type"));
			return null;
		}
		if(null!=adFile){
			ObjectFile objFile = new ObjectFile(ad.getId(), Advertisement.ATTR_CONTENT, 0);
			String url = fileUtil.uploadFile(objFile, adFile);
			//String url = fileUtil.uploadFile(account.getAccount()+"/"+ad.getId(), adFile);
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
		if(null==account||null==account.getAccount()){
			return null;
		}
		
		return adDao.selectUserAdList(account.getAccount());
	}
	
	@Override
	public List<Advertisement> getAdList(Advertisement ad, Account account){
		if(null==account){
			return null;
		}
		if(!account.isAdmin()){
			if(null==ad){
				ad = new Advertisement();
			}
			ad.setState(Advertisement.STATE_CHECKED);
		}
		return adDao.selectAdList(ad);
	}

	@Override
	public boolean updateAd(Advertisement ad, Account account) {
		if(null==ad||null==ad.getId()||null==account){
			return false;
		}
		if(null!=ad.getType()&&!ad.isTypeLegal()){
			MessageContext.setMsg(msgUtil.get("parameter.illegal", "type"));
			return false;
		}
		if(null!=ad.getState()&&!ad.isStateLegal()){
			MessageContext.setMsg(msgUtil.get("parameter.illegal", "state"));
			return false;
		}
		Advertisement dbAd = adDao.selectAd(ad.getId());
		if(null==dbAd){
			return false;
		}
		if(!dbAd.getOwner().equals(account.getAccount())&&!account.isAdmin()){
			MessageContext.setMsg(msgUtil.get("permission.deny"));
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

	@Override
	public Page<Advertisement> getUserAdPage(Advertisement ad, Account account, Page<Advertisement> page) {
		if(null==account||null==account.getAccount()){
			return null;
		}
		ad = null==ad? new Advertisement():ad;
		if(!account.isAdmin()&&!account.getAccount().equals(ad.getOwner())){
			ad.setState(Advertisement.STATE_CHECKED);
		}
		return selectAdPage(ad, page);
	}
	
	private Page<Advertisement> selectAdPage(Advertisement ad, Page<Advertisement> page){
		CompletableFuture<List<Advertisement>> adfuture = new CompletableFuture<>();
		new Thread(() -> {
			try{
				List<Advertisement> advertisements = adDao.selectAdList(ad, page);
				for(Advertisement a:advertisements){
					if(Advertisement.TYPE_VIDEO.equals(a.getType())){
						a.setCoverUrl(objFileDao.selectObjFileUrl(a.getId(), Advertisement.ATTR_COVER, 0));
					}
				}
				adfuture.complete(advertisements);
			}catch (Exception e) {
				LOGGER.error("selectAdPage ad[{}] page[{}] exception [{}]", ad, page, e.getMessage());
				adfuture.completeExceptionally(e);
			}
		}).start();
		
		try{
			page.setTotal(adDao.countAd(ad));
			page.setList(adfuture.get());
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("selectAdPage ad[{}] page[{}] exception [{}]", ad, page, e.getMessage());
			return null;
		} 
		
		return page;
	}

	@Override
	public Page<Advertisement> getHotAdPage(Advertisement ad, Account account, Page<Advertisement> page) {
		if(null==account||null==account.getAccount()){
			return null;
		}
		ad = null==ad? new Advertisement():ad;
		ad.setState(Advertisement.STATE_CHECKED);
		final Advertisement selectAd = ad;
		CompletableFuture<List<Advertisement>> adfuture = new CompletableFuture<>();
		new Thread(() -> {
			try{
				List<Advertisement> advertisements = adDao.selectAdListOrderByOrder(selectAd, page);
				for(Advertisement a:advertisements){
					if(Advertisement.TYPE_VIDEO.equals(a.getType())){
						a.setCoverUrl(objFileDao.selectObjFileUrl(a.getId(), Advertisement.ATTR_COVER, 0));
					}
				}
				adfuture.complete(advertisements);
			}catch (Exception e) {
				LOGGER.error("selectAdListOrderByOrder ad[{}] page[{}] exception [{}]", selectAd, page, e.getMessage());
				adfuture.completeExceptionally(e);
			}
		}).start();
		
		try{
			page.setTotal(adDao.countAd(selectAd));
			page.setList(adfuture.get());
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("selectAdPage ad[{}] page[{}] exception [{}]", selectAd, page, e.getMessage());
			return null;
		} 
		
		return page;
	}

}

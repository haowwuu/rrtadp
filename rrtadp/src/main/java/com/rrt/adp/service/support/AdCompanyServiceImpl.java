package com.rrt.adp.service.support;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rrt.adp.dao.AdCompanyDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.AdCompany;
import com.rrt.adp.model.Page;
import com.rrt.adp.service.AdCompanyService;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.util.MessageUtil;

@Service
public class AdCompanyServiceImpl implements AdCompanyService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdCompanyServiceImpl.class);
	@Resource
	private AdCompanyDao companyDao;
	@Resource
	private MessageUtil msgUtil;

	@Override
	public boolean addAdCompany(AdCompany company, Account account) {
		if(!Account.isAdmin(account)){
			MessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		try{
			companyDao.insertAdCompany(company);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("[{}] addAdCompany code [{}] exception [{}]", account, company, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean updateAdCompany(AdCompany company, Account account) {
		if(!Account.isAdmin(account)){
			MessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		try{
			companyDao.updateAdCompany(company);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("[{}] updateAdCompany code [{}] exception [{}]", account, company, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteAdCompany(String companyId, Account account) {
		if(!Account.isAdmin(account)){
			MessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		try{
			companyDao.deleteAdCompany(companyId);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("[{}] addAdCompany code [{}] exception [{}]", account, companyId, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Page<AdCompany> getAdCompanyPage(AdCompany company, Page<AdCompany> page) {
		CompletableFuture<List<AdCompany>> companyfuture = new CompletableFuture<>();
		new Thread(() -> {
			try{
				List<AdCompany> advertisements = companyDao.selectAdCompany(company, page);
				companyfuture.complete(advertisements);
			}catch (Exception e) {
				LOGGER.error("selectAdPage ad[{}] page[{}] exception [{}]", company, page, e.getMessage());
				companyfuture.completeExceptionally(e);
			}
		}).start();
		
		try{
			page.setTotal(companyDao.countAdCompany(company));
			page.setList(companyfuture.get());
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("getAdCompanyPage ad[{}] page[{}] exception [{}]", company, page, e.getMessage());
			return null;
		} 
		return page;
	}

}

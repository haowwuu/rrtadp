package com.rrt.adp.service.support;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rrt.adp.dao.CodeDao;
import com.rrt.adp.dao.DistrictCodeDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.Code;
import com.rrt.adp.model.DistrictCode;
import com.rrt.adp.service.CodeService;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.util.MessageUtil;

@Service
public class CodeServiceImpl implements CodeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeServiceImpl.class);
	
	@Resource
	private CodeDao codeDao;
	@Resource
	private DistrictCodeDao districtDao;
	@Resource
	private MessageUtil msgUtil;

	@Override
	public List<Code> getCodeList(String type) {
		if(!StringUtils.hasText(type)){
			return new ArrayList<>();
		}
		Code code = new Code();
		code.setType(type);
		try{
			return codeDao.selectCode(code);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("getCodeList type [{}] exception [{}]", type, e.getMessage());
		}
		return null;
	}

	@Override
	public boolean addCode(Code code, Account account) {
		if(!Account.isAdmin(account)){
			MessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		try{
			codeDao.insertCode(code);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("[{}] addCode code [{}] exception [{}]", account, code, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean updateCode(Code code, Account account) {
		if(!Account.isAdmin(account)){
			MessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		try{
			codeDao.updateCode(code);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("[{}] updateCode code [{}] exception [{}]", account, code, e.getMessage());
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteCode(String codeId, Account account) {
		if(!Account.isAdmin(account)){
			MessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		try{
			codeDao.deleteCode(codeId);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("[{}] deleteCode code [{}] exception [{}]", account, codeId, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public List<DistrictCode> getTopDistrictCode() {
		try{
			return districtDao.selectTopLevel();
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("getTopDistrictCode exception [{}]", e.getMessage());
		}
		return null;
	}

	@Override
	public List<DistrictCode> getChildDistrictCode(int id) {
		try{
			return districtDao.selectChild(id);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("getChildDistrictCode id[{}] exception [{}]", id, e.getMessage());
		}
		return null;
	}

	@Override
	public DistrictCode getDistrictCode(String districtCode) {
		try{
			return districtDao.selectByCityCode(districtCode);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("getDistrictCode id[{}] exception [{}]", districtCode, e.getMessage());
		}
		return null;
	}

}

package com.rrt.adp.service;

import java.util.List;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Code;
import com.rrt.adp.model.DistrictCode;

public interface CodeService {
	
	List<Code> getCodeList(String type);
	
	boolean addCode(Code code, Account account);
	
	boolean updateCode(Code code, Account account);
	
	boolean deleteCode(String codeId, Account account);
	
	List<DistrictCode> getTopDistrictCode();
	
	List<DistrictCode> getChildDistrictCode(int id);
	
	DistrictCode getDistrictCode(String districtCode);

}

package com.rrt.adp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.rrt.adp.model.CompanyUser;

public interface CompanyUserDao {
	
	int insertUser(CompanyUser user);
	
	int deleteUser(@Param("account")String account);
	
	int updateUser(CompanyUser user);
	
	List<CompanyUser> selectUser();
	
	CompanyUser selectUserByAccount(@Param("account")String accont);
	
}

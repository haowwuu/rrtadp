package com.rrt.adp.dao;

import java.util.List;

import com.rrt.adp.model.PersonUser;

public interface PersonUserDao {
	
	int insertUser(PersonUser user);
	
	int deleteUser(String account);
	
	int updateUser(PersonUser user);
	
	List<PersonUser> selectUser();
	
	PersonUser selectUserByAccount(String accont);
}

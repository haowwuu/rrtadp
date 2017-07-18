package com.rrt.adp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.rrt.adp.model.PersonUser;

public interface PersonUserDao {
	
	@Insert("insert into user_person (id, create_time, update_time, account, password, description," +
			"account_type, account_role, account_state, user_name, nick_name, phone, email, " +
			"address, id_card, id_card_front_url, id_card_back_url)"+
			"values (#{id}, #{createTime}, #{updateTime}, #{account}, #{password}, #{description}, " +
			"#{type}, #{role}, #{state}, #{name}, #{nickName}, #{phone}, #{email}, " +
			"#{address}, #{IDCard}, #{IDCardFrontPicUrl}, #{IDCardBackPicUrl})")
	int insertUser(PersonUser user);
	
	@Delete("delete from user_person where account = #{account}")
	int deleteUser(@Param("account")String account);
	
	@UpdateProvider(type=SqlProvider.class, method="updatePersonUser")
	int updateUser(PersonUser user);
	
	@Select("select * from user_person")
	@Results({ 
		@Result(property = "id", column = "id"),
	    @Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
		@Result(property = "account", column = "account"), 
	    @Result(property = "password", column = "password"),
	    @Result(property = "description", column = "description"),
		@Result(property = "type", column = "account_type"),
		@Result(property = "role", column = "account_role"),
		@Result(property = "state", column = "account_state"),
		@Result(property = "name", column = "user_name"),
		@Result(property = "nickName", column = "nick_name"),
		@Result(property = "phone", column = "phone"),
		@Result(property = "email", column = "email"),
		@Result(property = "address", column = "address"),
		@Result(property = "IDCard", column = "id_card"),
		@Result(property = "IDCardFrontPicUrl", column = "id_card_front_url"),
		@Result(property = "IDCardBackPicUrl", column = "id_card_back_url")
	})
	List<PersonUser> selectUser();
	
	@Select("select * from user_person where account = #{account}")
	@Results({ 
		@Result(property = "id", column = "id"),
	    @Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time"),
		@Result(property = "account", column = "account"), 
	    @Result(property = "password", column = "password"),
	    @Result(property = "description", column = "description"),
		@Result(property = "type", column = "account_type"),
		@Result(property = "role", column = "account_role"),
		@Result(property = "state", column = "account_state"),
		@Result(property = "name", column = "user_name"),
		@Result(property = "nickName", column = "nick_name"),
		@Result(property = "phone", column = "phone"),
		@Result(property = "email", column = "email"),
		@Result(property = "address", column = "address"),
		@Result(property = "IDCard", column = "id_card"),
		@Result(property = "IDCardFrontPicUrl", column = "id_card_front_url"),
		@Result(property = "IDCardBackPicUrl", column = "id_card_back_url")
	})
	PersonUser selectUserByAccount(@Param("account")String accont);
}

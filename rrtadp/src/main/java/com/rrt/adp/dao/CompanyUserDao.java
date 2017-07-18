package com.rrt.adp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.rrt.adp.model.CompanyUser;

public interface CompanyUserDao {
	
	@Insert("insert into user_company (id, create_time, update_time, account, password, description," +
			"account_type, account_role, account_state, company_name, legal_person, contact_person, office_phone, " +
			"company_address, certificate, certiticate_front_url, certificate_back_url)"+
			"values (#{id}, #{createTime}, #{updateTime}, #{account}, #{password}, #{description}, " +
			"#{type}, #{role}, #{state}, #{companyName}, #{legalPerson}, #{contactPerson}, #{officePhone}, " +
			"#{companyAddress}, #{certificate}, #{certificateFrontPicUrl}, #{certificateBackPicUrl})")
	int insertUser(CompanyUser user);
	
	@Delete("delete from user_company where account = #{account}")
	int deleteUser(@Param("account")String account);
	
	@UpdateProvider(type=SqlProvider.class, method="updateCompanyUser")
	int updateUser(CompanyUser user);
	
	@Select("select * from user_company")
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
		@Result(property = "companyName", column = "company_name"),
		@Result(property = "legalPerson", column = "legal_person"),
		@Result(property = "contactPerson", column = "contact_person"),
		@Result(property = "officePhone", column = "office_phone"),
		@Result(property = "companyAddress", column = "company_address"),
		@Result(property = "certificate", column = "certificate"),
		@Result(property = "certificateFrontPicUrl", column = "certiticate_front_url"),
		@Result(property = "certificateBackPicUrl", column = "certificate_back_url")
	})
	List<CompanyUser> selectUser();
	
	@Select("select * from user_company where account = #{account}")
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
		@Result(property = "companyName", column = "company_name"),
		@Result(property = "legalPerson", column = "legal_person"),
		@Result(property = "contactPerson", column = "contact_person"),
		@Result(property = "officePhone", column = "office_phone"),
		@Result(property = "companyAddress", column = "company_address"),
		@Result(property = "certificate", column = "certificate"),
		@Result(property = "certificateFrontPicUrl", column = "certiticate_front_url"),
		@Result(property = "certificateBackPicUrl", column = "certificate_back_url")
	})
	CompanyUser selectUserByAccount(@Param("account")String accont);
	
}

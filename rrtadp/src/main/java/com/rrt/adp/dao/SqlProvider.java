package com.rrt.adp.dao;

import java.util.Date;
import org.springframework.util.StringUtils;

import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.PersonUser;


public class SqlProvider {

	public String updatePersonUser(PersonUser user){
		StringBuilder sb = new StringBuilder();
		sb.append("update user_person set ");
		user.setUpdateTime(new Date());
		sb.append("update_time = #{updateTime}");
		if(StringUtils.hasText(user.getPassword())){
			sb.append(",password = #{password}");
		}
		if(StringUtils.hasText(user.getDescription())){
			sb.append(",description = #{description}");
		}
		if(StringUtils.hasText(user.getType())){
			sb.append(",account_type = #{type}");
		}
		if(StringUtils.hasText(user.getRole())){
			sb.append(",account_role = #{role}");
		}
		if(StringUtils.hasText(user.getName())){
			sb.append(",user_name = #{name}");
		}
		if(StringUtils.hasText(user.getNickName())){
			sb.append(",nick_name = #{nickName}");
		}
		if(StringUtils.hasText(user.getPhone())){
			sb.append(",phone = #{phone}");
		}
		if(StringUtils.hasText(user.getEmail())){
			sb.append(",email = #{email}");
		}
		if(StringUtils.hasText(user.getDistrictCode())){
			sb.append(",district_code = #{districtCode}");
		}
		if(StringUtils.hasText(user.getAddress())){
			sb.append(",address = #{address}");
		}
		if(StringUtils.hasText(user.getIDCard())){
			sb.append(",id_card = #{IDCard}");
		}
		if(StringUtils.hasText(user.getIDCardFrontPicUrl())){
			sb.append(",id_card_front_url = #{IDCardFrontPicUrl}");
		}
		if(StringUtils.hasText(user.getIDCardBackPicUrl())){
			sb.append(",id_card_back_url = #{IDCardBackPicUrl}");
		}
		
		sb.append(" where account = #{account}");
		
		return sb.toString();	
	}
	
	public String updateCompanyUser(CompanyUser user){
		StringBuilder sb = new StringBuilder();
		sb.append("update user_company set ");
		user.setUpdateTime(new Date());
		sb.append("update_time = #{updateTime}");
		if(StringUtils.hasText(user.getPassword())){
			sb.append(",password = #{password}");
		}
		if(StringUtils.hasText(user.getDescription())){
			sb.append(",description = #{description}");
		}
		if(StringUtils.hasText(user.getType())){
			sb.append(",account_type = #{type}");
		}
		if(StringUtils.hasText(user.getRole())){
			sb.append(",account_role = #{role}");
		}
		if(StringUtils.hasText(user.getCompanyName())){
			sb.append(",company_name = #{companyName}");
		}
		if(StringUtils.hasText(user.getLegalPerson())){
			sb.append(",legal_person = #{legalPerson}");
		}
		if(StringUtils.hasText(user.getContactPerson())){
			sb.append(",contact_person = #{contactPerson}");
		}
		if(StringUtils.hasText(user.getContactPhone())){
			sb.append(",contact_phone = #{contactPhone}");
		}
		if(StringUtils.hasText(user.getOfficePhone())){
			sb.append(",office_phone = #{officePhone}");
		}
		if(StringUtils.hasText(user.getDistrictCode())){
			sb.append(",district_code = #{districtCode}");
		}
		if(StringUtils.hasText(user.getCompanyAddress())){
			sb.append(",company_address = #{companyAddress}");
		}
		if(StringUtils.hasText(user.getCertificate())){
			sb.append(",certificate = #{certificate}");
		}
		if(StringUtils.hasText(user.getCertificateFrontPicUrl())){
			sb.append(",certiticate_front_url = #{certificateFrontPicUrl}");
		}
		if(StringUtils.hasText(user.getCertificateBackPicUrl())){
			sb.append(",certificate_back_url = #{certificateBackPicUrl}");
		}
		
		sb.append(" where account = #{account}");
		
		return sb.toString();	
	}
}

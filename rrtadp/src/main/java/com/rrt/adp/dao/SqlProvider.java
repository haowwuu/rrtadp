package com.rrt.adp.dao;

import java.util.Date;
import org.springframework.util.StringUtils;
import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.model.Order;
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
		if(StringUtils.hasText(user.getState())){
			sb.append(",account_state = #{state}");
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
		if(StringUtils.hasText(user.getState())){
			sb.append(",account_state = #{state}");
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
	
	public String selectMediaDevice(MediaDevice device){
		StringBuilder sb = new StringBuilder();
		sb.append("select * from rrt_media_device where 1");
		if(StringUtils.hasText(device.getDeviceType())){
			sb.append(" and device_type = #{deviceType}");
		}
		if(StringUtils.hasText(device.getDeviceStatus())){
			sb.append(" and device_status = #{deviceStatus}");
		}
		if(device.getBasePrice()>0){
			sb.append(" and base_price <= #{basePrice}");
		}
		if(StringUtils.hasText(device.getKeyWords())){
			device.setKeyWords("%"+device.getKeyWords()+"%");
			sb.append(" and key_words like #{keyWords}");
		}
		if(StringUtils.hasText(device.getName())){
			device.setName("%"+device.getName()+"%");
			sb.append(" and name like #{name}");
		}
		if(StringUtils.hasText(device.getState())){
			sb.append(" and state = #{state}");
		}
		if(device.getLng()>0&&device.getLat()>0){
			sb.append(" and abs(lng - #{lng}) < 0.03 and abs(lat - #{lat}) < 0.03");
		}
		if(StringUtils.hasText(device.getDistrictCode())){
			sb.append(" and district_code = #{districtCode}");
		}
		if(StringUtils.hasText(device.getAddress())){
			device.setAddress("%"+device.getAddress()+"%");
			sb.append(" and address like #{address}");
		}
		if(StringUtils.hasText(device.getOwner())){
			sb.append(" and owner = #{owner}");
		}
		
		return sb.toString();
	}
	
	
	public String updateMediaDevice(MediaDevice device){
		StringBuilder sb = new StringBuilder();
		sb.append("update rrt_media_device set ");
		device.setUpdateTime(new Date());
		sb.append("update_time = #{updateTime}");
		if(StringUtils.hasText(device.getDeviceType())){
			sb.append(",device_type = #{deviceType}");
		}
		if(StringUtils.hasText(device.getDeviceStatus())){
			sb.append(",device_status = #{deviceStatus}");
		}
		if(device.getBasePrice()>0){
			sb.append(",base_price = #{basePrice}");
		}
		if(StringUtils.hasText(device.getKeyWords())){
			sb.append(",key_words = #{keyWords}");
		}
		if(StringUtils.hasText(device.getName())){
			sb.append(",name = #{name}");
		}
		if(StringUtils.hasText(device.getDescription())){
			sb.append(",description = #{description}");
		}
		if(StringUtils.hasText(device.getState())){
			sb.append(",state = #{state}");
		}
		if(null!=device.getPlayTime()){
			sb.append(",play_time = #{playTime}");
		}
		if(device.getPlayFrequency()>0){
			sb.append(",play_frequency = #{playFrequency}");
		}
		if(device.getLng()>0){
			sb.append(",lng = #{lng}");
		}
		if(device.getLat()>0){
			sb.append(",lat = #{lat}");
		}
		if(StringUtils.hasText(device.getDistrictCode())){
			sb.append(",district_code = #{districtCode}");
		}
		if(StringUtils.hasText(device.getAddress())){
			sb.append(",address = #{address}");
		}
		if(StringUtils.hasText(device.getOwner())){
			sb.append(",owner = #{owner}");
		}
		
		sb.append(" where id = #{id}");
		
		return sb.toString();	
	}
	
	public String updateOrder(Order order) {
		StringBuilder sb = new StringBuilder();
		sb.append("update rrt_order set ");
		order.setUpdateTime(new Date());
		sb.append("update_time = #{updateTime}");
		if(StringUtils.hasText(order.getAdId())){
			sb.append(",ad_id = #{adId}");
		}
		if(StringUtils.hasText(order.getDeviceId())){
			sb.append(",device_id = #{deviceId}");
		}
		if(order.getPrice()>0){
			sb.append(",price = #{price}");
		}
		if(StringUtils.hasText(order.getState())){
			sb.append(",state = #{state}");
		}
		if(StringUtils.hasText(order.getAdOwner())){
			sb.append(",ad_owner = #{adOwner}");
		}
		if(StringUtils.hasText(order.getDeviceOwner())){
			sb.append(",device_owner = #{deviceOwner}");
		}
		
		sb.append(" where id = #{id}");
		
		return sb.toString();	
	}
	
	public String selectAdvertisement(Advertisement ad){
		StringBuilder sb = new StringBuilder();
		sb.append("select * from rrt_ad where 1");
		if(StringUtils.hasText(ad.getTitle())){
			ad.setTitle("%"+ad.getTitle()+"%");
			sb.append(" and title like #{title}");
		}
		if(StringUtils.hasText(ad.getType())){
			sb.append(" and type = #{type}");
		}
		if(StringUtils.hasText(ad.getState())){
			sb.append(" and state = #{state}");
		}
		if(StringUtils.hasText(ad.getContent())){
			ad.setContent("%"+ad.getContent()+"%");
			sb.append(" and content like #{content}");
		}
		
		if(StringUtils.hasText(ad.getOwner())){
			sb.append(" and owner = #{owner}");
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	public String updateAdvertisement(Advertisement ad) {
		StringBuilder sb = new StringBuilder();
		sb.append("update rrt_ad set ");
		ad.setUpdateTime(new Date());
		sb.append("update_time = #{updateTime}");
		if(StringUtils.hasText(ad.getTitle())){
			sb.append(",title = #{title}");
		}
		if(StringUtils.hasText(ad.getType())){
			sb.append(",type = #{type}");
		}
		if(StringUtils.hasText(ad.getState())){
			sb.append(",state = #{state}");
		}
		if(StringUtils.hasText(ad.getContent())){
			sb.append(",content = #{content}");
		}
		if(StringUtils.hasText(ad.getContentUrl())){
			sb.append(",content_url = #{contentUrl}");
		}
		if(ad.getTimeInSecond()>0){
			sb.append(",time_in_second = #{timeInSecond}");
		}
		if(StringUtils.hasText(ad.getOwner())){
			sb.append(",owner = #{owner}");
		}
		
		sb.append(" where id = #{id}");
		
		return sb.toString();	
	}
}

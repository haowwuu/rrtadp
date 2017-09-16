package com.rrt.adp.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import com.rrt.adp.dao.CompanyUserDao;
import com.rrt.adp.model.CompanyUser;
import com.rrt.adp.util.SequenceGenerator;

/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年9月16日
 * 
 */
public class CompanyUserDaoImpl implements CompanyUserDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertUser(CompanyUser user) {
		if(null==user){
			return 0;
		}
		user.setId(SequenceGenerator.next());
		user.setCreateTime(new Date());
		user.setState(CompanyUser.STATE_NEW);
		return this.jdbcTemplate.update("insert into user_company (id, create_time, update_time, "
				+ "account, password, description, account_type, account_role, account_state, "
				+ "company_name, legal_person, contact_person, contact_phone, office_phone, "
				+ "district_code, company_address, certificate, certiticate_front_url, certificate_back_url) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[]{
						user.getId(),
						user.getCreateTime(),
						user.getUpdateTime(),
						user.getAccount(),
						user.getPassword(),
						user.getDescription(),
						user.getType(),
						user.getRole(),
						user.getState(),
						user.getCompanyName(),
						user.getLegalPerson(),
						user.getContactPerson(),
						user.getContactPhone(),
						user.getOfficePhone(),
						user.getDistrictCode(),
						user.getCompanyAddress(),
						user.getCertificate(),
						user.getCertificateFrontPicUrl(),
						user.getCertificateBackPicUrl(),
				});
	}

	@Override
	public int deleteUser(String account) {
		if(null==account){
			return 0;
		}
		return this.jdbcTemplate.update("delete from user_company where account = ?", account);
	}

	@Override
	public int updateUser(CompanyUser user) {
		Object[] update = buildUpdateSql(user);
		if(null==update){
			return 0;
		}
		String sql = (String)update[update.length-1];
		Object[] values = new Object[update.length-1];
		System.arraycopy(update, 0, values, 0, update.length-1);
		return this.jdbcTemplate.update(sql, values);
	}
	
	private List<CompanyUser> select(CompanyUser user){
		Object[] select = buildSelectSql(user);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.query(sql, values, new CompanyUserMapper());
	}

	@Override
	public List<CompanyUser> selectUser() {
		CompanyUser user = new CompanyUser();
		return select(user);
	}

	@Override
	public CompanyUser selectUserByAccount(String accont) {
		if(!StringUtils.hasText(accont)){
			return null;
		}
		CompanyUser user = new CompanyUser();
		user.setAccount(accont);
		List<CompanyUser> users = select(user);
		if(null==users||users.size()==0){
			return null;
		}
		return users.get(0);
	}
	
	private static final class CompanyUserMapper implements RowMapper<CompanyUser> {

	    public CompanyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
	        CompanyUser user = new CompanyUser();
	        user.setId(rs.getString("id"));
	        user.setCreateTime(rs.getDate("create_time"));
	        user.setUpdateTime(rs.getDate("update_time"));
	        user.setAccount(rs.getString("account"));
	        user.setPassword(rs.getString("password"));
	        user.setDescription(rs.getString("description"));
	        user.setType(rs.getString("account_type"));
	        user.setRole(rs.getString("account_role"));
	        user.setState(rs.getString("account_state"));
	        user.setCompanyName(rs.getString("company_name"));
	        user.setLegalPerson(rs.getString("legal_person"));
	        user.setContactPerson(rs.getString("contact_person"));
	        user.setContactPhone(rs.getString("contact_person"));
	        user.setOfficePhone(rs.getString("office_phone"));
	        user.setDistrictCode(rs.getString("district_code"));
			user.setCompanyAddress(rs.getString("company_address"));
			user.setCertificate(rs.getString("certificate"));
			user.setCertificateFrontPicUrl(rs.getString("certiticate_front_url"));
			user.setCertificateBackPicUrl(rs.getString("certificate_back_url"));
	        return user;
	    }
	}
	
	private Object[] buildSelectSql(CompanyUser user) {
		
		StringBuilder select = new StringBuilder();
		select.append("select * from user_company where 1");
		if(null==user){
			return new Object[]{select.toString()};
		}
		Object[] values = new Object[30];
		int i = 0;
		if(StringUtils.hasText(user.getAccount())){
			select.append(" and account = ?");
			values[i] = user.getAccount();
			i++;
		}
		if(StringUtils.hasText(user.getType())){
			select.append(" and account_type = ?");
			values[i] = user.getType();
		    i++;
		}
		if(StringUtils.hasText(user.getRole())){
			select.append(" and account_role = ?");
			values[i] = user.getRole();
			i++;
		}
		if(StringUtils.hasText(user.getState())){
			select.append(" and account_state = ?");
			values[i] = user.getState();
			i++;
		}
		if(StringUtils.hasText(user.getCompanyName())){
			select.append(" and company_name = ?");
			values[i] = user.getCompanyName();
			i++;
		}
		if(StringUtils.hasText(user.getLegalPerson())){
			select.append(" and legal_person = ?");
			values[i] = user.getLegalPerson();
			i++;
		}
		if(StringUtils.hasText(user.getContactPerson())){
			select.append(" and contact_person = ?");
			values[i] = user.getContactPerson();
			i++;
		}
		if(StringUtils.hasText(user.getContactPhone())){
			select.append(" and contact_phone = ?");
			values[i] = user.getContactPhone();
			i++;
		}
		if(StringUtils.hasText(user.getOfficePhone())){
			select.append(" and office_phone = ?");
			values[i] = user.getOfficePhone();
			i++;
		}
		if(StringUtils.hasText(user.getDistrictCode())){
			select.append(" and district_code = ?");
			values[i] = user.getDistrictCode();
			i++;
		}
		if(StringUtils.hasText(user.getCompanyAddress())){
			select.append(" and company_address = ?");
			values[i] = user.getCompanyAddress();
			i++;
		}
		if(StringUtils.hasText(user.getCertificate())){
			select.append(" and certificate = ?");
			values[i] = user.getCertificate();
			i++;
		}
		
		values[i] = select.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}
	
	
	private Object[] buildUpdateSql(CompanyUser user) {
		if(null==user||null==user.getAccount()){
			return null;
		}
		StringBuilder update = new StringBuilder();
		Object[] values = new Object[30];
		int i = 0;
		update.append("update user_company set update_time = ?");
		values[i] = new Date(); 
		i++;
		
		if(StringUtils.hasText(user.getPassword())){
			update.append(",password = ?");
			values[i] = user.getPassword();
			i++;
		}
		if(StringUtils.hasText(user.getDescription())){
			update.append(",description = ?");
			values[i] = user.getDescription();
			i++;
		}
		if(StringUtils.hasText(user.getType())){
			update.append(",account_type = ?");
			values[i] = user.getType();
			i++;
		}
		if(StringUtils.hasText(user.getRole())){
			update.append(",account_role = ?");
			values[i] = user.getRole();
			i++;
		}
		if(StringUtils.hasText(user.getState())){
			update.append(",account_state = ?");
			values[i] = user.getState();
			i++;
		}
		if(StringUtils.hasText(user.getCompanyName())){
			update.append(",company_name = ?");
			values[i] = user.getCompanyName();
			i++;
		}
		if(StringUtils.hasText(user.getLegalPerson())){
			update.append(",legal_person = ?");
			values[i]=user.getLegalPerson();
			i++;
		}
		if(StringUtils.hasText(user.getContactPerson())){
			update.append(",contact_person = ?");
			values[i]=user.getContactPerson();
			i++;
		}
		if(StringUtils.hasText(user.getContactPhone())){
			update.append(",contact_phone = ?");
			values[i]=user.getContactPerson();
			i++;
		}
		if(StringUtils.hasText(user.getOfficePhone())){
			update.append(",office_phone = ?");
			values[i]=user.getOfficePhone();
			i++;
		}
		if(StringUtils.hasText(user.getDistrictCode())){
			update.append(",district_code = ?");
			values[i]=user.getDistrictCode();
			i++;
		}
		if(StringUtils.hasText(user.getCompanyAddress())){
			update.append(",company_address = ?");
			values[i]=user.getCompanyAddress();
			i++;
		}
		if(StringUtils.hasText(user.getCertificate())){
			update.append(",certificate = ?");
			values[i]=user.getCertificate();
			i++;
		}
		if(StringUtils.hasText(user.getCertificateFrontPicUrl())){
			update.append(",certiticate_front_url = ?");
			values[i]=user.getCertificateFrontPicUrl();
			i++;
		}
		if(StringUtils.hasText(user.getCertificateBackPicUrl())){
			update.append(",certificate_back_url = ?");
			values[i]=user.getCertificateBackPicUrl();
			i++;
		}
		
		values[i] = user.getAccount();
		i++;
		
		update.append(" where account = ?");
		values[i] = update.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}

}

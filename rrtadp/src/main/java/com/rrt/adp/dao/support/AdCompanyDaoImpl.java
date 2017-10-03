package com.rrt.adp.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.rrt.adp.dao.AdCompanyDao;
import com.rrt.adp.model.AdCompany;
import com.rrt.adp.model.Page;
import com.rrt.adp.util.PaginationJdbcTemplate;
import com.rrt.adp.util.SequenceGenerator;

@Repository
public class AdCompanyDaoImpl implements AdCompanyDao {
	
	@Resource
	private PaginationJdbcTemplate jdbcTemplate;

	@Override
	public int insertAdCompany(AdCompany company) {
		if(null==company){
			return 0;
		}
		company.setId(SequenceGenerator.next());
		company.setCreateTime(new Date());
		company.setDataState(AdCompany.STATE_NEW);
		return this.jdbcTemplate.update("insert into ad_company (id, data_state, create_time, update_time, "
				+ "name, org_code, legal_person, address, contact_person, contact_phone) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[]{
						company.getId(),
						company.getDataState(),
						company.getCreateTime(),
						company.getUpdateTime(),
						company.getName(),
						company.getOrgCode(),
						company.getLegalPerson(),
						company.getAddress(),
						company.getContactPerson(),
						company.getContactPhone()
				});
	}

	@Override
	public int updateAdCompany(AdCompany company) {
		Object[] update = buildUpdateSql(company);
		if(null==update){
			return 0;
		}
		String sql = (String)update[update.length-1];
		Object[] values = new Object[update.length-1];
		System.arraycopy(update, 0, values, 0, update.length-1);
		return this.jdbcTemplate.update(sql, values);
	}

	@Override
	public int deleteAdCompany(String companyId) {
		if(null==companyId){
			return 0;
		}
		return this.jdbcTemplate.update("delete from ad_company where id = ?", companyId);
	}
	
	@Override
	public int countAdCompany(AdCompany company) {
		Object[] select = buildSelectSql("select count(*) from ad_company", company);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryForObject(sql, Integer.class, values);
	}

	@Override
	public List<AdCompany> selectAdCompany(AdCompany company, Page<?> page) {
		Object[] select = buildSelectSql("select * from ad_company", company);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryPagination(sql, values, 
				page.getPageNum(), page.getPageSize(), new CompanyMapper());
	}
	
	private static final class CompanyMapper implements RowMapper<AdCompany> {

	    public AdCompany mapRow(ResultSet rs, int rowNum) throws SQLException {
	        AdCompany company = new AdCompany();
	        company.setId(rs.getString("id"));
	        company.setDataState(rs.getString("data_state"));
	        company.setCreateTime(rs.getDate("create_time"));
	        company.setUpdateTime(rs.getDate("update_time"));
	        company.setName(rs.getString("name"));
	        company.setOrgCode(rs.getString("org_code"));
	        company.setLegalPerson(rs.getString("legal_person"));
	        company.setAddress(rs.getString("address"));
	        company.setContactPerson(rs.getString("contact_person"));
	        company.setContactPhone(rs.getString("contact_phone"));        
	        return company;
	    }
	}
	
	private Object[] buildSelectSql(String selectOrCount, AdCompany company) {
		
		StringBuilder select = new StringBuilder();
		select.append(selectOrCount);
		select.append(" where 1");
		if(null==company){
			return new Object[]{select.toString()};
		}
		Object[] values = new Object[20];
		int i = 0;
		if(StringUtils.hasText(company.getId())){
			select.append(" and id = ?");
			values[i] = company.getId();
		    i++;
		}
		if(StringUtils.hasText(company.getName())){
			select.append(" and name like ?");
			values[i] = "%"+company.getName()+"%";
			i++;
		}
		if(StringUtils.hasText(company.getOrgCode())){
			select.append(" and org_code = ?");
			values[i] = company.getOrgCode();
		    i++;
		}
		if(StringUtils.hasText(company.getLegalPerson())){
			select.append(" and legal_person = ?");
			values[i] = company.getLegalPerson();
			i++;
		}
		if(StringUtils.hasText(company.getAddress())){
			select.append(" and address like ?");
			values[i] = "%"+company.getAddress()+"%";
			i++;
		}
		if(StringUtils.hasText(company.getContactPerson())){
			select.append(" and contact_person = ?");
			values[i] = company.getContactPerson();
			i++;
		}
		if(StringUtils.hasText(company.getContactPhone())){
			select.append(" and contact_phone = ?");
			values[i] = company.getContactPhone();
			i++;
		}
		values[i] = select.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}
	
	private Object[] buildUpdateSql(AdCompany company) {
		if(null==company||null==company.getId()){
			return null;
		}
		StringBuilder update = new StringBuilder();
		Object[] values = new Object[20];
		int i = 0;
		update.append("update ad_company set update_time = ?");
		values[i] = new Date(); 
		i++;
		if(StringUtils.hasText(company.getName())){
			update.append(",name = ?");
			values[i] = company.getName();
			i++;
		}
		if(StringUtils.hasText(company.getOrgCode())){
			update.append(",org_code = ?");
			values[i] = company.getOrgCode();
			i++;
		}
		if(StringUtils.hasText(company.getLegalPerson())){
			update.append(",legal_person = ?");
			values[i] = company.getLegalPerson();
			i++;
		}
		if(StringUtils.hasText(company.getAddress())){
			update.append(",address = ?");
			values[i] = company.getAddress();
			i++;
		}
		if(StringUtils.hasText(company.getContactPerson())){
			update.append(",contact_person = ?");
			values[i] = company.getContactPerson();
			i++;
		}
		if(StringUtils.hasText(company.getContactPhone())){
			update.append(",contact_phone = ?");
			values[i] = company.getContactPhone();
			i++;
		}
		
		values[i] = company.getId();
		i++;
		
		update.append(" where id = ?");
		values[i] = update.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}
}
